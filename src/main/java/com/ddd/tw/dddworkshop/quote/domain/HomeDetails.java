package com.ddd.tw.dddworkshop.quote.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeDetails {
    private String constructionMaterial;
    private String buildingType;
    private String bedroomsType;
}
