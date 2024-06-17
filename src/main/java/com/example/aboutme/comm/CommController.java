package com.example.aboutme.comm;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CommController {
    private final CommService commService;
    private final HttpSession session;
}
