package com.example.aboutme.user.UserResponseRecord.ClientMainDTO;

import java.util.List;

public record ClientMainDTORecord(
        List<CommDTORecord> comms,
        List<ExpertDTORecord> experts
) {}

