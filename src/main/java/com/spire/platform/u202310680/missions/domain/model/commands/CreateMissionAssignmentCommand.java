package com.spire.platform.u202310680.missions.domain.model.commands;

import com.spire.platform.u202310680.missions.domain.model.valueobjects.SatelliteCode;

import java.time.LocalDateTime;

public record CreateMissionAssignmentCommand(SatelliteCode satelliteCode,
                                             String orbitClass,
                                             Integer estimatedDuration,
                                             LocalDateTime requestedAt) {
}
