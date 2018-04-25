package com.water.demo.ddd.domain.quote.repository;

import com.water.demo.ddd.domain.quote.model.HomePolicyQuote;

public interface HomePolicyQuoteRepository {
    void save(HomePolicyQuote homePolicyQuote);

    HomePolicyQuote byId(String quoteId);
}
