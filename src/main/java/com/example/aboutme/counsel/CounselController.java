package com.example.aboutme.counsel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class CounselController {
    private final CounselService counselService;

    //상담일정
    @GetMapping("/schedule")
    public String schedule() {
        return "expert/schedule";
    }
}
