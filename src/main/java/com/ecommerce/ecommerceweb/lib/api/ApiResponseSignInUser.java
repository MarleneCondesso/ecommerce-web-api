package com.ecommerce.ecommerceweb.lib.api;

public class ApiResponseSignInUser {

    private String status;
    private String token;

    public ApiResponseSignInUser(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
