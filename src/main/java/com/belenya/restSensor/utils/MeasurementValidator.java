package com.belenya.restSensor.utils;

import com.belenya.restSensor.dto.MeasurementDto;
import com.belenya.restSensor.repositories.SensorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {

    private final SensorsRepository sensorsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDto measurementDto = (MeasurementDto) target;
        if (!sensorsRepository.existsByName(measurementDto.getSensor().getName())) {
            errors.rejectValue("sensor", "", "Sensor not exists.");
        }
    }
}
