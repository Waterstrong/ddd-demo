package com.ddd.tw.dddworkshop.quote.service;

import static java.lang.String.format;
import static java.util.stream.Stream.of;

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
    private static final Double BASE_PREMIUM = 100.0;
    private static final String NUMBER_PATTERN = "#.00";

    @Autowired
    private QuoteRateRepository quoteRateRepository;

    public HomePolicyQuote generateHomePolicyQuote(HomeDetailCommand homeDetail) {
        HomePolicyQuote homePolicyQuote = HomePolicyQuoteMapper.INSTANCE.mapToHomePolicyQuote(homeDetail);

        homePolicyQuote.setPremium(calculateHomePolicyQuotePremium(homeDetail));

        return homePolicyQuote;
    }

    public CarPolicyQuote generateCarPolicyQuote(CarDetailCommand carDetail) {
        CarPolicyQuote carPolicyQuote = CarPolicyQuoteMapper.INSTANCE.mapToCarPolicyQuote(carDetail);

        carPolicyQuote.setPremium(calculateCarPolicyQuotePremium(carDetail));

        return carPolicyQuote;
    }

    private Double calculateHomePolicyQuotePremium(HomeDetailCommand homeDetail) {
        Double constructionMaterialRate = retrieveQuoteRate(homeDetail.getConstructionMaterial());
        Double buildingTypeRate = retrieveQuoteRate(homeDetail.getBuildingType());
        Double bedroomsTypeRate = retrieveQuoteRate(homeDetail.getBedroomsType());

        Double premium = multiplyBasePremiumByRates(constructionMaterialRate, buildingTypeRate, bedroomsTypeRate);

        return formatPremium(premium);
    }

    private Double calculateCarPolicyQuotePremium(CarDetailCommand carDetail) {
        String brandModel = format("%s %s", carDetail.getBrand(), carDetail.getModel());
        Double yearOfMakeRate = retrieveQuoteRate(carDetail.getYearOfMake());
        Double brandModelRate = quoteRateRepository.getRate(brandModel);
        Double parkingAddressRate = retrieveQuoteRate(carDetail.getParkingAddress());
        Double kilosEachYearRate = retrieveQuoteRate(carDetail.getKilosEachYear());

        Double premium = multiplyBasePremiumByRates(yearOfMakeRate, brandModelRate, parkingAddressRate, kilosEachYearRate);

        return formatPremium(premium);
    }

    private Double retrieveQuoteRate(String category) {
        Double rate = quoteRateRepository.getRate(category);
        if (rate == null) {
            throw new InvalidQuotationException(category);
        }
        return rate;
    }

    private Double multiplyBasePremiumByRates(Double... rates) {
        return of(rates).reduce(BASE_PREMIUM, (current, next) -> current * next);
    }

    private Double formatPremium(Double premium) {
        DecimalFormat decimalFormat = new DecimalFormat(NUMBER_PATTERN);
        return new Double(decimalFormat.format(premium));
    }
}
