package com.example.aboutme.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    전문가한테 속한 리뷰 리스트
    @Query("SELECT r FROM Review r WHERE r.counsel.expert.id = :expertId")
    List<Review> findByExpertId(@Param("expertId") Integer expertId);
}
