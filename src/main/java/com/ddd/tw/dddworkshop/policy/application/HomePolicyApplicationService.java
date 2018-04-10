package com.ddd.tw.dddworkshop.policy.application;

import static com.ddd.tw.dddworkshop.policy.mapper.HomePolicyMapper.INSTANCE;

import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.HomePolicy;
import com.ddd.tw.dddworkshop.policy.domain.PolicyHolder;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;

@Service
public class HomePolicyApplicationService implements PolicyApplicationService<HomePolicyCommand> {
    @Override
    public Quotation calculateQuote(HomePolicyCommand policyCommand) {
        HomePolicy homePolicy = INSTANCE.mapToHomePolicy(policyCommand);

        return homePolicy.calculateQuote();
    }

    @Override
    public String generatePolicy(HomePolicyCommand policyCommand, PolicyHolder policyHolder) {
        return null;
    }
}
