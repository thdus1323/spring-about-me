package com.example.aboutme.comm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class CommRepositoryTest {

    @Autowired
    private CommRepository commRepository;

    @Test
    void findAllCommsWithReply_test() {
        List<CommResponse.ALLCommWithRepliesDTO> commAndReplyDTOS = commRepository.findAllCommWithReplies();
        commAndReplyDTOS.forEach(commAndReplyDTO -> System.out.println("commAndReplyDTO = " + commAndReplyDTO));
    }

    @Test
    void detail_test() {
        CommResponse.CommWithRepliesDTO comm = commRepository.findByIdDetail(1);
        System.out.println(comm.toString());
    }
}