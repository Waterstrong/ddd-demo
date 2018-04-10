package com.ddd.tw.dddworkshop.policy.domain;

import static com.google.common.collect.ImmutableMap.of;
import static java.lang.String.format;

import java.util.Map;

import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;
import com.google.common.collect.ImmutableMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarPolicy extends Policy {
    private static final Map<String, Double> yearOfMakeRateMap = of("≥2015", 1.0, "2010-2014", 1.2, "≤2009", 1.4);
    private static final Map<String, Double> brandModelRateMap = ImmutableMap.<String, Double>builder()
            .put("BMW C600", 1.4).put("BMW C650", 1.6).put("DUCATI 1199", 1.4).put("DUCATI 1299", 1.6)
            .put("DUCATI 848", 1.6).put("HONDA AG-XR", 1.2).put("HONDA CB1100", 1.2).put("HONDA CB125E", 1.4)
            .build();
    private static final Map<String, Double> parkingAddressRateMap = of("Carport", 1.1, "Drive way", 1.2, "Street", 1.6);
    private static final Map<String, Double> kilosEachYearRateMap = of("≥1500km", 1.4, "750-1499km", 1.2, "<750km", 1.0);

    private String yearOfMake;
    private String brand;
    private String model;
    private String parkingAddress;
    private String kilosEachYear;
    private DriverDetail driverDetail;

    @Override
    public Quotation calculateQuote() {
        Double yearOfMakeRate = yearOfMakeRateMap.get(yearOfMake);
        Double brandModelRate = brandModelRateMap.get(format("%s %s", brand, model));
        Double parkingAddressRate = parkingAddressRateMap.get(parkingAddress);
        Double kilosEachYearRate = kilosEachYearRateMap.get(kilosEachYear);

        checkRateValidState(yearOfMakeRate, brandModelRate, parkingAddressRate, kilosEachYearRate);

        Double premium = BASE_PREMIUM * yearOfMakeRate * brandModelRate * parkingAddressRate * kilosEachYearRate;

        return new Quotation(formatPremium(premium));
    }

    private void checkRateValidState(Double yearOfMakeRate, Double brandModelRate, Double parkingAddressRate, Double kilosEachYearRate) {
        if (yearOfMakeRate == null || brandModelRate == null || parkingAddressRate == null || kilosEachYearRate == null) {
            throw new InvalidQuotationException();
        }
    }
}
