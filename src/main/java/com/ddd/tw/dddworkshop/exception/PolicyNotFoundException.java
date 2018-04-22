package com.ddd.tw.dddworkshop.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class PolicyNotFoundException extends DomainException {
    public PolicyNotFoundException(String policyNumber) {
        super(NOT_FOUND, "013", format("Policy <%s> not found!", policyNumber));
    }
}
