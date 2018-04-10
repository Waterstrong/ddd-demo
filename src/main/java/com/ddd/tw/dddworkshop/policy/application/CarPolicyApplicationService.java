package com.ddd.tw.dddworkshop.policy.application;

import static com.ddd.tw.dddworkshop.policy.mapper.CarPolicyMapper.INSTANCE;

import com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.CarPolicy;
import com.ddd.tw.dddworkshop.policy.domain.PolicyHolder;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;

public class CarPolicyApplicationService implements PolicyApplicationService<CarPolicyCommand> {
    @Override
    public Quotation calculateQuote(CarPolicyCommand policyCommand) {
        CarPolicy carPolicy = INSTANCE.mapToCarPolicy(policyCommand);

        return carPolicy.calculateQuote();
    }

    @Override
    public String generatePolicy(CarPolicyCommand policyCommand, PolicyHolder policyHolder) {
        return null;
    }
}
