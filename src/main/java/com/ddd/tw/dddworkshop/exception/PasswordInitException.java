package com.ddd.tw.dddworkshop.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class PasswordInitException extends DomainException {
    public PasswordInitException() {
        super(CONFLICT, "019", "Password already init!");
    }
}
