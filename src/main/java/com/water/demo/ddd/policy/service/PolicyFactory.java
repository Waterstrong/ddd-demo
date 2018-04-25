package com.water.demo.ddd.policy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.exception.PolicyAlreadyExistException;
import com.water.demo.ddd.exception.QuoteNotFoundException;
import com.water.demo.ddd.policy.command.BuyPolicyCommand;
import com.water.demo.ddd.policy.mapper.PolicyMapper;
import com.water.demo.ddd.policy.model.Policy;
import com.water.demo.ddd.policy.repository.PolicyRepository;
import com.water.demo.ddd.quote.repository.CarPolicyQuoteRepository;
import com.water.demo.ddd.quote.repository.HomePolicyQuoteRepository;

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
