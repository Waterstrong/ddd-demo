package com.water.demo.ddd.domain.quote.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateHomePolicyQuoteCommand {
    private String constructionMaterial;
    private String buildingType;
    private String bedroomsType;
    private String address;
}
