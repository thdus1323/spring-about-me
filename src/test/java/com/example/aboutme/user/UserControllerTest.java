package com.example.aboutme.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class UserControllerTest {

    @Autowired
    private UserRepository userRepo;

    //전문가 찾기 - 상세보기
    @Test
    void findExpertDetail() {
        // Mock 데이터
        User expert = new User();
        expert.setId(1);
        expert.setName("홍길동");
        expert.setEmail("expert1@nate.com");
        expert.setPhone("01012345678");
        expert.setProfileImage("expert1.jpg");

        Optional<User> user = userRepo.findById(expert.getId());
        System.out.println("user = " + user);

    }
}