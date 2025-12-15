package com.spire.platform.u202310680.missions.application.internal.commandservices;

import com.spire.platform.u202310680.missions.application.internal.outboundservices.acl.ExternalRegulationsService;
import com.spire.platform.u202310680.missions.domain.model.aggregates.MissionAssignment;
import com.spire.platform.u202310680.missions.domain.model.commands.CreateMissionAssignmentCommand;
import com.spire.platform.u202310680.missions.domain.model.valueobjects.SatelliteCode;
import com.spire.platform.u202310680.missions.domain.services.MissionAssignmentCommandService;
import com.spire.platform.u202310680.missions.infrastructure.persistence.jpa.repositories.MissionAssignmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class MissionAssignmentCommandServiceImpl implements MissionAssignmentCommandService {
    private final MissionAssignmentRepository missionAssignmentRepository;
    private final ExternalRegulationsService externalRegulationsService;

    public MissionAssignmentCommandServiceImpl(MissionAssignmentRepository missionAssignmentRepository, ExternalRegulationsService externalRegulationsService){
        this.missionAssignmentRepository = missionAssignmentRepository;
        this.externalRegulationsService = externalRegulationsService;
    }

    @Override
    public Optional<MissionAssignment> handle(CreateMissionAssignmentCommand command){
        // As a first step, we are gonna convert our raw data into value objects
        var satelliteCode = new SatelliteCode(command.satelliteCodeRaw());

        // As a second step, our repository has a validation, so we must use it
        var requestDate = command.requestedAt().toLocalDate();
        var startOfDay = requestDate.atStartOfDay();
        var endOfDay = requestDate.atTime(LocalTime.MAX);

        if(missionAssignmentRepository.existsBySatelliteCodeAndRequestedAtBetween(satelliteCode, startOfDay, endOfDay)){
            throw new IllegalArgumentException("Mission assignment already exists for this satellite on the requested date");
        }

        // VALIDATE IF THE ORBIT CLASS FROM THE INPUT EXISTS IN THE TABLE
        var maxSafeDurationOpt = externalRegulationsService.fetchMaxSafeDurationByOrbitClass(command.orbitClass());

        if (maxSafeDurationOpt.isEmpty()) {
            throw new IllegalArgumentException("Orbit Class '" + command.orbitClass() + "' is not regulated or does not exist.");
        }
        var missionAssignment = new MissionAssignment(
                satelliteCode,
                command.orbitClass(),
                command.estimatedDuration(),
                command.requestedAt());

        // Event
        var maxSafeDuration = maxSafeDurationOpt.get();
        missionAssignment.evaluateOrbitalEfficiency(maxSafeDuration);

        /*different interesting way of doing it*/
        /*externalRegulationsService.fetchMaxSafeDurationByOrbitClass(command.orbitClass())
                .ifPresent(missionAssignment::evaluateOrbitalEfficiency);
                            // maxSafeDuration -> {
                            //missionAssignment.evaluateOrbitalEfficiency(maxSafeDuration);*/

        missionAssignmentRepository.save(missionAssignment);
        return Optional.of(missionAssignment);
    }
}
