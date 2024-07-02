package com.example.aboutme.comm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommServiceTest {

    @Autowired
    private CommRepository commRepository;

    @Test
    void findAllCommWithReply() {
//        int page = 0;
//        int size = 10;
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Comm> commPage = commRepository.findAllByComm(pageable);
//
//        // 로그 출력
//        System.out.println("Total Elements: " + commPage.getTotalElements());
//        System.out.println("Total Pages: " + commPage.getTotalPages());
//        System.out.println("Current Page: " + commPage.getNumber());
//        System.out.println("Page Size: " + commPage.getSize());
//        System.out.println("Content: ");
//        commPage.getContent().forEach(comm -> {
//            System.out.println("Comm ID: " + comm.getId());
//            System.out.println("Content: " + comm.getContent());
//            System.out.println("Title: " + comm.getTitle());
//            System.out.println("Replies Count: " + comm.getReplies().size());
//            System.out.println("----------------------------");
//        });

    }


//        Page<Comm> commPage = commRepository.findAllCommWithReplies(pageable);
//        System.out.println("commPage = " + commPage);
//
//        System.out.println("Total Elements: " + commPage.getTotalElements());
//        System.out.println("Total Pages: " + commPage.getTotalPages());
//        System.out.println("Current Page: " + commPage.getNumber());
//        System.out.println("Page Size: " + commPage.getSize());
//        System.out.println("Content: ");
//
//        commPage.getContent().forEach(comm -> {
//            System.out.println("Comm ID: " + comm.getId());
//            System.out.println("Content: " + comm.getContent());
//            System.out.println("Title: " + comm.getTitle());
//            System.out.println("Replies Count: " + comm.getReplies().size());
//            System.out.println("----------------------------");
//        });
//        List<CommResponse.ALLCommWithRepliesDTO> dtos = commPage.getContent().stream()
//                .map(CommResponse.ALLCommWithRepliesDTO::new)
//                .collect(Collectors.toList());
//
//        Page<CommResponse.ALLCommWithRepliesDTO> resultPage = new PageImpl<>(dtos, pageable, commPage.getTotalElements());
//
//        // 검증
//        assertThat(resultPage).isNotNull();
//        assertThat(resultPage.getContent().size()).isLessThanOrEqualTo(size);
//        assertThat(resultPage.getTotalElements()).isEqualTo(commPage.getTotalElements());
//
//        // 추가적인 검증 로직을 여기에 추가할 수 있습니다.
//        // 예: 첫 번째 요소의 필드 값이 예상 값과 같은지 확인
//        if (!resultPage.getContent().isEmpty()) {
//            CommResponse.ALLCommWithRepliesDTO firstDto = resultPage.getContent().get(0);
//            assertThat(firstDto.getId()).isNotNull();
//            assertThat(firstDto.getWriterName()).isNotNull();
//        }
}