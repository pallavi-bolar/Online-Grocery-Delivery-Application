package com.axis.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "cart_id")
	private Long cartId;

	@Column(name = "created_date")
	private LocalDate createdDate;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;

	@OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItems> cartItems = new ArrayList<>();

	@OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
	private List<Order> orders;

	 // Constructors, getters, and setters
	
	public ShoppingCart() {
		super();
	}
	
	public ShoppingCart(Long cartId, Customer customer, LocalDate createdDate, List<CartItems> cartItems) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.createdDate = createdDate;
		this.cartItems = cartItems;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
