package com.example.aboutme.user.pr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PRRepository extends JpaRepository<PR, Integer> {
    @Query("SELECT p FROM PR p WHERE p.user.id = :expertId")
    List<PR> findByExpertId(@Param("expertId") Integer expertId);
}

