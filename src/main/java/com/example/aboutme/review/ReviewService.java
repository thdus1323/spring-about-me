package com.example.aboutme.review;

import com.example.aboutme._core.error.exception.Exception400;
import com.example.aboutme._core.error.exception.Exception403;
import com.example.aboutme._core.error.exception.Exception404;
import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.review.ReviewReqRecord.GetReviewReqDTO;
import com.example.aboutme.review.ReviewRespRecord.ExpertReviewDTO.ReviewDTORecord;
import com.example.aboutme.review.ReviewRespRecord.ExpertReviewDTO.ReviewRecord;
import com.example.aboutme.review.ReviewRespRecord.ExpertReviewDTO.UserRecord;
import com.example.aboutme.review.ReviewRespRecord.GetReviewRespDTO;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CounselRepository counselRepository;

    @Transactional
    public ReviewDTORecord getExpertReview(SessionUser sessionUser) {
        // 0. 인증
        if (sessionUser == null) {
            throw new Exception403("인증되지 않은 유저입니다");
        }
        // 1.리뷰 리스트 찾기
        User client = userRepository.findById(sessionUser.getId()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다"));

        List<ReviewRecord> reviews = reviewRepository.findByExpertId(client.getId()).stream().map(review -> {
            // 2. 유저정보 넣기
            UserRecord users = new UserRecord(review.getClient().getId(), review.getClient().getName(), review.getClient().getProfileImage());
            return new ReviewRecord(review.getId(), review.getContent(), users);

        }).toList();

        return new ReviewDTORecord(reviews);
    }

    @Transactional
    public GetReviewRespDTO 리뷰조회하기(GetReviewReqDTO reqDTO, SessionUser sessionUser) {
        Counsel counsel = counselRepository.findById(reqDTO.counselId()).orElseThrow(() -> new Exception404("상담이력을 찾을 수 없습니다."));
        User expert = userRepository.findById(reqDTO.expertId())
                .orElseThrow(() -> new Exception400("전문가를 찾을 수 없습니다."));

        return  GetReviewRespDTO.builder()
                .profileImage(expert.getProfileImage())
                .expertName(expert.getName())
                .voucherType(counsel.getVoucher().getVoucherType().getKorean())
                .counselDate(counsel.getCounselDate())
                .expertId(expert.getId())
                .clientId(sessionUser.getId())
                .counselId(counsel.getId())
                .build();
    }
}
