package com.ddd.tw.dddworkshop.policy.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Policy {
    private String policyNumber;
    private String policyStartDate;
    private Quotation quotation;
    private PolicyHolder policyHolder;

    public abstract Quotation calculateQuote();
}
