package com.ecommerce.ecommerceweb.service;

import com.ecommerce.ecommerceweb.lib.exceptions.AuthenticationException;
import com.ecommerce.ecommerceweb.model.AuthenticationToken;
import com.ecommerce.ecommerceweb.model.User;
import com.ecommerce.ecommerceweb.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationTokenService {

    @Autowired
    AuthenticationTokenRepository authenticationTokenRepository;


    public List<AuthenticationToken> getAll(){
        return authenticationTokenRepository.findAll();
    }

    public void createAuthenticationToken(User user){

        AuthenticationToken authenticationToken = new AuthenticationToken(user);

        authenticationTokenRepository.save(authenticationToken);
    }

    public User readUserByAuthenticationToken(String authenticationToken){

        AuthenticationToken token = authenticationTokenRepository.findByToken(authenticationToken);

        if(Objects.isNull(token)){
            throw new AuthenticationException("This token is not present");
        }

        return token.getUser();
    }
    public AuthenticationToken readAuthenticationTokenByUser(User user){
        return authenticationTokenRepository.findByUser(user);
    }

   public void checkIfAuthenticationTokenExists(String authenticationToken){
        if(Objects.isNull(authenticationToken)){
            throw new AuthenticationException("This token is invalid!");
        }

        if(Objects.isNull(readUserByAuthenticationToken(authenticationToken))){
            throw new AuthenticationException("This token is invalid!");
        }
   }

   public User checkTokenAndReturnUserByToken(String token){
       checkIfAuthenticationTokenExists(token);

       return readUserByAuthenticationToken(token);
   }
}
