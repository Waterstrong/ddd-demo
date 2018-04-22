package com.ddd.tw.dddworkshop.quote.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.ddd.tw.dddworkshop.quote.model.CarPolicyQuote;

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
