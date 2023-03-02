package com.belenya.restSensor.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    private Boolean raining;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    private Long addedAt;
}
