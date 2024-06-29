package com.example.aboutme.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}