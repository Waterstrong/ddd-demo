package com.ddd.tw.dddworkshop.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class InvalidQuotationException extends ApiException {
    public InvalidQuotationException() {
        super(BAD_REQUEST, "8000", "Invalid policy information. Failed to calculate the quotation!");
    }
}
