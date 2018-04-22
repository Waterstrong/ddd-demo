package com.ddd.tw.dddworkshop.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class LoginFailedException extends DomainException {
    public LoginFailedException() {
        super(UNAUTHORIZED, "020", "Password incorrect or not init! Login failed!");
    }
}
