package com.example.aboutme._core.utils;

import com.example.aboutme.user.User;

public class NullHandlerUtil {

    // User 기본값 설정
    public static User handleNulls(User user) {
        if (user.getProfileImage() == null || user.getProfileImage().isEmpty()) {
            user.setProfileImage("/images/badge.png");
        }
        if (user.getExpertTitle() == null || user.getExpertTitle().isEmpty()) {
            user.setExpertTitle("당신의 길에 함께하는 동반자가 되고 싶습니다.");
        }
        // Add more fields as needed
        return user;
    }


}
