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

    // 현재 게시글 ID를 제외하고 같은 카테고리의 다른 게시글을 가져오는 쿼리
    List<Comm> findByCategoryAndIdNot(CommCategory category, Long id);


    List<Comm> findByCategory(CommCategory category);


    @Query("SELECT c FROM Comm c LEFT JOIN FETCH c.replies r WHERE c.category = :category AND c.id <> :id")
    List<Comm> findByCategoryWithRepliesAndExcludeId(@Param("category") CommCategory category, @Param("id") Integer id);


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
               SELECT c FROM Comm c LEFT JOIN FETCH c.replies r ORDER BY c.id DESC
            """)
    List<CommResponse.ALLCommWithRepliesDTO> findAllCommWithReplies();

    // detail 출력 쿼리
    @Query(" SELECT c FROM Comm c LEFT JOIN FETCH c.replies r WHERE c.id = :id ")
    CommResponse.CommWithRepliesDTO findByIdDetail(@Param("id") Integer id);


    // 전문답변 있는지 확인하는 쿼리
    @Query("SELECT COUNT(r) > 0 FROM Comm c JOIN c.replies r WHERE c.id = :id AND r.user.userRole = com.example.aboutme.user.enums.UserRole.EXPERT")
    boolean existsExpertReply(@Param("id") Integer id);
}