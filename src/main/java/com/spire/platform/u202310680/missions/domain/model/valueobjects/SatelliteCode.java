package com.spire.platform.u202310680.missions.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public record SatelliteCode(String satelliteCode) {
    public SatelliteCode {
        // Quick validation if it's null or empty
        if (satelliteCode == null || satelliteCode.isBlank()) {
            throw new IllegalArgumentException("Satellite code cannot be null or empty");
        }
        try {
            // UUID Syntax Validation, just to check if the UUID is a valid one
            UUID.fromString(satelliteCode);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Satellite code must be a valid UUID string");
        }
    }
}
