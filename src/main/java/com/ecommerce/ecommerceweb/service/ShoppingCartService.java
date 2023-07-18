package com.ecommerce.ecommerceweb.service;

import com.ecommerce.ecommerceweb.lib.dto.ListShoppingCartDTO;
import com.ecommerce.ecommerceweb.lib.dto.ShoppingCartDTO;
import com.ecommerce.ecommerceweb.lib.exceptions.CustomException;
import com.ecommerce.ecommerceweb.lib.mapping.ShoppingCartToShoppingCartDTOMapper;
import com.ecommerce.ecommerceweb.model.Product;
import com.ecommerce.ecommerceweb.model.ShoppingCart;
import com.ecommerce.ecommerceweb.model.User;
import com.ecommerce.ecommerceweb.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductService productService;

    ShoppingCartToShoppingCartDTOMapper shoppingCartDTOMapper = new ShoppingCartToShoppingCartDTOMapper();

    public ListShoppingCartDTO getAll(User user){

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<ShoppingCartDTO> shoppingCartsDTO = new ArrayList<>();

        double cost = 0;

        for(ShoppingCart shoppingCart : shoppingCartList){
            shoppingCartsDTO.add(shoppingCartDTOMapper.Map(shoppingCart));
            cost += shoppingCart.getProduct().getPrice() * shoppingCart.getQuantity();
        }



        return  new ListShoppingCartDTO(shoppingCartsDTO, cost);

    }


    public void addProductToShoppingCard(ShoppingCartDTO shoppingCartDTO, User user){

        Product product = productService.findById(shoppingCartDTO.getProduct().getId());

        ShoppingCart shoppingCart = new ShoppingCart(product, user, shoppingCartDTO.getQuantity());

        shoppingCartRepository.save(shoppingCart);

    }

    public void deleteProductFromShoppingCart(Integer id, User user){

        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);

        if(shoppingCart.isEmpty()) {
            throw new CustomException("This shopping cart id is not valid!" + id);
        }

        if(!shoppingCart.get().getUser().equals(user)){
            throw new CustomException("This shopping cart doesn't belong to user" + " " + user.getFirstName() + " " + user.getLastName());
        }

        shoppingCartRepository.delete(shoppingCart.get());
    }
}
