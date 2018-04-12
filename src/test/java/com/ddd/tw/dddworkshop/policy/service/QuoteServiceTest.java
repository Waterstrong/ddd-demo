package com.ddd.tw.dddworkshop.policy.service;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ddd.tw.dddworkshop.exception.InvalidQuotationException;
import com.ddd.tw.dddworkshop.policy.domain.CarDetails;
import com.ddd.tw.dddworkshop.policy.domain.HomeDetails;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;
import com.ddd.tw.dddworkshop.policy.repository.QuoteRateRepository;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceTest {
    @InjectMocks
    private QuoteService quoteService;

    @Mock
    private QuoteRateRepository quoteRateRepository;

    @Before
    public void setUp() {
        when(quoteRateRepository.getRate("钢材")).thenReturn(1.2);
        when(quoteRateRepository.getRate("公寓")).thenReturn(1.1);
        when(quoteRateRepository.getRate("三室及以上")).thenReturn(1.3);
        when(quoteRateRepository.getRate("2010-2014")).thenReturn(1.2);
        when(quoteRateRepository.getRate("BMW C650")).thenReturn(1.6);
        when(quoteRateRepository.getRate("Street")).thenReturn(1.6);
        when(quoteRateRepository.getRate("750-1499km")).thenReturn(1.2);
        when(quoteRateRepository.getRate("UNKNOWN")).thenReturn(null);
    }

    @Test
    public void shouldCalculateQuoteGivenHomeDetailsInformation() {
        HomeDetails homeDetails = new HomeDetails("钢材", "公寓", "三室及以上");

        Quotation quotation = quoteService.calculateQuote(homeDetails);

        assertThat(quotation.getQuoteId(), is(notNullValue()));
        assertThat(quotation.getPremium(), is(171.6));
    }

    @Test
    public void shouldCalculateQuoteThrowsExceptionGivenHomeDetailsInformationAsUnknown() {
        HomeDetails homeDetails = new HomeDetails("UNKNOWN", "UNKNOWN", "UNKNOWN");

        try {
            quoteService.calculateQuote(homeDetails);
            fail();
        } catch (InvalidQuotationException e) {
            assertInvalidQuotationException(e);
        }
    }

    @Test
    public void shouldCalculateQuoteGivenCarDetailsInformation() {
        CarDetails carDetails = new CarDetails("2010-2014", "BMW", "C650", "Street", "750-1499km");

        Quotation quotation = quoteService.calculateQuote(carDetails);

        assertThat(quotation.getQuoteId(), is(notNullValue()));
        assertThat(quotation.getPremium(), is(368.64));
    }

    @Test
    public void shouldCalculateQuoteThrowsInvalidQuotationGivenCarDetailsInformationAsUnknown() {
        CarDetails carDetails = new CarDetails("2010-2014", "BMW", "C650", "UNKNOWN", "UNKNOWN");

        try {
            quoteService.calculateQuote(carDetails);
            fail();
        } catch (InvalidQuotationException e) {
            assertInvalidQuotationException(e);
        }
    }

    private void assertInvalidQuotationException(InvalidQuotationException e) {
        assertThat(e.getStatus(), is(BAD_REQUEST));
        assertThat(e.getCode(), is("8000"));
        assertThat(e.getMessage(), is("Invalid policy information. Failed to calculate the quotation!"));
    }
}