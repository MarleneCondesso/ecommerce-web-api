package com.ecommerce.ecommerceweb.lib.stripe;

public class StripeResponse {

    private String id;

    public StripeResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
