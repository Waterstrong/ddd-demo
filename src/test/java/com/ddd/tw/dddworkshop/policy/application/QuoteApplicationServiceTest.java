package com.ddd.tw.dddworkshop.policy.application;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ddd.tw.dddworkshop.quote.domain.CarDetails;
import com.ddd.tw.dddworkshop.quote.domain.HomeDetails;
import com.ddd.tw.dddworkshop.quote.domain.Quotation;
import com.ddd.tw.dddworkshop.policy.service.QuoteService;
import com.ddd.tw.dddworkshop.quote.QuoteApplicationService;

@RunWith(MockitoJUnitRunner.class)
public class QuoteApplicationServiceTest {
    @InjectMocks
    private QuoteApplicationService applicationService;

    @Mock
    private QuoteService quoteService;

    @Test
    public void shouldDelegateToQuoteServiceToCalculateHomePolicyQuoteSuccessfully() {
        HomeDetails homeDetails = new HomeDetails();
        Quotation expectedQuote = new Quotation();
        when(quoteService.calculateQuote(homeDetails)).thenReturn(expectedQuote);

        Quotation quotation = applicationService.calculateQuote(homeDetails);

        assertThat(quotation, is(expectedQuote));
    }

    @Test
    public void shouldDelegateToQuoteServiceToCalculateCarPolicyQuoteSuccessfully() {
        CarDetails carDetails = new CarDetails();
        Quotation expectedQuote = new Quotation();
        when(quoteService.calculateQuote(carDetails)).thenReturn(expectedQuote);

        Quotation quotation = applicationService.calculateQuote(carDetails);

        assertThat(quotation, is(expectedQuote));
    }
}