package com.spire.platform.u202310680.missions.application.internal.outboundservices.acl;

import com.spire.platform.u202310680.regulations.interfaces.acl.RegulationsContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalRegulationsService {
    private final RegulationsContextFacade regulationsContextFacade;

    public ExternalRegulationsService(RegulationsContextFacade regulationsContextFacade){
        this.regulationsContextFacade = regulationsContextFacade;
    }

    public Optional<Integer> fetchMaxSafeDurationByOrbitClass(String orbitClass) {
        Integer duration = regulationsContextFacade.fetchMaxSafeDurationByOrbitClass(orbitClass);

        if(duration == null || duration == 0){
            return Optional.empty();
        }

        return Optional.of(duration);
    }
}
