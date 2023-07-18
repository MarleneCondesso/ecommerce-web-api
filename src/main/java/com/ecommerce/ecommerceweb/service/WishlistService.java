package com.ecommerce.ecommerceweb.service;

import com.ecommerce.ecommerceweb.lib.dto.ProductDTO;
import com.ecommerce.ecommerceweb.lib.mapping.ProductToProductDTOMapper;
import com.ecommerce.ecommerceweb.model.User;
import com.ecommerce.ecommerceweb.model.Wishlist;
import com.ecommerce.ecommerceweb.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    ProductToProductDTOMapper productDTOMapper = new ProductToProductDTOMapper();



    public List<ProductDTO> get(User user){
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Wishlist> wishlists = wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);

        for(Wishlist wishlist : wishlists){

            productDTOS.add(productDTOMapper.Map(wishlist.getProduct(), wishlist.getProduct().getCategory()));

        }

        return productDTOS;
    }



    public void create(Wishlist wishlist){
        wishlistRepository.save(wishlist);
    }
}
