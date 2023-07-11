package com.axis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
