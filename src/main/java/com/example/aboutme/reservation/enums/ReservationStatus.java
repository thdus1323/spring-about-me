package com.example.aboutme.reservation.enums;


public enum ReservationStatus {
    PENDING("예약 대기"),
    SCHEDULED("확정 예정"),
    COMPLETED("예약 확정"),
    CANCELLED("예약 취소");

    private final String korean;

    ReservationStatus(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}

