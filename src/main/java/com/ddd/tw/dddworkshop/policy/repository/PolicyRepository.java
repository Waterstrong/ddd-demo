package com.ddd.tw.dddworkshop.policy.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.ddd.tw.dddworkshop.policy.model.Policy;

@Repository
public class PolicyRepository {
    private Map<String, Policy> policyMap = new HashMap<>();

    public void save(Policy policy) {
        policyMap.put(policy.getPolicyNumber(), policy);
    }

    public Policy byId(String policyNumber) {
        return policyMap.get(policyNumber);
    }
}
