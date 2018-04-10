package com.ddd.tw.dddworkshop.policy.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HomePolicyCommand {
    private String policyStartDate;
    private String quoteId;
    private Double quotePremium;
    private String constructionMaterial;
    private String buildingType;
    private String bedroomsType;
    private String address;
}
