package com.ddd.tw.dddworkshop.policy.service;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;
import com.ddd.tw.dddworkshop.policy.domain.CarDetails;
import com.ddd.tw.dddworkshop.policy.domain.HomeDetails;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;
import com.ddd.tw.dddworkshop.policy.repository.QuoteRateRepository;

@Service
public class QuoteService {
    private static final double BASE_PREMIUM = 100.0;
    private static final String NUMBER_PATTERN = "#.00";

    @Autowired
    private QuoteRateRepository quoteRateRepository;

    public Quotation calculateQuote(HomeDetails homeDetails) {
        Double constructionMaterialRate = quoteRateRepository.getRate(homeDetails.getConstructionMaterial());
        Double buildingTypeRate = quoteRateRepository.getRate(homeDetails.getBuildingType());
        Double bedroomsTypeRate = quoteRateRepository.getRate(homeDetails.getBedroomsType());

        checkRateValidState(constructionMaterialRate, buildingTypeRate, bedroomsTypeRate);

        Double premium = BASE_PREMIUM * constructionMaterialRate * buildingTypeRate * bedroomsTypeRate;

        return new Quotation(formatPremium(premium));
    }

    public Quotation calculateQuote(CarDetails carDetails) {
        Double yearOfMakeRate = quoteRateRepository.getRate(carDetails.getYearOfMake());
        Double brandModelRate = quoteRateRepository.getRate(format("%s %s", carDetails.getBrand(), carDetails.getModel()));
        Double parkingAddressRate = quoteRateRepository.getRate(carDetails.getParkingAddress());
        Double kilosEachYearRate = quoteRateRepository.getRate(carDetails.getKilosEachYear());

        checkRateValidState(yearOfMakeRate, brandModelRate, parkingAddressRate, kilosEachYearRate);

        Double premium = BASE_PREMIUM * yearOfMakeRate * brandModelRate * parkingAddressRate * kilosEachYearRate;

        return new Quotation(formatPremium(premium));
    }


    private void checkRateValidState(Double constructionMaterialRate, Double buildingTypeRate, Double bedroomsTypeRate) {
        if (isEmpty(constructionMaterialRate) || isEmpty(buildingTypeRate) || isEmpty(bedroomsTypeRate)) {
            throw new InvalidQuotationException();
        }
    }

    private Double formatPremium(Double premium) {
        DecimalFormat decimalFormat = new DecimalFormat(NUMBER_PATTERN);
        return new Double(decimalFormat.format(premium));
    }

    private void checkRateValidState(Double yearOfMakeRate, Double brandModelRate, Double parkingAddressRate, Double kilosEachYearRate) {
        if (isEmpty(yearOfMakeRate) || isEmpty(brandModelRate) || isEmpty(parkingAddressRate) || isEmpty(kilosEachYearRate)) {
            throw new InvalidQuotationException();
        }
    }

}
