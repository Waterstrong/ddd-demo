package com.ddd.tw.dddworkshop.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class IncorrectEmailException extends DomainException {
    public IncorrectEmailException(String email) {
        super(BAD_REQUEST, "015", format("Incorrect email <%s>!", email));
    }
}
