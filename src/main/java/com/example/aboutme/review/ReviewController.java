package com.example.aboutme.review;

import com.example.aboutme._core.utils.RedisUtil;
import com.example.aboutme.review.ReviewReqRecord.GetReviewReqDTO;
import com.example.aboutme.review.ReviewReqRecord.WriteReviewReqDTO;
import com.example.aboutme.review.ReviewRespRecord.ExpertReviewDTO.ReviewDTORecord;
import com.example.aboutme.review.ReviewRespRecord.GetReviewRespDTO;
import com.example.aboutme.user.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        log.info("sessionUser {}", sessionUser);
        ReviewDTORecord reviewDTORecord = reviewService.getExpertReview(sessionUser);

        model.addAttribute("reviewList", reviewDTORecord);
        return "expert/review";
    }

    @GetMapping("/reviewForm")
    public String reviewForm(GetReviewReqDTO reqDTO, Model model) {
        log.info("리뷰폼 이동 {}", reqDTO);
        SessionUser sessionUser = redisUtil.getSessionUser();
        GetReviewRespDTO respDTO = reviewService.getReview(reqDTO, sessionUser);
        model.addAttribute("model", respDTO);
        return "client/review-form";
    }


    @PostMapping("/reviewForm")
    public String saveReview(WriteReviewReqDTO reqDTO, Model model) {
        SessionUser sessionUser = redisUtil.getSessionUser();
        reviewService.saveReview(reqDTO, sessionUser);
        return "redirect:client/mypage";
    }


}
