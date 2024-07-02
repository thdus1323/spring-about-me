package com.example.aboutme.counsel.enums;


public enum ReservationStatus {
    RESERVATION_PENDING("예약 대기"),
    RESERVATION_SCHEDULED("확정 예정"), //
    RESERVATION_COMPLETED("예약 확정"), //
    RESERVATION_CANCELLED("예약 취소"),
    COUNSEL_COMPLETED("상담 완료");

    private final String korean;

    ReservationStatus(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}

