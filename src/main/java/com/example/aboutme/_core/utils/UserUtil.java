package com.example.aboutme._core.utils;

public class UserUtil {
    private static final String DEFAULT_PROFILE_IMAGE = "/images/badge.png";

    public static String getDefaultProfileImage() {
        return DEFAULT_PROFILE_IMAGE;
    }

    public static String getProfileImage(String profileImage) {
        return (profileImage != null && !profileImage.isEmpty()) ? profileImage : DEFAULT_PROFILE_IMAGE;
    }
}
