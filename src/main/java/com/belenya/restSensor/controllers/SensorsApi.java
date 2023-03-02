package com.belenya.restSensor.controllers;

import com.belenya.restSensor.dto.SensorDto;
import com.belenya.restSensor.models.Sensor;
import com.belenya.restSensor.services.sensor.SensorService;
import com.belenya.restSensor.utils.SensorValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorsApi {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDto sensorDto, BindingResult bindingResult) {
        sensorValidator.validate(sensorDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        sensorService.register(sensorDtoToSensor(sensorDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Sensor sensorDtoToSensor(SensorDto sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }
}
