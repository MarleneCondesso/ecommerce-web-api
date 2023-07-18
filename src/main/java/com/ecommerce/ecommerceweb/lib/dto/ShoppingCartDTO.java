package com.ecommerce.ecommerceweb.lib.dto;

import com.ecommerce.ecommerceweb.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShoppingCartDTO {

    @JsonIgnore
    private Integer id;
    private Product product;
    private Integer quantity;

    public ShoppingCartDTO(Integer id, Product product, Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
