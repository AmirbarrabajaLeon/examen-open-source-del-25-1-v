package com.spire.platform.u202310680.regulations.application.internal.queryservices;

import com.spire.platform.u202310680.regulations.domain.model.aggregates.OrbitThreshold;
import com.spire.platform.u202310680.regulations.domain.model.queries.GetAllOrbitThresholdsQuery;
import com.spire.platform.u202310680.regulations.domain.model.queries.GetOrbitThresholdByIdQuery;
import com.spire.platform.u202310680.regulations.domain.model.queries.GetOrbitThresholdByOrbitClassQuery;
import com.spire.platform.u202310680.regulations.domain.services.OrbitThresholdQueryService;
import com.spire.platform.u202310680.regulations.infrastructure.persistence.jpa.repositories.OrbitThresholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrbitThresholdQueryServiceImpl implements OrbitThresholdQueryService {
    private final OrbitThresholdRepository orbitThresholdRepository;

    // Constructor
    public OrbitThresholdQueryServiceImpl(OrbitThresholdRepository orbitThresholdRepository){
        this.orbitThresholdRepository = orbitThresholdRepository;
    }

    // Implementations
    @Override
    public Optional<OrbitThreshold> handle(GetOrbitThresholdByIdQuery query){
        return orbitThresholdRepository.findById(query.id());
    }

    @Override
    public List<OrbitThreshold> handle(GetAllOrbitThresholdsQuery query){
        return orbitThresholdRepository.findAll();
    }

    @Override
    public Optional<OrbitThreshold> handle(GetOrbitThresholdByOrbitClassQuery query){
        return orbitThresholdRepository.findByOrbitClass(query.orbitClass());
    }
}
