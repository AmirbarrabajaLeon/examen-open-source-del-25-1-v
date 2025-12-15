package com.spire.platform.u202310680.regulations.application.internal.eventhandlers;

import com.spire.platform.u202310680.regulations.domain.model.commands.CreateOrbitThresholdCommand;
import com.spire.platform.u202310680.regulations.domain.services.OrbitThresholdCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RegulationsApplicationReadyEventHandler {

    private final OrbitThresholdCommandService orbitThresholdCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegulationsApplicationReadyEventHandler.class);

    public RegulationsApplicationReadyEventHandler(OrbitThresholdCommandService orbitThresholdCommandService){
        this.orbitThresholdCommandService = orbitThresholdCommandService;
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if orbit thresholds seeding is needed for {} at {}", applicationName, currentTimestamp());

        // Datos requeridos por el enunciado
        registerOrbitThreshold("LEO", 300);
        registerOrbitThreshold("MEO", 450);
        registerOrbitThreshold("GEO", 600);

        LOGGER.info("Orbit thresholds seeding verification finished.");
    }

    private void registerOrbitThreshold(String orbitClass, Integer maxSafeDuration) {
        var command = new CreateOrbitThresholdCommand(orbitClass, maxSafeDuration);
        var result = orbitThresholdCommandService.handle(command);

        if (result.isPresent()) {
            LOGGER.info("Seeded Orbit Threshold: {} with max duration {}", orbitClass, maxSafeDuration);
        } else {
            LOGGER.info("Orbit Threshold {} already exists. Skipping.", orbitClass);
        }
    }
}
