package com.spire.platform.u202310680.missions.infrastructure.persistence.jpa.repositories;

import com.spire.platform.u202310680.missions.domain.model.aggregates.MissionAssignment;
import com.spire.platform.u202310680.missions.domain.model.valueobjects.SatelliteCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MissionAssignmentRepository extends JpaRepository<MissionAssignment, Long> {
    boolean existsBySatelliteCodeAndRequestedAtBetween(SatelliteCode satelliteCode, LocalDateTime start, LocalDateTime end);
}
