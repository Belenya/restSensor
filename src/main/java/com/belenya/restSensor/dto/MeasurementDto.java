package com.belenya.restSensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class MeasurementDto {

    @NotNull
    @Range(min = -100, max = 100)
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDto sensor;
}
