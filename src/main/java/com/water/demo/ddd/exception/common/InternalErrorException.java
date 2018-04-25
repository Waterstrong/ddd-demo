package com.water.demo.ddd.exception.common;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.INTERNAL_SERVER_ERROR_CODE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalErrorException extends ApplicationException {
    public InternalErrorException(String message) {
        super(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_CODE, message);
    }
}
