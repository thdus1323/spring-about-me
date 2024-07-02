package com.example.aboutme.reply;

import com.example.aboutme.comm.enums.CommCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    // 카테고리별로 모든 댓글을 가져옵니다.
    @Query("SELECT r FROM Reply r JOIN r.comm c WHERE c.category = :category")
    List<Reply> findByCategory(@Param("category") CommCategory category);


    Page<Reply> findByUserId(@Param("userId") Integer userId, Pageable pageable);

    @Query("SELECT r FROM Reply r WHERE r.comm.id = :commId ORDER BY r.user.userRole DESC ")
    List<Reply> findByCommId(@Param("commId") Integer commId);
}
