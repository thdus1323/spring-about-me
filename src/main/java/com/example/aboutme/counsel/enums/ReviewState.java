package com.example.aboutme.counsel.enums;

public enum ReviewState {
    REVIEW_COMPLETED("작성 완료"),
    REVIEW_PENDING("작성 대기");

    private final String korean;

    ReviewState(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
