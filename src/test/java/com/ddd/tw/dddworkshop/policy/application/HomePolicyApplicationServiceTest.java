package com.ddd.tw.dddworkshop.policy.application;

import static com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand.builder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;

@RunWith(MockitoJUnitRunner.class)
public class HomePolicyApplicationServiceTest {
    @InjectMocks
    private HomePolicyApplicationService service;

    @Test
    @Ignore
    public void shouldCalculateQuoteGivenHomePolicyInformation() {
        HomePolicyCommand homePolicyCommand = builder()
                .constructionMaterial("钢材")
                .buildingType("公寓")
                .bedroomsType("三室及以上")
                .build();

        Quotation quotation = service.calculateQuote(homePolicyCommand);

        assertThat(quotation.getQuoteId(), is(nullValue()));
        assertThat(quotation.getPremium(), is(171.6));
    }

    @Test
    public void shouldCalculateQuoteReturnsNullGivenHomePolicyCommandAsEmpty() {
        HomePolicyCommand homePolicyCommand = builder().build();

        Quotation quotation = service.calculateQuote(homePolicyCommand);

        assertThat(quotation, is(nullValue()));
    }

}