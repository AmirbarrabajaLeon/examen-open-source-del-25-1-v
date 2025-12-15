package com.spire.platform.u202310680.missions.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.Date;

public record MissionAssignmentResource(
        Long id,
        String satelliteCode,
        String orbitClass,
        Integer estimatedDuration,
        String status,
        LocalDateTime requestedAt,
        Date createdAt,
        Date updatedAt
) {
}
