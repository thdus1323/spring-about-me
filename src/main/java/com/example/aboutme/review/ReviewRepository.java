package com.example.aboutme.review;

import com.example.aboutme.user.UserResponseRecord.ExpertMainDTO.RecentReviewRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    //    전문가한테 속한 리뷰 리스트
    @Query("SELECT r FROM Review r WHERE r.counsel.expert.id = :expertId")
    List<Review> findByExpertId(@Param("expertId") Integer expertId);

    Page<Review> findByClientId(@Param("clientId") Integer clientId, Pageable pageable);

    @Query("""
            SELECT new com.example.aboutme.user.UserResponseRecord.ExpertMainDTO.RecentReviewRecord(r.id, u.name, r.score, r.content) 
            FROM Review r  
            JOIN r.client u  
            WHERE r.counsel.expert.id = :expertId
            """)
    List<RecentReviewRecord> findReviewRecordsByExpertId(@Param("expertId") Integer expertId);

    //    전문가한테 속한 최근 한달간의 리뷰 리스트
    @Query("SELECT r FROM Review r WHERE r.counsel.expert.id = :expertId AND r.createdAt >= :oneMonthAgo")
    List<Review> findByExpertIdAndCreatedAtAfter(@Param("expertId") Integer expertId, @Param("oneMonthAgo") Timestamp oneMonthAgo);

    //스코어랑 카운트 계산
    @Query("SELECT r.score, COUNT(r) as count FROM Review r WHERE r.expert.id = :expertId GROUP BY r.score ORDER BY r.score DESC ")
    List<Object[]> countReviewByScore(@Param("expertId") Integer expertId);

    // 리뷰 평균점수 계산
    @Query("SELECT AVG(r.score) FROM Review r WHERE r.expert.id = :expertId")
    Double findAverageScoreByExpertId(@Param("expertId") Integer expertId);

    // 리뷰총 갯수
    @Query("SELECT count(r.score) FROM Review r WHERE r.expert.id = :expertId")
    Integer countByExpertId(@Param("expertId") Integer expertId);
}
