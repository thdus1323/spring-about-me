package com.example.aboutme.schedule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ScheduleRestController {

    private final ScheduleService scheduleService;

    public ScheduleRestController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/api/available-times")
    public List<String> getAvailableTimes(
            @RequestParam("date") String dateStr,
            @RequestParam("expertId") int expertId) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        return scheduleService.getAvailableTimesForDate(expertId, date);
    }
}