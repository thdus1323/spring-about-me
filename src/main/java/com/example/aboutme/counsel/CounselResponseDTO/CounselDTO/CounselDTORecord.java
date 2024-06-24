package com.example.aboutme.counsel.CounselResponseDTO.CounselDTO;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserResponseDTO.expertFindDTO.VoucherImageRecord;

import java.util.List;

public record CounselDTORecord(
        Integer expertId,
        String profileImage,
        List<UserRecord> user
) {}
