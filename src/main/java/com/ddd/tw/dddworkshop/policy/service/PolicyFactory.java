package com.ddd.tw.dddworkshop.policy.service;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ddd.tw.dddworkshop.exception.QuoteNotFoundException;
import com.ddd.tw.dddworkshop.policy.command.BuyPolicyCommand;
import com.ddd.tw.dddworkshop.policy.mapper.PolicyMapper;
import com.ddd.tw.dddworkshop.policy.model.Policy;
import com.ddd.tw.dddworkshop.quote.repository.CarPolicyQuoteRepository;
import com.ddd.tw.dddworkshop.quote.repository.HomePolicyQuoteRepository;

@Component
public class PolicyFactory {
    @Autowired
    private HomePolicyQuoteRepository homePolicyQuoteRepository;

    @Autowired
    private CarPolicyQuoteRepository carPolicyQuoteRepository;

    public Policy createPolicy(BuyPolicyCommand command) {
        @NotBlank String quoteId = command.getQuoteId();
        if (!existQuote(quoteId)) {
            throw new QuoteNotFoundException(quoteId);
        }
        return PolicyMapper.INSTANCE.mapToPolicy(command);

    }

    private boolean existQuote(String quoteId) {
        return homePolicyQuoteRepository.byId(quoteId) != null ||
                carPolicyQuoteRepository.byId(quoteId) != null;
    }
}
