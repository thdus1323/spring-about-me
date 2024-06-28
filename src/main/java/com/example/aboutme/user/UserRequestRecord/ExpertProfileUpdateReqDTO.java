package com.example.aboutme.user.UserRequestRecord;

import com.example.aboutme.user.enums.ExpertLevel;

public record ExpertProfileUpdateReqDTO(
        String username, // 닉네임
        ExpertLevel expertLevel,
        String profileImage
) {
}
