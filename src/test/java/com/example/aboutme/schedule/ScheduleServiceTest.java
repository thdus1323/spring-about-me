package com.example.aboutme.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@DataJpaTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    
    @Test
    void getAvailableTimesForDate() {
        int id = 21;
        String dateStr = "2024-06-24";
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        List<String> strings = scheduleService.getAvailableTimesForDate(id, date);
        strings.forEach(s -> System.out.println("s = " + s));
    }
}