package com.ecommerce.ecommerceweb.controller;

import com.ecommerce.ecommerceweb.lib.dto.ProductDTO;
import com.ecommerce.ecommerceweb.lib.api.ApiResponse;
import com.ecommerce.ecommerceweb.model.Product;
import com.ecommerce.ecommerceweb.model.User;
import com.ecommerce.ecommerceweb.model.Wishlist;
import com.ecommerce.ecommerceweb.service.AuthenticationTokenService;
import com.ecommerce.ecommerceweb.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {


    @Autowired
    WishlistService wishlistService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;


    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDTO>> getAllByUser( @PathVariable String token){
        authenticationTokenService.checkIfAuthenticationTokenExists(token);

        User user = authenticationTokenService.readUserByAuthenticationToken(token);

        List<ProductDTO> wishList =  wishlistService.get(user);

        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody Product product, @RequestParam String token){

        authenticationTokenService.checkIfAuthenticationTokenExists(token);

        User user = authenticationTokenService.readUserByAuthenticationToken(token);

        Wishlist wishlist = new Wishlist(user, product);

        wishlistService.create(wishlist);

        return new ResponseEntity<>(new ApiResponse(true, "The product was added to the wishlist"), HttpStatus.CREATED);
    }


}
