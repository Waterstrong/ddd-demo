package com.water.demo.ddd.quote.command;

import com.water.demo.ddd.quote.model.DriverDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateCarPolicyQuoteCommand {
    private String yearOfMake;
    private String brand;
    private String model;
    private String parkingAddress;
    private String kilosEachYear;
    private DriverDetail driverDetail;
}
