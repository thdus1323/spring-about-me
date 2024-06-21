package com.example.aboutme.user.UserResponseDTO.expertFindDTO;

import java.util.List;

public record FindWrapperRecord(
        List<ExpertInfoRecord> experts
) {}
