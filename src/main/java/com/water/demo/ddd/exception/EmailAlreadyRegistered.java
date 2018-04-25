package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.EMAIL_REGISTERED_ERROR_CODE;
import static java.lang.String.format;

import com.water.demo.ddd.exception.common.ResourceConflictException;

public class EmailAlreadyRegistered extends ResourceConflictException {
    public EmailAlreadyRegistered(String email) {
        super(EMAIL_REGISTERED_ERROR_CODE, format("Email <%s> already registered!", email));
    }
}
