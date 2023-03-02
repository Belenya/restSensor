package com.belenya.restSensor.services.measurement;

import com.belenya.restSensor.models.Measurement;

import java.util.List;

public interface MeasurementService {
    void save(Measurement measurement);
    List<Measurement> findAll();
    Long getRainyDaysCount();
}
