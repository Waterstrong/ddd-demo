package com.water.demo.ddd.domain.quote.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PolicyQuote {
    private String quoteId = UUID.randomUUID().toString();
    private Double premium;
}
