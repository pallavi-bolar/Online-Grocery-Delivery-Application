package com.axis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axis.entity.Product;
import com.axis.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
