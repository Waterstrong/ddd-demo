package com.water.demo.ddd.persistence.policy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.water.demo.ddd.domain.policy.model.Policy;
import com.water.demo.ddd.domain.policy.repository.PolicyRepository;

@Repository
public class PolicyRepositoryInMemory implements PolicyRepository {
    private Map<String, Policy> policyMap = new HashMap<>();

    @Override
    public void save(Policy policy) {
        policyMap.put(policy.getPolicyNumber(), policy);
    }

    @Override
    public Policy byPolicyNumber(String policyNumber) {
        return policyMap.get(policyNumber);
    }

    @Override
    public Policy byQuoteId(String quoteId) {
        return policyMap.values().stream()
                .filter(policy -> quoteId.equals(policy.getQuoteId()))
                .findFirst()
                .orElse(null);
    }
}
