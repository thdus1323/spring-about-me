package com.example.aboutme.comm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommRepository extends JpaRepository<Comm, Integer> {
//    SELECT new com.example.aboutme.comm.CommResponse.ClientMainCommListDTO(c, c.user, r)

//    @Query("""
//            SELECT new com.example.aboutme.comm.CommResponse.ClientMainCommListDTO(
//            c.id,
//            c.title,
//            c.content,
//            c.category,
//            c.user.profileImage,
//            c.user.name,
//            r.user.profileImage,
//            r.user.name
//            )
//            FROM Comm c
//            JOIN c.replies r
//            WHERE r.user.userRole = com.example.aboutme.user.enums.UserRole.EXPERT
//                    """)
//    List<CommResponse.ClientMainCommListDTO> findCommsWithReply();
}
