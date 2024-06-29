package com.example.aboutme.user.UserRequestRecord;

import com.example.aboutme.user.enums.ExpertLevel;

public record ExpertProfileUpdateReqDTO(
        String expertLevel,
        String profileImage
) {
}
