package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.USER_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

import com.water.demo.ddd.exception.common.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String key) {
        super(USER_NOT_FOUND_ERROR_CODE, format("User <%s> not found!", key));
    }
}
