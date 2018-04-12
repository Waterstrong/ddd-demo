package com.ddd.tw.dddworkshop.quote.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDetails {
    private String yearOfMake;
    private String brand;
    private String model;
    private String parkingAddress;
    private String kilosEachYear;
}
