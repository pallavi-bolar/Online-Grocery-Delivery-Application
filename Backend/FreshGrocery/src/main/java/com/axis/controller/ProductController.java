package com.axis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.entity.Product;
import com.axis.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        // Retrieve the product from the database using the product ID
        Optional<Product> productOptional = productRepository.findById(productId);
        
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam(required = false) String category,
                                        @RequestParam(required = false) String brand,
                                        @RequestParam(required = false) String name) {
        if (category != null && brand != null && name != null) {
            // Search by category, brand, and keyword
            return productRepository.findByCategory_CategoryNameIgnoreCaseAndBrandIgnoreCaseAndNameIgnoreCase(category, brand, name);
        } else if (brand != null) {
            // Search by brand
            return productRepository.findByBrand(brand);
        } else if (category != null) {
            // Search by category
            return productRepository.findByCategory_CategoryName(category);
        } else if (name != null) {
            // Search by keyword
            return productRepository.findByNameContainingIgnoreCase(name);
        } else {
            // No search parameters provided, return all products
            return productRepository.findAll();
        }
    }
}

