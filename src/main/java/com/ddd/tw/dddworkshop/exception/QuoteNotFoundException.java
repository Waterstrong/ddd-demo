package com.ddd.tw.dddworkshop.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class QuoteNotFoundException extends DomainException {
    public QuoteNotFoundException(String quoteId) {
        super(NOT_FOUND, "010", format("Quote <%s> does not exist!", quoteId));
    }
}
