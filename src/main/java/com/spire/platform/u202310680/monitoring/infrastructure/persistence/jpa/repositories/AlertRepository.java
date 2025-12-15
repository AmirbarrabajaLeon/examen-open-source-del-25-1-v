package com.spire.platform.u202310680.monitoring.infrastructure.persistence.jpa.repositories;

import com.spire.platform.u202310680.monitoring.domain.model.aggregates.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

}
