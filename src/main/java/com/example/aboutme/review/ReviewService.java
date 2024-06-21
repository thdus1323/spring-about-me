package com.example.aboutme.review;

import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme.review.ReviewResponseDTO.ExpertReviewDTO.ReviewDTORecord;
import com.example.aboutme.review.ReviewResponseDTO.ExpertReviewDTO.ReviewRecord;
import com.example.aboutme.review.ReviewResponseDTO.ExpertReviewDTO.UserRecord;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import com.example.aboutme.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewDTORecord getExpertReview(Integer expertId,User sessionUser){
        // 0. 인증
        if (sessionUser == null){
            throw new Exception403("인증되지 않은 유저입니다");
        }
        // 1.리뷰 리스트 찾기
        User user = userRepository.findById(expertId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다"));

        List<ReviewRecord> reviews = reviewRepository.findByExpertId(user.getId()).stream().map(review -> {
            // 2. 유저정보 넣기
            UserRecord users = new UserRecord(review.getUser().getId(),review.getUser().getName(),review.getUser().getProfileImage());
            return new ReviewRecord(review.getId(),review.getContent(),users);
        }).toList();

        return new ReviewDTORecord(reviews);
    }

}
