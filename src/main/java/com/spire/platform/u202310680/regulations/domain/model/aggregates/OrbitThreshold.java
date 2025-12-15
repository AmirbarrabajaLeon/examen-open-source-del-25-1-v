package com.spire.platform.u202310680.regulations.domain.model.aggregates;

import com.spire.platform.u202310680.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class OrbitThreshold extends AuditableAbstractAggregateRoot<OrbitThreshold> {
    /**
     Integer ID and Auditable Attributes are inherited from AuditableAbstractAggregateRoot
     */
    @Column(nullable = false, unique = true)
    private String orbitClass;

    @Column(nullable = false)
    private Integer maxSafeDuration;

    protected OrbitThreshold(){
        super();
    }

    public OrbitThreshold(String orbitClass, Integer maxSafeDuration){
        /*
        * Fail Fast/Always valid approach
        * */
        if(orbitClass == null || orbitClass.isBlank()){
            throw new IllegalArgumentException("Orbit Class cannot be null or empty.");
        }
        if(maxSafeDuration < 1){
            throw new IllegalArgumentException("Max Safe Duration has to be positive.");
        }
        this.orbitClass = orbitClass;
        this.maxSafeDuration = maxSafeDuration;
    }
}
