package com.water.demo.ddd.domain.quote.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomePolicyQuote extends PolicyQuote {
    private String constructionMaterial;
    private String buildingType;
    private String bedroomsType;
    private String address;
}
