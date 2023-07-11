package com.axis.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "customers") 
@JsonIgnoreProperties({"orders"})
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "phone")
	private String phone;
	
	@Column(nullable = false)
	private String password;

	@Column(name = "delivery_address")
	private String delivery_address;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore
	private ShoppingCart shoppingCart;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonIgnore
	    private List<Order> orders;

	// Constructors, getters, setters, and other methods

	public Customer() {
		super();
	}

	public Customer(Long id, String name, String email, String phone_number, String password,String delivery_address,
			ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone_number;
		this.password = password;
		this.delivery_address = delivery_address;
		this.shoppingCart = shoppingCart;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone_number) {
		this.phone = phone_number;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	 public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
}