package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.LOGIN_FAILED_ERROR_CODE;

import com.water.demo.ddd.exception.common.UnauthorizedException;

public class LoginFailedException extends UnauthorizedException {
    public LoginFailedException() {
        super(LOGIN_FAILED_ERROR_CODE, "Password incorrect or not init! Login failed!");
    }
}
