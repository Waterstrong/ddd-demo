package com.ddd.tw.dddworkshop.policy.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;

public abstract class PolicyTest {
    protected void assertInvalidQuotationException(InvalidQuotationException e) {
        assertThat(e.getStatus(), is(BAD_REQUEST));
        assertThat(e.getCode(), is("8000"));
        assertThat(e.getMessage(), is("Invalid policy information. Failed to calculate the quotation!"));
    }
}
