package com.example.aboutme.user;


import com.example.aboutme.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {


//    @Query("""
//            SELECT new com.example.aboutme.user.UserResponse$ExpertDTO(
//            u.id,
//            u.profileImage,
//            u.name,
//            u.expertTitle,
//            v.id,
//            v.voucherType
//            )
//            FROM User u
//            LEFT JOIN Voucher v ON v.expert.id = u.id
//            WHERE u.userRole = com.example.aboutme.user.enums.UserRole.EXPERT
//            """)
//    List<UserResponse.ExpertDTO> findExpertWithVoucher();
}
