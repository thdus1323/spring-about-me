package com.example.aboutme.schedule.enums;

public enum RestType {
    PERIODIC("주기적인"),
    WEEKLY("주간"),
    TIME_SPECIFIC("특정 시간"),
    DAY_SPECIFIC("특정 일");

    private final String korean;

    RestType(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}