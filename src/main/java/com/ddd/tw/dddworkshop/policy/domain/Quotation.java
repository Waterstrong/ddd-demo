package com.ddd.tw.dddworkshop.policy.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Quotation {
    private String quoteId = UUID.randomUUID().toString();
    private Double premium;

    public Quotation(Double premium) {
        this.premium = premium;
    }
}
