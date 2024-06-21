package com.example.aboutme._core.utils;

public class UserDefault {
    private static final String DEFAULT_EXPERT_TITLE = "당신의 길에 함께하는 동반자가 되고 싶습니다.";

    public static String getDefaultExpertTitle() {
        return DEFAULT_EXPERT_TITLE;
    }

    public static String getExpertTitle(String expertTitle) {
        return (expertTitle != null && !expertTitle.isEmpty()) ? expertTitle : DEFAULT_EXPERT_TITLE;
    }
}
