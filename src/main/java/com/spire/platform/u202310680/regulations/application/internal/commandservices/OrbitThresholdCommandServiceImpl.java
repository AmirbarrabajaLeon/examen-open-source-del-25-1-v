package com.spire.platform.u202310680.regulations.application.internal.commandservices;

import com.spire.platform.u202310680.regulations.domain.model.aggregates.OrbitThreshold;
import com.spire.platform.u202310680.regulations.domain.model.commands.CreateOrbitThresholdCommand;
import com.spire.platform.u202310680.regulations.domain.services.OrbitThresholdCommandService;
import com.spire.platform.u202310680.regulations.infrastructure.persistence.jpa.repositories.OrbitThresholdRepository;

import java.util.Optional;

public class OrbitThresholdCommandServiceImpl implements OrbitThresholdCommandService {
    // Let's define our repository
    private final OrbitThresholdRepository orbitThresholdRepository;

    // Constructor
    public OrbitThresholdCommandServiceImpl(OrbitThresholdRepository orbitThresholdRepository){
        this.orbitThresholdRepository = orbitThresholdRepository;
    }

    // Implementations
    @Override
    public Optional<OrbitThreshold> handle(CreateOrbitThresholdCommand command){
        if(orbitThresholdRepository.findByOrbitClass(command.orbitClass()).isPresent()){
            return Optional.empty();
        }

        var orbitThreshold = new OrbitThreshold(command.orbitClass(), command.maxSafeDuration());

        orbitThresholdRepository.save(orbitThreshold);
        return Optional.of(orbitThreshold);
    }
}
