package com.water.demo.ddd.domain.quote.repository;

public interface QuoteRateRepository {
    Double getRate(String category);
}
