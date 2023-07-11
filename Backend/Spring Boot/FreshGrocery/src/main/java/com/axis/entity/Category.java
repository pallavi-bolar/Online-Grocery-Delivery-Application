package com.axis.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long customer_id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "description")
	private String description;

	public Category() {
		super();
	}

	public Category(Long id, String name, String description) {
		super();
		this.customer_id = id;
		this.categoryName = name;
		this.description = description;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long id) {
		this.customer_id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String name) {
		this.categoryName = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
