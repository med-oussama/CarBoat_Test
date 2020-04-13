package com.example.carboat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Vehicle {
    private String make;
    private String model;
    private String version;
    private String category;
    private String registerNumber;
    private Long mileage;
}
