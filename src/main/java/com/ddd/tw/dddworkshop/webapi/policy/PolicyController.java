package com.ddd.tw.dddworkshop.webapi.policy;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ddd.tw.dddworkshop.policy.PolicyApplicationService;
import com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand;
import com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand;

@RestController
public class PolicyController {
    @Autowired
    private PolicyApplicationService applicationService;

    @ResponseStatus(CREATED)
    public String generateHomePolicy() {
        return applicationService.generatePolicy(HomePolicyCommand.builder().build());
    }

    @ResponseStatus(CREATED)
    public String generateCarPolicy() {
        return applicationService.generatePolicy(CarPolicyCommand.builder().build());
    }

}
