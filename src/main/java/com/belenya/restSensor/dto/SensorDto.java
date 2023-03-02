package com.belenya.restSensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;
}
