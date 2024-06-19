package com.example.aboutme.user.ResponseDTO.ExpertDetailDTO;

import java.util.List;

public record DetailDTORecord(
        UserRecord user,
        String lowestPrice,
        List<ReviewRecord> reviews,
        List<PRRecord> prs,
        List<SpecRecord> specs
) {}
