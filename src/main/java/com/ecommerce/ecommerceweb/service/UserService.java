package com.ecommerce.ecommerceweb.service;

import com.ecommerce.ecommerceweb.lib.api.ApiResponseSignInUser;
import com.ecommerce.ecommerceweb.lib.api.ApiResponseSignUpUser;
import com.ecommerce.ecommerceweb.lib.exceptions.AuthenticationException;
import com.ecommerce.ecommerceweb.lib.exceptions.CustomException;
import com.ecommerce.ecommerceweb.model.AuthenticationToken;
import com.ecommerce.ecommerceweb.model.SignIn;
import com.ecommerce.ecommerceweb.model.SignUp;
import com.ecommerce.ecommerceweb.model.User;
import com.ecommerce.ecommerceweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    public List<User> listUsers(){
        return userRepository.findAll();
    }
    @Transactional
    public ApiResponseSignUpUser signUp(SignUp signUp){
        if(readUserByEmail(signUp.getEmail())){
            throw new CustomException("This User already have an account with this email!");
        }

        String hashPassword;

        try{
            hashPassword = hashPassword(signUp.getPassword());
        } catch (NoSuchAlgorithmException e){
            throw new CustomException(e.getMessage());
        }

        User user = new User(signUp.getFirstName(), signUp.getLastName(), signUp.getEmail(), hashPassword);

        userRepository.save(user);

        authenticationTokenService.createAuthenticationToken(user);

        return new ApiResponseSignUpUser("sucess", "this is a new sign up!");
    }

    public ApiResponseSignInUser signIn(SignIn signIn){

        User user = userRepository.findByEmail(signIn.getEmail());

        if(Objects.isNull(user)){
            throw new AuthenticationException("This email is not register, please first sign up.");
        }

        try {
            if(!user.getPassword().equals(hashPassword(signIn.getPassword()))){
                throw new AuthenticationException("This password is invalid!");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationException(e.getMessage());
        }

        AuthenticationToken token = authenticationTokenService.readAuthenticationTokenByUser(user);

        if(Objects.isNull(token)){
            throw new CustomException("This user can not access to his token!");
        }

        return new ApiResponseSignInUser("success", token.getToken());
    }


    public boolean readUserByEmail(String email){
       return Objects.nonNull(userRepository.findByEmail(email));
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();

        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return hash;
    }

}
