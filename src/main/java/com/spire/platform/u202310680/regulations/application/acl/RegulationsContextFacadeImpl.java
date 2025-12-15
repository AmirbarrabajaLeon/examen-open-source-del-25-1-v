package com.spire.platform.u202310680.regulations.application.acl;

import com.spire.platform.u202310680.regulations.domain.model.aggregates.OrbitThreshold;
import com.spire.platform.u202310680.regulations.domain.model.queries.GetOrbitThresholdByOrbitClassQuery;
import com.spire.platform.u202310680.regulations.domain.services.OrbitThresholdQueryService;
import com.spire.platform.u202310680.regulations.interfaces.acl.RegulationsContextFacade;
import org.springframework.stereotype.Service;

@Service
public class RegulationsContextFacadeImpl implements RegulationsContextFacade {
    private final OrbitThresholdQueryService orbitThresholdQueryService;

    public RegulationsContextFacadeImpl(OrbitThresholdQueryService orbitThresholdQueryService){
        this.orbitThresholdQueryService = orbitThresholdQueryService;
    }

    @Override
    public Integer fetchMaxSafeDurationByOrbitClass(String orbitClass){
        // Internal query check
        var query = new GetOrbitThresholdByOrbitClassQuery(orbitClass);

        var threshold = orbitThresholdQueryService.handle(query);

        return threshold.map(OrbitThreshold::getMaxSafeDuration).orElse(0);
    }
}
