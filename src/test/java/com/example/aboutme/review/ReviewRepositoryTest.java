package com.example.aboutme.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void findByExpertId() {
        List<Review> reviews = reviewRepository.findByExpertId(22);
        reviews.stream().forEach(review -> System.out.println("review = " + review));
    }


    @Test
    void countByReviewAndAge() {
        Integer expertId = 21; // 테스트할 expertId
        List<Object[]> reviews = reviewRepository.countReviewByScore(expertId);
        reviews.forEach(review -> {
            Integer score = (Integer) review[0];
            Long count = (Long) review[1];
            System.out.println("Score: " + score + ", Count: " + count);
        });

        Double averageScore = reviewRepository.findAverageScoreByExpertId(expertId);
        System.out.println("Average Score: " + averageScore);

        Integer totalReviews = reviewRepository.countByExpertId(expertId);
        System.out.println("Total Reviews: " + totalReviews);
    }


}