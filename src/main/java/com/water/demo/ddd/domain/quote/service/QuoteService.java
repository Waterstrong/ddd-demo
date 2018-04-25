package com.water.demo.ddd.domain.quote.service;

import static java.lang.String.format;
import static java.util.stream.Stream.of;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.domain.quote.command.GenerateCarPolicyQuoteCommand;
import com.water.demo.ddd.domain.quote.command.GenerateHomePolicyQuoteCommand;
import com.water.demo.ddd.domain.quote.mapper.CarPolicyQuoteMapper;
import com.water.demo.ddd.domain.quote.mapper.HomePolicyQuoteMapper;
import com.water.demo.ddd.domain.quote.model.CarPolicyQuote;
import com.water.demo.ddd.domain.quote.model.HomePolicyQuote;
import com.water.demo.ddd.domain.quote.repository.QuoteRateRepository;
import com.water.demo.ddd.exception.BadRequestException;

@Service
public class QuoteService {
    private static final Double BASE_PREMIUM = 100.0;
    private static final String NUMBER_PATTERN = "#.00";
    private static final String INVALID_POLICY_QUOTE_CATEGORY_MESSAGE = "Invalid policy quote category: '%s'. Failed to calculate the quote!";
    private static final String BRAND_MODEL_FORMAT = "%s %s";

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
        String brandModel = format(BRAND_MODEL_FORMAT, command.getBrand(), command.getModel());
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
            throw new BadRequestException(format(INVALID_POLICY_QUOTE_CATEGORY_MESSAGE, category));
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
