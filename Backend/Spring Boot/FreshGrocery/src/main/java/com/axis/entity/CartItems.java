package com.axis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    @JsonProperty("cartItemId")
    private Long cartItemId;
    
    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonProperty("product")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("cartItems")
    private ShoppingCart shoppingCart;
    
    // Constructors, getters, and setters

	public CartItems() {
		super();
	}

	public CartItems(Long cartItemId, ShoppingCart shoppingCart, Product product, int quantity) {
		super();
		this.cartItemId = cartItemId;
		this.shoppingCart = shoppingCart;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
    
}
