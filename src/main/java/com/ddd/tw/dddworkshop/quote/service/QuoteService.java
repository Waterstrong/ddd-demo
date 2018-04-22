package com.ddd.tw.dddworkshop.quote.service;

import static java.lang.String.format;
import static java.util.stream.Stream.of;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.exception.InvalidQuoteCategoryException;
import com.ddd.tw.dddworkshop.quote.command.GenerateCarPolicyQuoteCommand;
import com.ddd.tw.dddworkshop.quote.command.GenerateHomePolicyQuoteCommand;
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

    public HomePolicyQuote generateHomePolicyQuote(GenerateHomePolicyQuoteCommand command) {
        HomePolicyQuote homePolicyQuote = HomePolicyQuoteMapper.INSTANCE.mapToHomePolicyQuote(command);

        homePolicyQuote.setPremium(calculateHomePolicyQuotePremium(command));

        return homePolicyQuote;
    }

    public CarPolicyQuote generateCarPolicyQuote(GenerateCarPolicyQuoteCommand command) {
        CarPolicyQuote carPolicyQuote = CarPolicyQuoteMapper.INSTANCE.mapToCarPolicyQuote(command);

        carPolicyQuote.setPremium(calculateCarPolicyQuotePremium(command));

        return carPolicyQuote;
    }

    private Double calculateHomePolicyQuotePremium(GenerateHomePolicyQuoteCommand command) {
        Double constructionMaterialRate = retrieveQuoteRate(command.getConstructionMaterial());
        Double buildingTypeRate = retrieveQuoteRate(command.getBuildingType());
        Double bedroomsTypeRate = retrieveQuoteRate(command.getBedroomsType());

        Double premium = multiplyBasePremiumByRates(constructionMaterialRate, buildingTypeRate, bedroomsTypeRate);

        return formatPremium(premium);
    }

    private Double calculateCarPolicyQuotePremium(GenerateCarPolicyQuoteCommand command) {
        String brandModel = format("%s %s", command.getBrand(), command.getModel());
        Double yearOfMakeRate = retrieveQuoteRate(command.getYearOfMake());
        Double brandModelRate = quoteRateRepository.getRate(brandModel);
        Double parkingAddressRate = retrieveQuoteRate(command.getParkingAddress());
        Double kilosEachYearRate = retrieveQuoteRate(command.getKilosEachYear());

        Double premium = multiplyBasePremiumByRates(yearOfMakeRate, brandModelRate, parkingAddressRate, kilosEachYearRate);

        return formatPremium(premium);
    }

    private Double retrieveQuoteRate(String category) {
        Double rate = quoteRateRepository.getRate(category);
        if (rate == null) {
            throw new InvalidQuoteCategoryException(category);
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
