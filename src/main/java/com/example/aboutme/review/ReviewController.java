package com.example.aboutme.review;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.review.ReviewResponseDTO.ExpertReviewDTO.ReviewDTORecord;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final RedisTemplate<String, Object> redisTemp;
    private final RedisUtil redisUtil;

    //TODO: 전문가용 페이지
    //후기
    @GetMapping("/review")
    public String review(Model model) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        log.info("sessionUser {}" , sessionUser);
        ReviewDTORecord reviewDTORecord = reviewService.getExpertReview(sessionUser);

        model.addAttribute("reviewList", reviewDTORecord);
        return "expert/review";
    }


}
