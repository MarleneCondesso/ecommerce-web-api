package com.ecommerce.ecommerceweb.controller;


import com.ecommerce.ecommerceweb.model.AuthenticationToken;
import com.ecommerce.ecommerceweb.model.Product;
import com.ecommerce.ecommerceweb.service.AuthenticationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("authentication_token")
public class AuthenticationTokenController {

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @GetMapping("list")
    public ResponseEntity<List<AuthenticationToken>> getAllWithCategory(){
        List<AuthenticationToken> authenticationTokens = authenticationTokenService.getAll();

        return new ResponseEntity<>(authenticationTokens, HttpStatus.OK);
    }

}
