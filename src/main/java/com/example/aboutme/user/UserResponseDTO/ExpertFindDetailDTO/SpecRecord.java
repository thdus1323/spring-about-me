package com.example.aboutme.user.UserResponseDTO.ExpertFindDetailDTO;


import com.example.aboutme.user.enums.SpecType;

public record SpecRecord(
        Integer userId,
        SpecType specType,
        String details
) {}

