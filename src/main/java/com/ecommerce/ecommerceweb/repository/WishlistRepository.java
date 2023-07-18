package com.ecommerce.ecommerceweb.repository;

import com.ecommerce.ecommerceweb.model.User;
import com.ecommerce.ecommerceweb.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {


    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);

}
