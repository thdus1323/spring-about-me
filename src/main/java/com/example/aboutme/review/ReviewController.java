package com.example.aboutme.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final ReviewService reviewService;

    //TODO: 전문가용 페이지
    //후기
    @GetMapping("/review")
    public String review() {
        return "expert/review";
    }

}
