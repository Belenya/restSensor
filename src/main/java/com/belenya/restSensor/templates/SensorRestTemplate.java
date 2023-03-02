package com.belenya.restSensor.templates;

import com.belenya.restSensor.dto.MeasurementDto;
import com.belenya.restSensor.dto.SensorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class SensorRestTemplate {

    private final RestTemplate restTemplate;

    public void registerSensor(String name) {
        HttpEntity<SensorDto> request = new HttpEntity<>(new SensorDto(name));

        restTemplate.exchange("http://localhost:8080/sensors/registration", HttpMethod.POST, request, HttpStatus.class);
    }

    public void create1000Measurements(String sensorName) {
        MeasurementDto measurementDto = new MeasurementDto();
        measurementDto.setSensor(new SensorDto(sensorName));
        HttpEntity<MeasurementDto> request;
        for(var i = 0; i < 1000; i++) {
            measurementDto.setValue(getRandomDoubleBetween(-100d, 100d));
            measurementDto.setRaining(getRandomBool());
            request = new HttpEntity<>(measurementDto);

            restTemplate.exchange("http://localhost:8080/measurements/add", HttpMethod.POST, request, HttpStatus.class);

        }
    }

    public Double getRandomDoubleBetween(Double min, Double max) {
        double random = (Math.random() * (max - min)) + min;
        return Math.round(random * 100) / 100d;
    }

    public boolean getRandomBool() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
