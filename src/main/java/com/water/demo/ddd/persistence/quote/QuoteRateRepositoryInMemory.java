package com.water.demo.ddd.persistence.quote;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.water.demo.ddd.domain.quote.repository.QuoteRateRepository;

@Repository
public class QuoteRateRepositoryInMemory implements QuoteRateRepository {
    private Map<String, Double> quoteRateMap = new HashMap<>();

    public QuoteRateRepositoryInMemory() {
        quoteRateMap.put("铝材", 1.1);
        quoteRateMap.put("公寓", 1.1);
        quoteRateMap.put("一室", 1.1);
        quoteRateMap.put("钢材", 1.2);
        quoteRateMap.put("砖", 1.3);
        quoteRateMap.put("别墅", 1.2);
        quoteRateMap.put("Soho", 1.3);
        quoteRateMap.put("二室", 1.2);
        quoteRateMap.put("三室及以上", 1.3);
        quoteRateMap.put("≥2015", 1.0);
        quoteRateMap.put("2010-2014", 1.2);
        quoteRateMap.put("≤2009", 1.4);
        quoteRateMap.put("BMW C600", 1.4);
        quoteRateMap.put("BMW C650", 1.6);
        quoteRateMap.put("DUCATI 1199", 1.4);
        quoteRateMap.put("DUCATI 1299", 1.6);
        quoteRateMap.put("DUCATI 848", 1.6);
        quoteRateMap.put("HONDA AG-XR", 1.2);
        quoteRateMap.put("HONDA CB1100", 1.2);
        quoteRateMap.put("HONDA CB125E", 1.4);
        quoteRateMap.put("Carport", 1.1);
        quoteRateMap.put("Drive way", 1.2);
        quoteRateMap.put("Street", 1.6);
        quoteRateMap.put("≥1500km", 1.4);
        quoteRateMap.put("750-1499km", 1.2);
        quoteRateMap.put("<750km", 1.0);
    }

    @Override
    public Double getRate(String category) {
        return quoteRateMap.get(category);
    }

}
