package com.spire.platform.u202310680.monitoring.domain.services;

import com.spire.platform.u202310680.monitoring.domain.model.aggregates.Alert;
import com.spire.platform.u202310680.monitoring.domain.model.commands.CreateAlertCommand;

import java.util.Optional;

public interface AlertCommandService {
    Optional<Alert> handle(CreateAlertCommand command);
}
