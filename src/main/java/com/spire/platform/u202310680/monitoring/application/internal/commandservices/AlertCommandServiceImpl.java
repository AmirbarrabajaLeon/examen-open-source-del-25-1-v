package com.spire.platform.u202310680.monitoring.application.internal.commandservices;

import com.spire.platform.u202310680.monitoring.domain.model.aggregates.Alert;
import com.spire.platform.u202310680.monitoring.domain.model.commands.CreateAlertCommand;
import com.spire.platform.u202310680.monitoring.domain.model.valueobjects.SatelliteCode;
import com.spire.platform.u202310680.monitoring.domain.services.AlertCommandService;
import com.spire.platform.u202310680.monitoring.infrastructure.persistence.jpa.repositories.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertCommandServiceImpl implements AlertCommandService {
    private final AlertRepository alertRepository;

    public AlertCommandServiceImpl(AlertRepository alertRepository){
        this.alertRepository = alertRepository;
    }

    @Override
    public Optional<Alert> handle(CreateAlertCommand command){

        //Transform primitives into value objects
        var satelliteCode = new SatelliteCode(command.satelliteCodeRaw());

        var alert = new Alert(satelliteCode, command.alertType());

        alertRepository.save(alert);

        return Optional.of(alert);
    }
}
