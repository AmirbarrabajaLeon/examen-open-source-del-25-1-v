package com.spire.platform.u202310680.missions.interfaces.rest.resources;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record CreateMissionAssignmentResource(
        @NotBlank(message = "Satellite Code cannot be empty")
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "Must be a valid UUID format")        String satelliteCode,

        @NotBlank
        String orbitClass,

        @NotNull
        @Min(value = 1, message = "Duration must be at least 1 minute") // Fast numerica validation, there is also one at the domain
        Integer estimatedDuration,

        @NotBlank
        LocalDateTime requestedAt
) {
}
