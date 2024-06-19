package com.example.aboutme.user;

import com.example.aboutme.review.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


}