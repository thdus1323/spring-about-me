package com.example.aboutme.schedule;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselResponseRecord.CounselDTO.CounselDTORecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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