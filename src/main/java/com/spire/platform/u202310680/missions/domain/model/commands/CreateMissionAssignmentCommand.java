package com.spire.platform.u202310680.missions.domain.model.commands;

import java.time.LocalDateTime;

public record CreateMissionAssignmentCommand(String satelliteCodeRaw,
                                             String orbitClass,
                                             Integer estimatedDuration,
                                             LocalDateTime requestedAt) {
}
