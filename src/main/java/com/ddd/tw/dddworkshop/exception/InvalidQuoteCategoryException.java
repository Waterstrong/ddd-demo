package com.ddd.tw.dddworkshop.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class InvalidQuoteCategoryException extends DomainException {
    public InvalidQuoteCategoryException(String category) {
        super(BAD_REQUEST, "008", format("Invalid policy quote category: '%s'. Failed to calculate the quote!", category));
    }
}
