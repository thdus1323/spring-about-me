package com.example.aboutme.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleServic;

}