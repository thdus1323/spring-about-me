package com.example.aboutme.payment.enums;

import com.example.aboutme._core.error.exception.Exception400;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentMethods {
    CREDIT_CARD("카드");

    // 결제 완료
    private final String korean;

    PaymentMethods(String korean) {
        this.korean = korean;
    }

    @JsonCreator
    public static PaymentMethods fromKorean(String korean) {
        for (PaymentMethods paymentMethods : PaymentMethods.values()) {
            if (paymentMethods.korean.equals(korean)) {
                return paymentMethods;
            }
        }
        throw new Exception400("올바르지 않은 결제요청입니다." + korean);
    }

    public String getKorean() {
        return korean;
    }
}