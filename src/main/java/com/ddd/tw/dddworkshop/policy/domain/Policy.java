package com.ddd.tw.dddworkshop.policy.domain;

import java.util.UUID;

import com.ddd.tw.dddworkshop.quote.domain.Quotation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Policy {
    private String policyNumber = UUID.randomUUID().toString();
    private String policyStartDate;
    private String quoteId;
    private PolicyHolder policyHolder;
}
