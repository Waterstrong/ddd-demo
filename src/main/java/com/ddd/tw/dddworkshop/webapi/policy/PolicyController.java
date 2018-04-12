package com.ddd.tw.dddworkshop.webapi.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.ddd.tw.dddworkshop.policy.application.PolicyApplicationService;
import com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand;
import com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand;

@RestController
public class PolicyController {
    @Autowired
    private PolicyApplicationService applicationService;

    public String generateHomePolicy() {
        return applicationService.generatePolicy(HomePolicyCommand.builder().build());
    }

    public String generateCarPolicy() {
        return applicationService.generatePolicy(CarPolicyCommand.builder().build());
    }

}
