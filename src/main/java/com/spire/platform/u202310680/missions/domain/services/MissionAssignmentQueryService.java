package com.spire.platform.u202310680.missions.domain.services;

import com.spire.platform.u202310680.missions.domain.model.aggregates.MissionAssignment;
import com.spire.platform.u202310680.missions.domain.model.queries.GetAllMissionAssignmentsQuery;
import com.spire.platform.u202310680.missions.domain.model.queries.GetMissionAssignmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MissionAssignmentQueryService {
    Optional<MissionAssignment> handle(GetMissionAssignmentByIdQuery query);
    List<MissionAssignment> handle(GetAllMissionAssignmentsQuery query);
}
