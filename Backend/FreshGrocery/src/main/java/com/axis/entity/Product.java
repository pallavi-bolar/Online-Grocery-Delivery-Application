package com.axis.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "brand", nullable = false)
    private String brand;
    
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER) // Eagerly fetch the category association
    @JoinColumn(name = "category_id")
    private Category category;

 // Constructors, getters, and setters

	public Product() {
		super();
	}

	public Product(Long productId, String name, BigDecimal price, String imageUrl, String brand, int quantity, Category category) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
		this.brand = brand;
		this.quantity = quantity;
		this.category = category;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
