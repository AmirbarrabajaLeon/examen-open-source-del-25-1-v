package com.spire.platform.u202310680.regulations.domain.services;

import com.spire.platform.u202310680.regulations.domain.model.aggregates.OrbitThreshold;
import com.spire.platform.u202310680.regulations.domain.model.queries.GetAllOrbitThresholdsQuery;
import com.spire.platform.u202310680.regulations.domain.model.queries.GetOrbitThresholdByIdQuery;
import com.spire.platform.u202310680.regulations.domain.model.queries.GetOrbitThresholdByOrbitClassQuery;

import java.util.List;
import java.util.Optional;

public interface OrbitThresholdQueryService {
    Optional<OrbitThreshold> handle(GetOrbitThresholdByIdQuery query);
    List<OrbitThreshold> handle(GetAllOrbitThresholdsQuery query);
    Optional<OrbitThreshold> handle(GetOrbitThresholdByOrbitClassQuery query);
}
