package com.example.aboutme.user.enums;

import com.example.aboutme._core.error.exception.Exception400;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MAN("남자"),
    WOMAN("여자"),
    OTHER("기타");

    private final String korean;

    Gender(String korean) {
        this.korean = korean;
    }

    @JsonValue
    public String getKorean() {
        return korean;
    }

    @JsonCreator
    public static Gender fromKorean(String korean) {
        for (Gender gender : Gender.values()) {
            if (gender.korean.equals(korean)) {
                return gender;
            }
        }
        throw new Exception400("올바르지 않는 성별입니다." + korean);
    }
}
