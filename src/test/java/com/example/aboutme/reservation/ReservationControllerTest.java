package com.example.aboutme.reservation;

import com.example.aboutme.reservation.resrvationResponse.ReservationListDTO;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.schedule.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationControllerTest {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    void findExpertReservation() {
        int id =21;
        List<Schedule> schedules = scheduleRepository.findByExpertId(id);
        schedules.forEach(schedule -> System.out.println("schedule = " + schedule));
    }
}