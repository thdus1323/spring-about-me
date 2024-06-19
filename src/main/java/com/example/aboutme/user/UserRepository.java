package com.example.aboutme.user;


import com.example.aboutme.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
}
