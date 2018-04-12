package com.ddd.tw.dddworkshop.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand;
import com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.CarPolicy;
import com.ddd.tw.dddworkshop.policy.domain.HomePolicy;
import com.ddd.tw.dddworkshop.policy.mapper.CarPolicyMapper;
import com.ddd.tw.dddworkshop.policy.mapper.HomePolicyMapper;
import com.ddd.tw.dddworkshop.policy.repository.PolicyRepository;

@Service
public class PolicyApplicationService {
    @Autowired
    private PolicyRepository policyRepository;

    public String generatePolicy(HomePolicyCommand command) {
        HomePolicy homePolicy = HomePolicyMapper.INSTANCE.mapToHomePolicy(command);
        policyRepository.save(homePolicy);
        return homePolicy.getPolicyNumber();
    }

    public String generatePolicy(CarPolicyCommand command) {
        CarPolicy carPolicy = CarPolicyMapper.INSTANCE.mapToCarPolicy(command);
        policyRepository.save(carPolicy);
        return carPolicy.getPolicyNumber();
    }
}
