package com.belenya.restSensor.repositories;

import com.belenya.restSensor.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurement, Long> {
    Long countAllByRainingIsTrue();
}
