package com.ddd.tw.dddworkshop.policy.application;

import static com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand.builder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;

@RunWith(MockitoJUnitRunner.class)
public class CarPolicyApplicationServiceTest {
    @InjectMocks
    private CarPolicyApplicationService service;

    @Test
    @Ignore
    public void shouldCalculateQuoteGivenCarPolicyInformation() {
        CarPolicyCommand carPolicyCommand = builder()
                .yearOfMake("2010-2014")
                .brand("DUCATI")
                .model("1299")
                .parkingAddress("Drive way")
                .kilosEachYear("750-1499km")
                .build();

        Quotation quotation = service.calculateQuote(carPolicyCommand);

        assertThat(quotation.getQuoteId(), is(nullValue()));
        assertThat(quotation.getPremium(), is(276.48));
    }

    @Test
    public void shouldCalculateQuoteReturnsNullGivenCarPolicyCommandAsEmpty() {
        CarPolicyCommand carPolicyCommand = builder().build();

        Quotation quotation = service.calculateQuote(carPolicyCommand);

        assertThat(quotation, is(nullValue()));
    }


}