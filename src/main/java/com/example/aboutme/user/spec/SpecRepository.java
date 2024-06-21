package com.example.aboutme.user.spec;

import com.example.aboutme.user.pr.PR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecRepository extends JpaRepository<Spec,Integer> {

    @Query("SELECT s FROM Spec s WHERE s.user.id = :expertId")
    List<Spec> findByExpertId(@Param("expertId") Integer expertId);
}
