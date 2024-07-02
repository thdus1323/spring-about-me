package com.example.aboutme.schedule;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.DayOfWeek;
import java.util.Optional;

@DataJpaTest
class ScheduleRepositoryTest {
    @Autowired
    private ScheduleRepository scheduleRepository;



    @Test
    void findByExpertIdAndDayOfWeek() {
        List<Schedule> schedules = scheduleRepository.findByExpertIdAndDay(21, DayOfWeek.MONDAY);
        schedules.forEach(schedule -> System.out.println("schedule = " + schedule));
//        assertFalse(schedules.isEmpty());
    }

    @Test
    void findByExpertIdAndDayOfWeekAndStartTime() {
        Optional<Schedule> schedule = scheduleRepository.findByExpertIdAndDayOfWeekAndStartTime(21, DayOfWeek.MONDAY, "09:00");
        Assertions.assertThat(schedule.isPresent());
    }
}