package com.ddd.tw.dddworkshop.policy.domain;

import static com.google.common.collect.ImmutableMap.of;

import java.util.Map;

import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomePolicy extends Policy {
    private static final Map<String, Double> constructionMaterialRateMap = of("铝材", 1.1, "钢材", 1.2, "砖", 1.3);
    private static final Map<String, Double> buildingTypeRateMap = of("公寓", 1.1, "别墅", 1.2, "Soho", 1.3);
    private static final Map<String, Double> bedroomsTypeRateMap = of("一室", 1.1, "二室", 1.2, "三室及以上", 1.3);

    private String constructionMaterial;
    private String buildingType;
    private String bedroomsType;
    private String address;

    @Override
    public Quotation calculateQuote() {
        Double constructionMaterialRate = constructionMaterialRateMap.get(constructionMaterial);
        Double buildingTypeRate = buildingTypeRateMap.get(buildingType);
        Double bedroomsTypeRate = bedroomsTypeRateMap.get(bedroomsType);

        checkRateValidState(constructionMaterialRate, buildingTypeRate, bedroomsTypeRate);

        Double premium = BASE_PREMIUM * constructionMaterialRate * buildingTypeRate * bedroomsTypeRate;

        return new Quotation(formatPremium(premium));
    }

    private void checkRateValidState(Double constructionMaterialRate, Double buildingTypeRate, Double bedroomsTypeRate) {
        if (constructionMaterialRate == null || buildingTypeRate == null || bedroomsTypeRate == null) {
            throw new InvalidQuotationException();
        }
    }
}
