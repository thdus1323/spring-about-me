package com.example.aboutme.user.UserResponseDTO.ClientMainDTO;

import java.util.List;

public record ClientMainDTORecord(
        List<CommDTORecord> comms,
        List<ExpertDTORecord> experts
) {}

