package com.example.aboutme.alarm;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AlarmRepositoryTest {

    @Autowired
    private AlarmRepository alarmRepository;

    @Test
    public void findByReceiverId_test() {
        //given
        Integer receiverId = 1;

        // when
        Integer unreadCount = alarmRepository.countUnreadAlarmsByReceiverId(receiverId);

        // then
        Assertions.assertThat(unreadCount).isEqualTo(3);
    }
}