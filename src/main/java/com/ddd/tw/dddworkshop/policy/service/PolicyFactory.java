package com.ddd.tw.dddworkshop.policy.service;

import org.springframework.stereotype.Component;
import com.ddd.tw.dddworkshop.policy.command.PolicyCommand;
import com.ddd.tw.dddworkshop.policy.mapper.PolicyMapper;
import com.ddd.tw.dddworkshop.policy.model.Policy;

@Component
public class PolicyFactory {
    public Policy createPolicy(PolicyCommand policyCommand) {
        // check quote id existing
        return PolicyMapper.INSTANCE.mapToPolicy(policyCommand);
    }
}
