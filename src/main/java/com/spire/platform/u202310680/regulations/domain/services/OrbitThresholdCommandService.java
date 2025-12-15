package com.spire.platform.u202310680.regulations.domain.services;

import com.spire.platform.u202310680.regulations.domain.model.aggregates.OrbitThreshold;
import com.spire.platform.u202310680.regulations.domain.model.commands.CreateOrbitThresholdCommand;

import java.util.Optional;

public interface OrbitThresholdCommandService {
    Optional<OrbitThreshold> handle(CreateOrbitThresholdCommand command);
}
