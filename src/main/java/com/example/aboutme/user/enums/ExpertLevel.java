package com.example.aboutme.user.enums;

import com.example.aboutme._core.error.exception.Exception400;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    public String getKorean() {
        return korean;
    }

    @JsonCreator
    public static ExpertLevel fromKorean(String korean) {
        for (ExpertLevel expertLevel : ExpertLevel.values()) {
            if (expertLevel.korean.equals(korean)) {
                return expertLevel;
            }
        }
        throw new Exception400("올바르지 않은 등급입니다." + korean);
    }
}
