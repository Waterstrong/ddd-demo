package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.INCORRECT_EMAIL_ERROR_CODE;
import static java.lang.String.format;

import com.water.demo.ddd.exception.common.BadRequestException;

public class IncorrectEmailException extends BadRequestException {
    public IncorrectEmailException(String email) {
        super(INCORRECT_EMAIL_ERROR_CODE, format("Incorrect email <%s>!", email));
    }
}
