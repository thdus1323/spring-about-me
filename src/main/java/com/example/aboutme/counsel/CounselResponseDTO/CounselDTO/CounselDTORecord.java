package com.example.aboutme.counsel.CounselResponseDTO.CounselDTO;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.user.User;

import java.util.List;

public record CounselDTORecord(
        List<UserRecord> user
) {}
