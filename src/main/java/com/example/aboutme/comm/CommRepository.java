package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommRepository extends JpaRepository<Comm, Integer> {

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

    // /comm 출력하려고 뽑은 쿼리
    @Query("""
                SELECT new com.example.aboutme.comm.CommResponse$CommAndReplyDTO(
                    c.id,
                    c.title,
                    c.content,
                    c.category,
                    c.user.profileImage,
                    c.user.name,
                    r.user.userRole,
                    r.user.profileImage,
                    r.user.name,
                    r.solution
                )
                FROM Comm c
                JOIN c.replies r
            """)
    List<CommResponse.CommAndReplyDTO> findAllCommsWithReply();


}