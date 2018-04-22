package com.ddd.tw.dddworkshop.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;

public class EmailAlreadyRegistered extends DomainException {
    public EmailAlreadyRegistered(String email) {
        super(CONFLICT, "017", format("Email <%s> already registered!", email));
    }
}
