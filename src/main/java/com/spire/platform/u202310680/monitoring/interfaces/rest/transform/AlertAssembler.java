package com.spire.platform.u202310680.monitoring.interfaces.rest.transform;

import com.spire.platform.u202310680.monitoring.domain.model.aggregates.Alert;
import com.spire.platform.u202310680.monitoring.interfaces.rest.resources.AlertResource;

public class AlertAssembler {
    public static AlertResource toResourceFromEntity(Alert entity){
        return new AlertResource(
                entity.getId(),
                entity.getSatelliteCode().satelliteCode(),
                entity.getAlertType().name(),
                entity.getRegisteredAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
