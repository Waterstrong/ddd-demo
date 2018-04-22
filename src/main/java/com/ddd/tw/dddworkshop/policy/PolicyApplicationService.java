package com.ddd.tw.dddworkshop.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ddd.tw.dddworkshop.policy.command.PolicyCommand;
import com.ddd.tw.dddworkshop.policy.model.Policy;
import com.ddd.tw.dddworkshop.policy.repository.PolicyRepository;
import com.ddd.tw.dddworkshop.policy.service.PolicyFactory;

@Service
@Transactional
public class PolicyApplicationService {
    @Autowired
    private PolicyFactory policyFactory;

    @Autowired
    private PolicyRepository policyRepository;

    public String buyPolicy(PolicyCommand policyCommand) {
        Policy policy = policyFactory.createPolicy(policyCommand);

        policyRepository.save(policy);

        return policy.getPolicyNumber();
    }
}
