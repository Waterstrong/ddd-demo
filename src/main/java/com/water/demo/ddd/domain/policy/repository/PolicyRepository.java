package com.water.demo.ddd.domain.policy.repository;

import com.water.demo.ddd.domain.policy.model.Policy;

public interface PolicyRepository {
    void save(Policy policy);

    Policy byPolicyNumber(String policyNumber);

    Policy byQuoteId(String quoteId);
}
