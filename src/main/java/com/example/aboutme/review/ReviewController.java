package com.example.aboutme.review;

import com.example.aboutme.review.ReviewResponseDTO.ExpertReviewDTO.ReviewDTORecord;
import com.example.aboutme.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final HttpSession session;
    //TODO: 전문가용 페이지
    //후기
    @GetMapping("/review/{expertId}")
    public String review(Model model, @PathVariable("expertId") Integer expertId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        ReviewDTORecord reviewDTORecord = reviewService.getExpertReview(expertId,sessionUser);

        model.addAttribute("reviewList", reviewDTORecord);
        return "expert/review";
    }


}
