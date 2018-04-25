package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.QUOTE_NOT_FOUND_ERROR_CODE;
import static java.lang.String.format;

import com.water.demo.ddd.exception.common.ResourceNotFoundException;

public class QuoteNotFoundException extends ResourceNotFoundException {
    public QuoteNotFoundException(String quoteId) {
        super(QUOTE_NOT_FOUND_ERROR_CODE, format("Quote <%s> does not exist!", quoteId));
    }
}
