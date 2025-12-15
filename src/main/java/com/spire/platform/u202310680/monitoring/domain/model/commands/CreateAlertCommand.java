package com.spire.platform.u202310680.monitoring.domain.model.commands;

import com.spire.platform.u202310680.monitoring.domain.model.valueobjects.AlertType;

public record CreateAlertCommand(String satelliteCodeRaw, AlertType alertType) {
}
