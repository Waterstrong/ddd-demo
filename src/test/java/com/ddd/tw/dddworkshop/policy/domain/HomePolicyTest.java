package com.ddd.tw.dddworkshop.policy.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;

public class HomePolicyTest extends PolicyTest {
    @Test
    public void shouldCalculateQuoteGivenHomePolicyInformation() {
        HomePolicy homePolicy = new HomePolicy("钢材", "公寓", "三室及以上", "");

        Quotation quotation = homePolicy.calculateQuote();

        assertThat(quotation.getQuoteId(), is(notNullValue()));
        assertThat(quotation.getPremium(), is(171.6));
    }

    @Test
    public void shouldCalculateQuoteGivenHomePolicyInformationWithMoreDifferentValues() {
        HomePolicy homePolicy1 = new HomePolicy("铝材", "别墅", "二室", "");
        HomePolicy homePolicy2 = new HomePolicy("砖", "Soho", "一室", "");

        Quotation quotation1 = homePolicy1.calculateQuote();
        Quotation quotation2 = homePolicy2.calculateQuote();

        assertThat(quotation1.getQuoteId(), is(notNullValue()));
        assertThat(quotation2.getQuoteId(), is(notNullValue()));
        assertThat(quotation1.getPremium(), is(158.4));
        assertThat(quotation2.getPremium(), is(185.9));
    }

    @Test
    public void shouldCalculateQuoteThrowsExceptionGivenHomePolicyInformationAsUnknown() {
        HomePolicy homePolicy = new HomePolicy("UNKNOWN", "UNKNOWN", "UNKNOWN", "");

        try {
            homePolicy.calculateQuote();
            fail();
        } catch (InvalidQuotationException e) {
            assertInvalidQuotationException(e);
        }
    }
}