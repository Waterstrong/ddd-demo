package com.ddd.tw.dddworkshop.policy;

import static com.ddd.tw.dddworkshop.utils.constant.Constants.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ddd.tw.dddworkshop.policy.command.PolicyCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/policy", consumes = CONTENT_TYPE)
@Api(tags = "Policy", description = "Policy Resource", consumes = CONTENT_TYPE)
public class PolicyController {
    @Autowired
    private PolicyApplicationService applicationService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Buy Policy", notes = "This is for buying policy")
    public HttpEntity<String> buyPolicy(@Valid @RequestBody PolicyCommand homePolicy) {
        String policyNumber = applicationService.buyPolicy(homePolicy);
        return new HttpEntity<>(policyNumber);
    }
}
