package com.axis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axis.entity.CartItems;
import com.axis.entity.Product;
import com.axis.entity.ShoppingCart;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {
	
	  @Query("SELECT ci FROM CartItems ci WHERE ci.shoppingCart = :shoppingCart AND ci.product = :product")
	    CartItems findByShoppingCartAndProduct(@Param("shoppingCart") ShoppingCart shoppingCart, @Param("product") Product product);

	List<CartItems> findByShoppingCart_CartId(Long cartId);


}
