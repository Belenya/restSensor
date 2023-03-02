package com.belenya.restSensor.services.measurement;

import com.belenya.restSensor.models.Measurement;
import com.belenya.restSensor.repositories.MeasurementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService{

    private final MeasurementsRepository measurementsRepository;

    @Override
    @Transactional
    public void save(Measurement measurement) {
        measurementsRepository.save(measurement);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Override
    public Long getRainyDaysCount() {
        return measurementsRepository.countAllByRainingIsTrue();
    }
}
