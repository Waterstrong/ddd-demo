package com.ddd.tw.dddworkshop.policy.domain;

import java.text.DecimalFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Policy {
    protected static final double BASE_PREMIUM = 100.0;
    private String policyNumber;
    private String policyStartDate;
    private Quotation quotation;
    private PolicyHolder policyHolder;

    public abstract Quotation calculateQuote();

    protected Double formatPremium(Double premium) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return new Double(decimalFormat.format(premium));
    }
}
