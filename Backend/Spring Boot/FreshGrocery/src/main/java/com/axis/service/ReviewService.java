package com.axis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.entity.Customer;
import com.axis.entity.Product;
import com.axis.entity.Review;
import com.axis.repository.CustomerRepository;
import com.axis.repository.ProductRepository;
import com.axis.repository.ReviewRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

	@Autowired
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Review addReview(Long customerId, Long productId, int rating, String comment) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (customerOptional.isPresent() && productOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Product product = productOptional.get();

            Review review = new Review();
            review.setCustomer(customer);
            review.setProduct(product);
            review.setRating(rating);
            review.setComment(comment);
            review.setTimestamp(new Timestamp(System.currentTimeMillis()));

            return reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Customer or product not found");
        }
    }

    public List<Review> getProductReviews(Long productId) {
        return productRepository.findReviewsByProductId(productId);
    }
}
