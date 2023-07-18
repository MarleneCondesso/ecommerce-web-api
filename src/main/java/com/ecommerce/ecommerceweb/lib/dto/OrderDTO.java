package com.ecommerce.ecommerceweb.lib.dto;

public class OrderDTO {

    private String product_name;
    private int quantity;
    private double price;
    private long product_id;
    private int user_id;

    public OrderDTO(String product_name, int quantity, double price, long product_id, int user_id) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
        this.product_id = product_id;
        this.user_id = user_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
