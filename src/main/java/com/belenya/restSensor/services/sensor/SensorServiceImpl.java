package com.belenya.restSensor.services.sensor;

import com.belenya.restSensor.models.Sensor;
import com.belenya.restSensor.repositories.SensorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorsRepository sensorsRepository;

    @Override
    @Transactional
    public void register(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

    @Override
    public Optional<Sensor> findByName(String name) {
        return sensorsRepository.findByName(name);
    }
}
