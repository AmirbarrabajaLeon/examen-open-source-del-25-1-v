package com.spire.platform.u202310680.missions.interfaces.rest;

import com.spire.platform.u202310680.missions.domain.services.MissionAssignmentCommandService;
import com.spire.platform.u202310680.missions.domain.services.MissionAssignmentQueryService;
import com.spire.platform.u202310680.missions.interfaces.rest.resources.CreateMissionAssignmentResource;
import com.spire.platform.u202310680.missions.interfaces.rest.resources.MissionAssignmentResource;
import com.spire.platform.u202310680.missions.interfaces.rest.transform.MissionAssignmentAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/mission-assignments", produces = MediaType.APPLICATION_JSON_VALUE)
public class MissionAssignmentsController {
    private final MissionAssignmentCommandService missionAssignmentCommandService;
    private final MissionAssignmentQueryService missionAssignmentQueryService;

    public MissionAssignmentsController(MissionAssignmentCommandService missionAssignmentCommandService, MissionAssignmentQueryService missionAssignmentQueryService){
        this.missionAssignmentCommandService = missionAssignmentCommandService;
        this.missionAssignmentQueryService = missionAssignmentQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new mission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mission created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MissionAssignmentResource> createMissionAssignment(@Valid @RequestBody CreateMissionAssignmentResource resource){
        var command = MissionAssignmentAssembler.toCommandFromResource(resource);

        var missionAssignment = missionAssignmentCommandService.handle(command);

        if (missionAssignment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var missionResource = MissionAssignmentAssembler.toResourceFromEntity(missionAssignment.get());
        return new ResponseEntity<>(missionResource, HttpStatus.CREATED);
    }
}
