package com.axis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.axis.entity.Product;
import com.axis.entity.Review;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_CategoryName(String categoryName);

    List<Product> findByBrand(String brand);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategory_CategoryNameAndBrand(String categoryName, String brand);

    List<Product> findByCategory_CategoryNameIgnoreCaseAndBrandIgnoreCaseAndNameIgnoreCase(String categoryName, String brand, String product);
  
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    List<Review> findReviewsByProductId(@Param("productId") Long productId);
}


