package com.ddd.tw.dddworkshop.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String key) {
        super(NOT_FOUND, "018", format("User <%s> not found!", key));
    }
}
