package com.spire.platform.u202310680.missions.application.internal.queryservices;

import com.spire.platform.u202310680.missions.domain.model.aggregates.MissionAssignment;
import com.spire.platform.u202310680.missions.domain.model.queries.GetAllMissionAssignmentsQuery;
import com.spire.platform.u202310680.missions.domain.model.queries.GetMissionAssignmentByIdQuery;
import com.spire.platform.u202310680.missions.domain.services.MissionAssignmentQueryService;
import com.spire.platform.u202310680.missions.infrastructure.persistence.jpa.repositories.MissionAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionAssignmentQueryServiceImpl implements MissionAssignmentQueryService {
    private final MissionAssignmentRepository missionAssignmentRepository;

    public MissionAssignmentQueryServiceImpl(MissionAssignmentRepository missionAssignmentRepository){
        this.missionAssignmentRepository = missionAssignmentRepository;
    }

    @Override
    public Optional<MissionAssignment> handle(GetMissionAssignmentByIdQuery query){
        return missionAssignmentRepository.findById(query.id());
    }

    @Override
    public List<MissionAssignment> handle(GetAllMissionAssignmentsQuery query){
        return missionAssignmentRepository.findAll();
    }
}
