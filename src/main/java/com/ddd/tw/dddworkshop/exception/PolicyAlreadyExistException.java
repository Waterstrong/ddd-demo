package com.ddd.tw.dddworkshop.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;

public class PolicyAlreadyExistException extends DomainException {
    public PolicyAlreadyExistException(String policyNumber, String quoteId) {
        super(CONFLICT, "012", format("Policy <%s> with quote <%s> already exist!", policyNumber, quoteId));
    }
}
