package com.ecommerce.ecommerceweb.lib.api;

import java.time.LocalDateTime;

public class ApiResponse {

    private final boolean response;
    private final String message;

    public ApiResponse(boolean response, String message){
      this.response = response;
      this.message = message;
    }

    public boolean getResponse(){
        return response;
    }
    public String getMessage(){
        return message;
    }
    public String getTimestamp(){
        return LocalDateTime.now().toString();
    }

}

