package com.ddd.tw.dddworkshop.quote.service;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;
import com.ddd.tw.dddworkshop.quote.command.CarDetailCommand;
import com.ddd.tw.dddworkshop.quote.command.HomeDetailCommand;
import com.ddd.tw.dddworkshop.quote.mapper.CarPolicyQuoteMapper;
import com.ddd.tw.dddworkshop.quote.mapper.HomePolicyQuoteMapper;
import com.ddd.tw.dddworkshop.quote.model.CarPolicyQuote;
import com.ddd.tw.dddworkshop.quote.model.HomePolicyQuote;
import com.ddd.tw.dddworkshop.quote.repository.QuoteRateRepository;

@Service
public class QuoteService {
    private static final double BASE_PREMIUM = 100.0;
    private static final String NUMBER_PATTERN = "#.00";

    @Autowired
    private QuoteRateRepository quoteRateRepository;

    public HomePolicyQuote generateHomePolicyQuote(HomeDetailCommand homeDetail) {
        HomePolicyQuote homePolicyQuote = HomePolicyQuoteMapper.INSTANCE.mapToHomePolicyQuote(homeDetail);
        homePolicyQuote.setPremium(calculatePremium(homeDetail));
        return homePolicyQuote;
    }

    public CarPolicyQuote generateCarPolicyQuote(CarDetailCommand carDetail) {
        CarPolicyQuote carPolicyQuote = CarPolicyQuoteMapper.INSTANCE.mapToCarPolicyQuote(carDetail);

        carPolicyQuote.setPremium(calculatePremium(carDetail));

        return carPolicyQuote;
    }

    private Double calculatePremium(HomeDetailCommand homeDetail) {
        Double constructionMaterialRate = quoteRateRepository.getRate(homeDetail.getConstructionMaterial());
        Double buildingTypeRate = quoteRateRepository.getRate(homeDetail.getBuildingType());
        Double bedroomsTypeRate = quoteRateRepository.getRate(homeDetail.getBedroomsType());

        checkRateValidState(constructionMaterialRate, buildingTypeRate, bedroomsTypeRate);

        Double premium = BASE_PREMIUM * constructionMaterialRate * buildingTypeRate * bedroomsTypeRate;

        return formatPremium(premium);
    }

    private Double calculatePremium(CarDetailCommand carDetail) {
        Double yearOfMakeRate = quoteRateRepository.getRate(carDetail.getYearOfMake());
        Double brandModelRate = quoteRateRepository.getRate(format("%s %s", carDetail.getBrand(), carDetail.getModel()));
        Double parkingAddressRate = quoteRateRepository.getRate(carDetail.getParkingAddress());
        Double kilosEachYearRate = quoteRateRepository.getRate(carDetail.getKilosEachYear());

        checkRateValidState(yearOfMakeRate, brandModelRate, parkingAddressRate, kilosEachYearRate);

        Double premium = BASE_PREMIUM * yearOfMakeRate * brandModelRate * parkingAddressRate * kilosEachYearRate;

        return formatPremium(premium);
    }


    private void checkRateValidState(Double constructionMaterialRate, Double buildingTypeRate, Double bedroomsTypeRate) {
        if (isEmpty(constructionMaterialRate) || isEmpty(buildingTypeRate) || isEmpty(bedroomsTypeRate)) {
            throw new InvalidQuotationException();
        }
    }

    private void checkRateValidState(Double yearOfMakeRate, Double brandModelRate, Double parkingAddressRate, Double kilosEachYearRate) {
        if (isEmpty(yearOfMakeRate) || isEmpty(brandModelRate) || isEmpty(parkingAddressRate) || isEmpty(kilosEachYearRate)) {
            throw new InvalidQuotationException();
        }
    }

    private Double formatPremium(Double premium) {
        DecimalFormat decimalFormat = new DecimalFormat(NUMBER_PATTERN);
        return new Double(decimalFormat.format(premium));
    }
}
