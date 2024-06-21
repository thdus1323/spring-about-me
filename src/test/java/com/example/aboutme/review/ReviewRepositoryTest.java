package com.example.aboutme.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void findByExpertId() {
        List<Review> reviews = reviewRepository.findByExpertId(22);
        reviews.stream().forEach(review -> System.out.println("review = " + review));
    }
}