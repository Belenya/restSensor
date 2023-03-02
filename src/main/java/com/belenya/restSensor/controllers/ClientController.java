package com.belenya.restSensor.controllers;

import com.belenya.restSensor.templates.SensorRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final SensorRestTemplate restTemplate;

    @PostMapping("/register/{sensorName}")
    public ResponseEntity<HttpStatus> registerSensor(@PathVariable String sensorName) {
        restTemplate.registerSensor(sensorName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add1000/{sensorName}")
    public ResponseEntity<HttpStatus> add1000Measurements(@PathVariable String sensorName) {
        restTemplate.create1000Measurements(sensorName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
