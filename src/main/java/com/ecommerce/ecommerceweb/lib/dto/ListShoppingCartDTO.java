package com.ecommerce.ecommerceweb.lib.dto;

import java.util.List;

public class ListShoppingCartDTO {


    List<ShoppingCartDTO> listShoppingCart;
    private double cost;

    public ListShoppingCartDTO(List<ShoppingCartDTO> listShoppingCart, double cost) {
        this.listShoppingCart = listShoppingCart;
        this.cost = cost;
    }

    public List<ShoppingCartDTO> getListShoppingCart() {
        return listShoppingCart;
    }

    public void setListShoppingCart(List<ShoppingCartDTO> listShoppingCart) {
        this.listShoppingCart = listShoppingCart;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
