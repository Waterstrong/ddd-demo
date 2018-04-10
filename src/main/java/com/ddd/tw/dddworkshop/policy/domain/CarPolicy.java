package com.ddd.tw.dddworkshop.policy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarPolicy extends Policy {
    private String yearOfMake;
    private String brand;
    private String model;
    private String parkingAddress;
    private String kilosEachYear;
    private DriverDetail driverDetail;

    @Override
    public Quotation calculateQuote() {
        return null;
    }
}
