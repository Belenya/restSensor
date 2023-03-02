package com.belenya.restSensor.services.sensor;

import com.belenya.restSensor.models.Sensor;

import java.util.Optional;

public interface SensorService {
    void register(Sensor sensor);
    Optional<Sensor> findByName(String name);
}
