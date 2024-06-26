package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommRepository extends JpaRepository<Comm, Integer> {


    //클라이언트 게시물 조회
    @Query("SELECT c FROM Comm c WHERE c.user.id = :userId")
    List<Comm> findByUserId(@Param("userId") Integer userId);

    List<Comm> findByCategory(CommCategory category);

    // 메인 커뮤니티 리스트
    @Query("""
            SELECT new com.example.aboutme.user.UserResponse$ClientMainDTO$CommDTO(
            c.id, 
            c.title, 
            c.content, 
            c.category, 
            c.user.profileImage, 
            c.user.name, 
            r.user.profileImage, 
            r.user.name
            )
            FROM Comm c
            JOIN c.replies r
            WHERE r.user.userRole = com.example.aboutme.user.enums.UserRole.EXPERT
                    """)
    List<UserResponse.ClientMainDTO.CommDTO> findCommsWithReply();

    // /com 출력하려고 뽑은 쿼리
    @Query("""
               SELECT c FROM Comm c LEFT JOIN FETCH c.replies r
            """)
    List<CommResponse.CommWithRepliesDTO> findAllCommWithReplies();

}