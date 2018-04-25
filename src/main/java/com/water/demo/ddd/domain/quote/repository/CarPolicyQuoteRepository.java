package com.water.demo.ddd.domain.quote.repository;

import com.water.demo.ddd.domain.quote.model.CarPolicyQuote;

public interface CarPolicyQuoteRepository {
    void save(CarPolicyQuote carPolicyQuote);

    CarPolicyQuote byId(String quoteId);
}
