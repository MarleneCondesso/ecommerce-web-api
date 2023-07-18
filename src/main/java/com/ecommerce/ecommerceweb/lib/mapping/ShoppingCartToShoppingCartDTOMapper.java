package com.ecommerce.ecommerceweb.lib.mapping;


import com.ecommerce.ecommerceweb.lib.dto.ShoppingCartDTO;
import com.ecommerce.ecommerceweb.model.ShoppingCart;

public class ShoppingCartToShoppingCartDTOMapper {

    public ShoppingCartDTO Map(ShoppingCart shoppingCart){
        return new ShoppingCartDTO(shoppingCart.getId(), shoppingCart.getProduct(), shoppingCart.getQuantity());
    }

}
