package com.water.demo.ddd.policy.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.water.demo.ddd.policy.model.Policy;

@Repository
public class PolicyRepository {
    private Map<String, Policy> policyMap = new HashMap<>();

    public void save(Policy policy) {
        policyMap.put(policy.getPolicyNumber(), policy);
    }

    public Policy byPolicyNumber(String policyNumber) {
        return policyMap.get(policyNumber);
    }

    public Policy byQuoteId(String quoteId) {
        return policyMap.values().stream()
                .filter(policy -> quoteId.equals(policy.getQuoteId()))
                .findFirst()
                .orElse(null);
    }
}
