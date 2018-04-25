package com.water.demo.ddd.exception;

import static com.water.demo.ddd.exception.common.ApplicationErrorCodes.INVALID_QUOTE_CATEGORY_ERROR_CODE;
import static java.lang.String.format;

import com.water.demo.ddd.exception.common.BadRequestException;

public class InvalidQuoteCategoryException extends BadRequestException {
    public InvalidQuoteCategoryException(String category) {
        super(INVALID_QUOTE_CATEGORY_ERROR_CODE, format("Invalid policy quote category: '%s'. Failed to calculate the quote!", category));
    }
}
