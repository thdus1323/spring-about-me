package com.example.aboutme.counsel.enums;

import lombok.Getter;

@Getter
public enum CounselStatus {
    COMPLETED("상담완료"), // 상담 완료
    CONFIRMED("상담확정"), // 상담 완료
    PENDING("상담대기"), // 상담 대기
    REFUNDED("환불"); // 환불

    private final String korean;

    CounselStatus(String korean) {
        this.korean = korean;
    }

}
