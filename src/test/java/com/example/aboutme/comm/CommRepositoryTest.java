package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.user.User;
import com.example.aboutme.user.enums.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommRepositoryTest {

    @Autowired
    private CommRepository commRepository;

    @Test
    void findAllCommsWithReply_test() {
//        List<CommResponse.ALLCommWithRepliesDTO> commAndReplyDTOS = commRepository.findAllCommWithReplies();
//
//        commAndReplyDTOS.forEach(commAndReplyDTO -> System.out.println("commAndReplyDTO = " + commAndReplyDTO));
    }

    @Test
    void detail_test() {
        CommResponse.CommWithRepliesDTO comm = commRepository.findByIdDetail(1);
        System.out.println(comm.toString());
    }

    @Test
    void findByCategoryTest() {
        List<Comm> comms = commRepository.findByCategory(CommCategory.FINANCE_BUSINESS);
        comms.forEach(comm -> System.out.println("comm = " + comm));

    }

    @Test
    void findCommMainByCategory() {
        List<CommResponse.CommMainByCategory> comms = commRepository.findCommMainByCategory(CommCategory.FINANCE_BUSINESS);
        comms.forEach(comm -> System.out.println("comm = " + comm));
    }

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1);
        user.setName("Test User");
        user.setProfileImage("test_image.png");
        user.setUserRole(UserRole.CLIENT);

        Comm comm = new Comm();
        comm.setCategory(CommCategory.FINANCE_BUSINESS);
        comm.setTitle("Test Title");
        comm.setContent("Test Content");
        comm.setUser(user);
        commRepository.save(comm);
    }

    @Test
    void findAllCommWithReplies() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Comm> commPage = commRepository.findAllCommWithReplies(pageable);

        assertThat(commPage).isNotNull();
        assertThat(commPage.getContent().size()).isLessThanOrEqualTo(10);
    }

    @Test
    void findByUserId() {
        int userId = 1;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Comm> commPage = commRepository.findByUserId(userId, pageable);

        assertThat(commPage).isNotNull();
        assertThat(commPage.getContent().size()).isLessThanOrEqualTo(10);
    }
}