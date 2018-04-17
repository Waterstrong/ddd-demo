package com.ddd.tw.dddworkshop.quote.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarPolicyQuote extends PolicyQuote {
    private String yearOfMake;
    private String brand;
    private String model;
    private String parkingAddress;
    private String kilosEachYear;
}
