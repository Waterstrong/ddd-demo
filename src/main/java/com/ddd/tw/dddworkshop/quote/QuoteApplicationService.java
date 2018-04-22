package com.ddd.tw.dddworkshop.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ddd.tw.dddworkshop.quote.command.GenerateCarPolicyQuoteCommand;
import com.ddd.tw.dddworkshop.quote.command.GenerateHomePolicyQuoteCommand;
import com.ddd.tw.dddworkshop.quote.model.CarPolicyQuote;
import com.ddd.tw.dddworkshop.quote.model.HomePolicyQuote;
import com.ddd.tw.dddworkshop.quote.model.PolicyQuote;
import com.ddd.tw.dddworkshop.quote.repository.CarPolicyQuoteRepository;
import com.ddd.tw.dddworkshop.quote.repository.HomePolicyQuoteRepository;
import com.ddd.tw.dddworkshop.quote.service.QuoteService;

@Service
@Transactional
public class QuoteApplicationService {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private CarPolicyQuoteRepository carPolicyQuoteRepository;

    @Autowired
    private HomePolicyQuoteRepository homePolicyQuoteRepository;

    public PolicyQuote generateQuote(GenerateHomePolicyQuoteCommand command) {
        HomePolicyQuote homePolicyQuote = quoteService.generateHomePolicyQuote(command);

        homePolicyQuoteRepository.save(homePolicyQuote);

        return homePolicyQuote;
    }

    public PolicyQuote generateQuote(GenerateCarPolicyQuoteCommand command) {
        CarPolicyQuote carPolicyQuote = quoteService.generateCarPolicyQuote(command);

        carPolicyQuoteRepository.save(carPolicyQuote);

        return carPolicyQuote;
    }
}
