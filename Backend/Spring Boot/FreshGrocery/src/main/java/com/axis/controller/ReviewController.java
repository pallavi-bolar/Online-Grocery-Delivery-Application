package com.axis.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.axis.entity.Review;
import com.axis.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody ReviewRequest reviewRequest) {
        Review review = reviewService.addReview(reviewRequest.getCustomerId(), reviewRequest.getProductId(), reviewRequest.getRating(), reviewRequest.getComment());
        return ResponseEntity.ok(review);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable("productId") Long productId) {
        List<Review> reviews = reviewService.getProductReviews(productId);
        return ResponseEntity.ok(reviews);
    }
    
    public static class ReviewRequest {
        private Long customerId;
        private Long productId;
        private int rating;
        private String comment;

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
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
    }

}
