package com.ecommerce.ecommerceweb.repository;

import com.ecommerce.ecommerceweb.model.ShoppingCart;
import com.ecommerce.ecommerceweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findAllByUserOrderByCreatedDateDesc(User user);
}
