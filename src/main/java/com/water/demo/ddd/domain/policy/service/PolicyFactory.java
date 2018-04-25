package com.water.demo.ddd.domain.policy.service;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.domain.policy.command.BuyPolicyCommand;
import com.water.demo.ddd.domain.policy.mapper.PolicyMapper;
import com.water.demo.ddd.domain.policy.model.Policy;
import com.water.demo.ddd.domain.policy.repository.PolicyRepository;
import com.water.demo.ddd.domain.quote.repository.CarPolicyQuoteRepository;
import com.water.demo.ddd.domain.quote.repository.HomePolicyQuoteRepository;
import com.water.demo.ddd.exception.ResourceConflictException;
import com.water.demo.ddd.exception.ResourceNotFoundException;

@Service
public class PolicyFactory {
    private static final String QUOTE_NOT_EXIST_MESSAGE = "Quote <%s> does not exist!";
    private static final String POLICY_EXIST_MESSAGE = "Policy <%s> with quote <%s> already exist!";

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
            throw new ResourceNotFoundException(format(QUOTE_NOT_EXIST_MESSAGE, quoteId));
        }

        Policy policy = policyRepository.byQuoteId(quoteId);
        if (policy != null) {
            String message = format(POLICY_EXIST_MESSAGE, policy.getPolicyNumber(), policy.getQuoteId());
            throw new ResourceConflictException(message);
        }
    }

    private boolean existQuote(String quoteId) {
        return homePolicyQuoteRepository.byId(quoteId) != null ||
                carPolicyQuoteRepository.byId(quoteId) != null;
    }
}
