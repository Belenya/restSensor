package com.belenya.restSensor.repositories;

import com.belenya.restSensor.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorsRepository extends JpaRepository<Sensor, Long> {
    boolean existsByName(String name);
    Optional<Sensor> findByName(String name);
}
