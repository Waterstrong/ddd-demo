package com.ddd.tw.dddworkshop.policy.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;

public class CarPolicyTest extends PolicyTest {
    @Test
    public void shouldCalculateQuoteGivenCarPolicyInformation() {
        CarPolicy carPolicy = new CarPolicy("2010-2014", "BMW", "C650", "Street", "750-1499km", null);

        Quotation quotation = carPolicy.calculateQuote();

        assertThat(quotation.getQuoteId(), is(notNullValue()));
        assertThat(quotation.getPremium(), is(368.64));
    }

    @Test
    public void shouldCalculateQuoteThrowsInvalidQuotationGivenCarPolicyInformationAsUnknown() {
        CarPolicy carPolicy = new CarPolicy("2010-2014", "BMW", "C650", "UNKNOWN", "UNKNOWN", null);

        try {
            carPolicy.calculateQuote();
            fail();
        } catch (InvalidQuotationException e) {
            assertInvalidQuotationException(e);
        }
    }
}