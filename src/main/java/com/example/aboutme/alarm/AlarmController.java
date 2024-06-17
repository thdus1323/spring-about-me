package com.example.aboutme.alarm;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AlarmController {
    private final AlarmService alarmService;
    private final HttpSession session;
}
