package com.axis.service;

import org.springframework.stereotype.Service;



import com.axis.entity.CartItems;
import com.axis.entity.Customer;
import com.axis.entity.Product;
import com.axis.entity.ShoppingCart;
import com.axis.exception.NotFoundException;
import com.axis.repository.CartItemRepository;
import com.axis.repository.CustomerRepository;
import com.axis.repository.ProductRepository;
import com.axis.repository.ShoppingCartRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ShoppingCartService {

	@Autowired
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CartItemRepository cartItemRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<CartItems> getCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByShoppingCart_CartId(cartId);
    }
    
    public Long getCartIdByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        ShoppingCart shoppingCart = customer.getShoppingCart();
        if (shoppingCart == null) {
            throw new NotFoundException("Shopping cart not found for customer");
        }

        return shoppingCart.getCartId();
    }
    
    public void addProductToCart(Long customerId, Long productId, int quantity) {
    	Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        ShoppingCart shoppingCart = customer.getShoppingCart();
        if (shoppingCart == null) {
            // Create a new shopping cart if it doesn't exist
            shoppingCart = new ShoppingCart();
            shoppingCart.setCustomer(customer);
            shoppingCart.setCreatedDate(LocalDate.now());
            shoppingCartRepository.save(shoppingCart);
            customer.setShoppingCart(shoppingCart);
            customerRepository.save(customer);
        }

        Product product = getProductById(productId);
        if (product != null) {
            CartItems existingCartItem = cartItemRepository.findByShoppingCartAndProduct(shoppingCart, product);
            if (existingCartItem != null) {
                // Update quantity of existing cart item
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                cartItemRepository.save(existingCartItem);
            } else {
                // Create a new cart item
                CartItems cartItem = new CartItems();
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setShoppingCart(shoppingCart);
                cartItemRepository.save(cartItem);
            }
        }
    }

    private Product getProductById(Long productId) {
        // Implement logic to retrieve product by ID
        return productRepository.findById(productId).orElse(null);
    }


    public void editCartItemQuantity(Long cartItemId, int newQuantity) {
        CartItems cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new NotFoundException("Cart item not found"));

        cartItem.setQuantity(newQuantity);

        cartItemRepository.save(cartItem);
    }

    public void removeCartItem(Long cartItemId) {
        CartItems cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new NotFoundException("Cart item not found"));

        cartItemRepository.delete(cartItem);
    }

}