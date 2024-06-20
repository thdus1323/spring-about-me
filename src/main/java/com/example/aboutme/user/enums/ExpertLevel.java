package com.example.aboutme.user.enums;

public enum ExpertLevel {
    LEVEL1("1급 심리상담사"), // 1급
    LEVEL2("2급 심리상담사"); // 2급

    private final String korean;

    ExpertLevel(String korean) {
        this.korean = korean;
    }
    @Override
    public String toString() {
        return korean;
    }
    public String getKorean() {
        return korean;
    }
}
