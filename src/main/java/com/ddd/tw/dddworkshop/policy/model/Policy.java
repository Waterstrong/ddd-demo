package com.ddd.tw.dddworkshop.policy.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Policy {
    private String policyNumber = UUID.randomUUID().toString();
    private String policyStartDate;
    private String quoteId;
    private HolderDetail holderDetail;
}
