package com.example.aboutme.counsel;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CounselController {
    private final CounselService counselService;
    private final HttpSession session;
}
