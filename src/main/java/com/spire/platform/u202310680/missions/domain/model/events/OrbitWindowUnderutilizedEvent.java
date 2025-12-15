package com.spire.platform.u202310680.missions.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class OrbitWindowUnderutilizedEvent extends ApplicationEvent {
    private final Long missionAssignmentId;
    private final String satelliteCode;

    public OrbitWindowUnderutilizedEvent(Object source, Long missionAssignmentId, String satelliteCode) {
        super(source);
        this.missionAssignmentId = missionAssignmentId;
        this.satelliteCode = satelliteCode;
    }
}
