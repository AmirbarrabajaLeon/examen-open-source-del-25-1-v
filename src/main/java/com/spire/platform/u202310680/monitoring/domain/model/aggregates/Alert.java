package com.spire.platform.u202310680.monitoring.domain.model.aggregates;

import com.spire.platform.u202310680.monitoring.domain.model.valueobjects.AlertType;
import com.spire.platform.u202310680.monitoring.domain.model.valueobjects.SatelliteCode;
import com.spire.platform.u202310680.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Alert extends AuditableAbstractAggregateRoot<Alert> {
    @Embedded
    @AttributeOverride(name = "satelliteCode", column = @Column(name = "satellite_code", nullable = false))
    private SatelliteCode satelliteCode;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private AlertType alertType;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    protected Alert(){
        super();
    }

    public Alert(SatelliteCode satelliteCode, AlertType alertType){

        if(satelliteCode == null){
           throw new IllegalArgumentException("Satellite Code reference cannot be null");
        }

        this.satelliteCode = satelliteCode;
        this.alertType = alertType;
        this.registeredAt = LocalDateTime.now();
    }
}
