package com.belenya.restSensor.utils;

import com.belenya.restSensor.dto.SensorDto;
import com.belenya.restSensor.repositories.SensorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {

    private final SensorsRepository sensorsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDto sensor = (SensorDto) target;
        if (sensorsRepository.existsByName(sensor.getName())) {
            errors.rejectValue("name", "", "Already exists");
        }
    }
}
