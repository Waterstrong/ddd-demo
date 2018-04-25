package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.POLICY_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

import com.water.demo.ddd.exception.common.ResourceNotFoundException;

public class PolicyNotFoundException extends ResourceNotFoundException {
    public PolicyNotFoundException(String policyNumber) {
        super(POLICY_NOT_FOUND_ERROR_CODE, format("Policy <%s> not found!", policyNumber));
    }
}
