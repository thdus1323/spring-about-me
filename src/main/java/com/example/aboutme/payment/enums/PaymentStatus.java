package com.example.aboutme.payment.enums;

public enum PaymentStatus {
    COMPLETED("결제 완료"), // 결제 완료
    PENDING("결제 대기"), // 결제 대기
    FAILED("결제 실패"), // 결제 실패
        REFUNDED("결제 취소"); // 환불

    private final String korean;

    PaymentStatus(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
