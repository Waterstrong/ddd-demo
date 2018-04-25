package com.water.demo.ddd.persistence.quote;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.water.demo.ddd.domain.quote.model.CarPolicyQuote;
import com.water.demo.ddd.domain.quote.repository.CarPolicyQuoteRepository;

@Repository
public class CarPolicyQuoteRepositoryInMemory implements CarPolicyQuoteRepository {
    private Map<String, CarPolicyQuote> carPolicyQuoteMap = new HashMap<>();

    @Override
    public void save(CarPolicyQuote carPolicyQuote) {
        carPolicyQuoteMap.put(carPolicyQuote.getQuoteId(), carPolicyQuote);
    }

    @Override
    public CarPolicyQuote byId(String quoteId) {
        return carPolicyQuoteMap.get(quoteId);
    }
}
