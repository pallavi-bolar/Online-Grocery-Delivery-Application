package com.axis.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int review_id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    // Constructors, getters, and setters

    public Review() {
		super();
	}

	public Review(int review_id, Customer customer, Product product, int rating, String comment, Timestamp timestamp) {
		super();
		this.review_id = review_id;
		this.customer = customer;
		this.product = product;
		this.rating = rating;
		this.comment = comment;
		this.timestamp = timestamp;
	}
    
	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
