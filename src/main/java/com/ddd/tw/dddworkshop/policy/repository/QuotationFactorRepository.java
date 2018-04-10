package com.ddd.tw.dddworkshop.policy.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.stereotype.Repository;
import com.ddd.tw.dddworkshop.policy.domain.QuotationFactor;

@Repository
public class QuotationFactorRepository {
    private Map<String, QuotationFactor> quotationFactorMap = new HashMap<>();

    public QuotationFactorRepository() {
        quotationFactorMap.put("111", new QuotationFactor("111", "建筑材料", "铝材", 1.1));
        quotationFactorMap.put("112", new QuotationFactor("222", "建筑材料", "钢材", 1.2));
        quotationFactorMap.put("113", new QuotationFactor("333", "建筑材料", "砖", 1.3));
        quotationFactorMap.put("114", new QuotationFactor("444", "建筑类型", "公寓", 1.1));
        quotationFactorMap.put("115", new QuotationFactor("555", "建筑类型", "别墅", 1.2));
        quotationFactorMap.put("116", new QuotationFactor("666", "建筑类型", "Soho", 1.3));
        quotationFactorMap.put("117", new QuotationFactor("777", "房间数量", "一室", 1.1));
        quotationFactorMap.put("118", new QuotationFactor("888", "房间数量", "二室", 1.2));
        quotationFactorMap.put("119", new QuotationFactor("999", "房间数量", "三室及以上", 1.3));

    }

    public QuotationFactor findByCateoryAndItem(String category, String item) {
        return quotationFactorMap.values().stream().findFirst().filter(matchCategoryAndItem(category, item)).orElse(null);
    }

    private Predicate<QuotationFactor> matchCategoryAndItem(String category, String item) {
        return it -> it.getCategory().equals(category) && it.getItem().equals(item);
    }
}
