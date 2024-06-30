package com.example.aboutme.schedule;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.schedule.ScheduleReqRecord.ScheduleSaveReqDTO;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

@RequiredArgsConstructor
@RestController
public class ScheduleRestController {

    private final ScheduleService scheduleService;
    private final RedisUtil redisUtil;

    @GetMapping("/api/available-times")
    public List<String> getAvailableTimes(
            @RequestParam("date") String dateStr,
            @RequestParam("expertId") int expertId) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        return scheduleService.getAvailableTimesForDate(expertId, date);
    }

    @PostMapping("/save-schedule")
    public ResponseEntity<String> saveSchedule(@RequestBody ScheduleSaveReqDTO schedule) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        try {
            scheduleService.saveSchedule(schedule, sessionUser);
            return ResponseEntity.ok("스케줄이 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("스케줄 저장에 실패했습니다.");
        }
    }
}