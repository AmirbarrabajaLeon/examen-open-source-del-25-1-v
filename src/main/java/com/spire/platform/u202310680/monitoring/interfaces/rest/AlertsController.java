package com.spire.platform.u202310680.monitoring.interfaces.rest;


import com.spire.platform.u202310680.monitoring.domain.model.queries.GetAllAlertsQuery;
import com.spire.platform.u202310680.monitoring.domain.services.AlertQueryService;
import com.spire.platform.u202310680.monitoring.interfaces.rest.resources.AlertResource;
import com.spire.platform.u202310680.monitoring.interfaces.rest.transform.AlertAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/alerts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlertsController {
    private final AlertQueryService alertQueryService;

    public AlertsController(AlertQueryService alertQueryService){
        this.alertQueryService = alertQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all alerts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerts found (or empty list)")
    })
    public ResponseEntity<List<AlertResource>> getAllAlerts(){
        var query = new GetAllAlertsQuery();

        var alerts = alertQueryService.handle(query);

        var alertResources = alerts.stream()
                .map(AlertAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(alertResources);
    }
}
