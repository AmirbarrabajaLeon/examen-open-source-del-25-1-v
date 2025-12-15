package com.spire.platform.u202310680.monitoring.application.internal.eventhandlers;

import com.spire.platform.u202310680.missions.domain.model.events.OrbitWindowUnderutilizedEvent;
import com.spire.platform.u202310680.monitoring.domain.model.commands.CreateAlertCommand;
import com.spire.platform.u202310680.monitoring.domain.model.valueobjects.AlertType;
import com.spire.platform.u202310680.monitoring.domain.services.AlertCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MonitoringEventHandler {
    private final AlertCommandService alertCommandService;

    public MonitoringEventHandler(AlertCommandService alertCommandService){
        this.alertCommandService = alertCommandService;
    }

    @EventListener
    public void on(OrbitWindowUnderutilizedEvent event){

        var command = new CreateAlertCommand(event.getSatelliteCode(), AlertType.SYSTEM_ERROR);

        alertCommandService.handle(command);
    }
}
