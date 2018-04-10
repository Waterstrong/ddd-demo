package com.ddd.tw.dddworkshop.policy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuotationFactor {
    private String uuid;
    private String category;
    private String item;
    private Double rate;
}
