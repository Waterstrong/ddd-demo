package com.ddd.tw.dddworkshop.policy.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.policy.domain.CarDetails;
import com.ddd.tw.dddworkshop.policy.domain.HomeDetails;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;
import com.ddd.tw.dddworkshop.policy.service.QuoteService;

@Service
public class QuoteApplicationService {

    @Autowired
    private QuoteService quoteService;

    public Quotation calculateQuote(HomeDetails homeDetails) {
        return quoteService.calculateQuote(homeDetails);
    }

    public Quotation calculateQuote(CarDetails carDetails) {
        return quoteService.calculateQuote(carDetails);
    }
}
