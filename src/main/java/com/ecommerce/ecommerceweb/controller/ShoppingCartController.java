package com.ecommerce.ecommerceweb.controller;

import com.ecommerce.ecommerceweb.lib.dto.ListShoppingCartDTO;
import com.ecommerce.ecommerceweb.lib.dto.ShoppingCartDTO;
import com.ecommerce.ecommerceweb.lib.api.ApiResponse;
import com.ecommerce.ecommerceweb.model.User;
import com.ecommerce.ecommerceweb.service.AuthenticationTokenService;
import com.ecommerce.ecommerceweb.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping_cart")
public class ShoppingCartController {


    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @GetMapping("/{token}")
    public ResponseEntity<ListShoppingCartDTO> getAll(@PathVariable String token){
        User user = authenticationTokenService.checkTokenAndReturnUserByToken(token);

        ListShoppingCartDTO listShoppingCartDTO = shoppingCartService.getAll(user);

        return new ResponseEntity<>(listShoppingCartDTO, HttpStatus.OK);
    }

    @PostMapping("/add_new_item")
    public ResponseEntity<ApiResponse> add_new_item(@RequestBody ShoppingCartDTO shoppingCartDTO, @RequestParam String token){
        User user = authenticationTokenService.checkTokenAndReturnUserByToken(token);

        shoppingCartService.addProductToShoppingCard(shoppingCartDTO, user);


        return new ResponseEntity<>(new ApiResponse(true, "product added to shopping cart"), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> delete_item(@RequestBody Integer id, @RequestParam String token){

        User user = authenticationTokenService.checkTokenAndReturnUserByToken(token);

        shoppingCartService.deleteProductFromShoppingCart(id, user);

        return new ResponseEntity<>(new ApiResponse(true, "Product was deleted from shopping cart"), HttpStatus.OK);
    }

}
