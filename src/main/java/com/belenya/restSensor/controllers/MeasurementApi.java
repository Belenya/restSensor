package com.belenya.restSensor.controllers;

import com.belenya.restSensor.dto.MeasurementDto;
import com.belenya.restSensor.dto.SensorDto;
import com.belenya.restSensor.models.Measurement;
import com.belenya.restSensor.models.Sensor;
import com.belenya.restSensor.services.measurement.MeasurementService;
import com.belenya.restSensor.services.sensor.SensorService;
import com.belenya.restSensor.utils.MeasurementValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementApi {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final SensorService sensorService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDto measurementDto, BindingResult bindingResult) {
        measurementValidator.validate(measurementDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Measurement measurement = measurementDtoToMeasurement(measurementDto);
        Optional<Sensor> sensor = sensorService.findByName(measurement.getSensor().getName());
        sensor.ifPresent(measurement::setSensor);
        measurementService.save(enrichMeasurement(measurement));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementDto> findAll() {
        return measurementService.findAll()
                .stream()
                .map(this::measurementToMeasurementDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    private Measurement measurementDtoToMeasurement(MeasurementDto measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }

    private MeasurementDto measurementToMeasurementDto(Measurement measurement) {
        MeasurementDto measurementDto = modelMapper.map(measurement, MeasurementDto.class);
        measurementDto.setSensor(new SensorDto(measurement.getSensor().getName()));
        return measurementDto;
    }

    private Measurement enrichMeasurement(Measurement measurement) {
        measurement.setAddedAt(System.currentTimeMillis());
        return measurement;
    }
}
