package com.axis.controller;

import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.entity.CartItems;
import com.axis.service.ShoppingCartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

	@Autowired
    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    
    @PostMapping("/{customerId}/add")
    public ResponseEntity<String> addProductToCart(@PathVariable Long customerId, @RequestParam Long productId, @RequestParam int quantity) {
        shoppingCartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok("Product added to cart successfully");
    }
    
    @GetMapping("display-cart-items/{cartId}")
    public ResponseEntity<List<CartItems>> displayShoppingCartByCartId(@PathVariable Long cartId) {
        List<CartItems> cartItems = shoppingCartService.getCartItemsByCartId(cartId);
        return ResponseEntity.ok(cartItems);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Long> getCartIdByCustomerId(@PathVariable Long customerId) {
        Long cartId = shoppingCartService.getCartIdByCustomerId(customerId);
        return ResponseEntity.ok(cartId);
    }

    @PutMapping("/cart-item/{cartItemId}")
    public ResponseEntity<String> editCartItemQuantity(
            @PathVariable("cartItemId") Long cartItemId,
            @RequestParam("quantity") int newQuantity) {
        shoppingCartService.editCartItemQuantity(cartItemId, newQuantity);
        return new ResponseEntity<>("Cart item quantity updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable("cartItemId") Long cartItemId) {
        shoppingCartService.removeCartItem(cartItemId);
        return new ResponseEntity<>("Cart item removed successfully", HttpStatus.OK);
    }

}
