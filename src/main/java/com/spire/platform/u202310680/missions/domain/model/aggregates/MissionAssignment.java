package com.spire.platform.u202310680.missions.domain.model.aggregates;

import com.spire.platform.u202310680.missions.domain.model.events.OrbitWindowUnderutilizedEvent;
import com.spire.platform.u202310680.missions.domain.model.valueobjects.MissionAssignmentStatus;
import com.spire.platform.u202310680.missions.domain.model.valueobjects.SatelliteCode;
import com.spire.platform.u202310680.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class MissionAssignment extends AuditableAbstractAggregateRoot<MissionAssignment> {
    /**
     Integer ID and Auditable Attributes are inherited from AuditableAbstractAggregateRoot
     */
    @Embedded
    @AttributeOverride(name = "satelliteCode", column = @Column(name = "satellite_code", nullable = false))
    private SatelliteCode satelliteCode;

    @Column(nullable = false)
    private String orbitClass;

    @Column(nullable = false)
    private Integer estimatedDuration;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private MissionAssignmentStatus status;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    protected MissionAssignment(){
        super();
        this.status = MissionAssignmentStatus.SCHEDULED;
    }

    public MissionAssignment(SatelliteCode satelliteCode, String orbitClass, Integer estimatedDuration, LocalDateTime requestedAt){
        this();
        /*
         * Fail-fast or in other word just quick validations
         * could we also say the always valid approach
         */
        if(satelliteCode == null){
            throw new IllegalArgumentException("Satellite Code cannot be null reference");
        }

        if(orbitClass == null || orbitClass.isBlank()){
            throw new IllegalArgumentException("Orbit Class cannot be null or empty");
        }

        if(estimatedDuration < 1){
            throw new IllegalArgumentException("Estimated duration has to be positive");
        }

        if(requestedAt.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Requested date cannot be in the future");
        }

        this.satelliteCode = satelliteCode;
        this.orbitClass = orbitClass;
        this.estimatedDuration = estimatedDuration;
        this.requestedAt = requestedAt;
    }

    public void evaluateOrbitalEfficiency(Integer maxSafeDuration){
        double threshold = maxSafeDuration * 0.20;
        if(this.estimatedDuration < threshold){
            this.registerEvent(new OrbitWindowUnderutilizedEvent(
                    this,
                    this.getId(),
                    this.satelliteCode.satelliteCode()
            ));
        }
    }
}
