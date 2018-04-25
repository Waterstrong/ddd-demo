package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.POLICY_EXIST_ERROR_CODE;
import static java.lang.String.format;

import com.water.demo.ddd.exception.common.ResourceConflictException;

public class PolicyAlreadyExistException extends ResourceConflictException {
    public PolicyAlreadyExistException(String policyNumber, String quoteId) {
        super(POLICY_EXIST_ERROR_CODE, format("Policy <%s> with quote <%s> already exist!", policyNumber, quoteId));
    }
}
