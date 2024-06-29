package com.example.aboutme.user.UserRequestRecord;

public record UserProfileUpdateReqDTO(
        String name, // 닉네임
//        String birthYear,
        String gender, // 한글로 받기
        String profileImage
) {
}
