package com.water.demo.ddd.quote.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.water.demo.ddd.quote.model.CarPolicyQuote;

@Repository
public class CarPolicyQuoteRepository {
    private Map<String, CarPolicyQuote> carPolicyQuoteMap = new HashMap<>();

    public void save(CarPolicyQuote carPolicyQuote) {
        carPolicyQuoteMap.put(carPolicyQuote.getQuoteId(), carPolicyQuote);
    }

    public CarPolicyQuote byId(String quoteId) {
        return carPolicyQuoteMap.get(quoteId);
    }
}
