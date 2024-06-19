package com.example.aboutme.user;

import com.example.aboutme.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.aboutme.user.enums.UserRole.EXPERT;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;



}
