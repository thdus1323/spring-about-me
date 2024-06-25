package com.example.aboutme.counsel.enums;

public enum StateEnum {
    COMPLETED("상담완료"), // 상담 완료
    PENDING("상담대기"), // 상담 대기
    REFUNDED("환불"); // 환불

    private final String korean;

    StateEnum(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
