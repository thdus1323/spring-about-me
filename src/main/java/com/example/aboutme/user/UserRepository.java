package com.example.aboutme.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
            SELECT new com.example.aboutme.user.UserResponse$ClientMainDTO$ExpertDTO(
            u.id,
            u.profileImage,
            u.name,
            u.expertTitle
            )
            FROM User u
            WHERE u.userRole = com.example.aboutme.user.enums.UserRole.EXPERT
            """)
    List<UserResponse.ClientMainDTO.ExpertDTO> findExpert();

}