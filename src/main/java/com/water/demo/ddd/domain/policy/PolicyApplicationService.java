package com.water.demo.ddd.domain.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.water.demo.ddd.domain.policy.command.BuyPolicyCommand;
import com.water.demo.ddd.domain.policy.model.Policy;
import com.water.demo.ddd.domain.policy.repository.PolicyRepository;
import com.water.demo.ddd.domain.policy.service.PolicyFactory;

@Service
@Transactional
public class PolicyApplicationService {
    @Autowired
    private PolicyFactory policyFactory;

    @Autowired
    private PolicyRepository policyRepository;

    public String buyPolicy(BuyPolicyCommand command) {
        Policy policy = policyFactory.createPolicy(command);

        policyRepository.save(policy);

        return policy.getPolicyNumber();
    }
}
