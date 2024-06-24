package com.example.aboutme.user.UserRequestRecord;

import com.example.aboutme.user.enums.Gender;

public record UserProfileUpdateReqDTO(
        String username, // 닉네임
        String birthYear,
        String gender, // 한글로 받기
        String profileImage
) {
}
