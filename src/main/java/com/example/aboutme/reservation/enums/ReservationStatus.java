package com.example.aboutme.reservation.enums;


public enum ReservationStatus {
    SCHEDULED("예약 예정"),
    COMPLETED("예약 완료"),
    CANCELLED("예약 취소");

    private final String korean;

    ReservationStatus(String korean) {
        this.korean = korean;
    }
    public String getKorean() {
        return korean;
    }
}

