package com.spire.platform.u202310680.missions.interfaces.rest.transform;

import com.spire.platform.u202310680.missions.domain.model.aggregates.MissionAssignment;
import com.spire.platform.u202310680.missions.domain.model.commands.CreateMissionAssignmentCommand;
import com.spire.platform.u202310680.missions.interfaces.rest.resources.CreateMissionAssignmentResource;
import com.spire.platform.u202310680.missions.interfaces.rest.resources.MissionAssignmentResource;

public class MissionAssignmentAssembler {
    public static CreateMissionAssignmentCommand toCommandFromResource(CreateMissionAssignmentResource resource){
        return new CreateMissionAssignmentCommand(
                resource.satelliteCode(),
                resource.orbitClass(),
                resource.estimatedDuration(),
                resource.requestedAt()
        );
    }

    public static MissionAssignmentResource toResourceFromEntity(MissionAssignment entity){
        return new MissionAssignmentResource(
                entity.getId(),
                entity.getSatelliteCode().satelliteCode(),
                entity.getOrbitClass(),
                entity.getEstimatedDuration(),
                entity.getStatus().name(),
                entity.getRequestedAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
