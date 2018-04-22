package com.ddd.tw.dddworkshop.policy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.exception.PolicyAlreadyExistException;
import com.ddd.tw.dddworkshop.exception.QuoteNotFoundException;
import com.ddd.tw.dddworkshop.policy.command.BuyPolicyCommand;
import com.ddd.tw.dddworkshop.policy.mapper.PolicyMapper;
import com.ddd.tw.dddworkshop.policy.model.Policy;
import com.ddd.tw.dddworkshop.policy.repository.PolicyRepository;
import com.ddd.tw.dddworkshop.quote.repository.CarPolicyQuoteRepository;
import com.ddd.tw.dddworkshop.quote.repository.HomePolicyQuoteRepository;

@Service
public class PolicyFactory {
    @Autowired
    private HomePolicyQuoteRepository homePolicyQuoteRepository;

    @Autowired
    private CarPolicyQuoteRepository carPolicyQuoteRepository;

    @Autowired
    private PolicyRepository policyRepository;

    public Policy createPolicy(BuyPolicyCommand command) {
        checkBuyPolicyCondition(command);

        return PolicyMapper.INSTANCE.mapToPolicy(command);

    }

    private void checkBuyPolicyCondition(BuyPolicyCommand command) {
        String quoteId = command.getQuoteId();
        if (!existQuote(quoteId)) {
            throw new QuoteNotFoundException(quoteId);
        }

        Policy policy = policyRepository.byQuoteId(quoteId);
        if (policy != null) {
            throw new PolicyAlreadyExistException(policy.getPolicyNumber(), policy.getQuoteId());
        }
    }

    private boolean existQuote(String quoteId) {
        return homePolicyQuoteRepository.byId(quoteId) != null ||
                carPolicyQuoteRepository.byId(quoteId) != null;
    }
}
