package com.ecommerce.ecommerceweb.lib.exceptions;

public class AuthenticationException extends IllegalArgumentException {

    public AuthenticationException(String message){
        super(message);
    }
}
