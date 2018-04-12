package com.ddd.tw.dddworkshop.policy.mapper;

import static com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand.builder;
import static com.ddd.tw.dddworkshop.policy.mapper.HomePolicyMapper.INSTANCE;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.HomePolicy;

public class HomePolicyMapperTest {
    @Test
    @Ignore
    public void shouldMapHomePolicyCommandToHomePolicy() {
        HomePolicyCommand homePolicyCommand = builder()
                .policyStartDate("2018-04-10")
                .constructionMaterial("钢材")
                .buildingType("别墅")
                .bedroomsType("三室及以上")
                .address("Address Line")
                .quoteId("123456")
                .quotePremium(1024.0)
                .build();

        HomePolicy homePolicy = INSTANCE.mapToHomePolicy(homePolicyCommand);

        assertThat(homePolicy.getPolicyNumber(), is(nullValue()));
        assertThat(homePolicy.getPolicyStartDate(), is("2018-04-10"));
        assertThat(homePolicy.getQuoteId(), is("123456"));
        assertThat(homePolicy.getPolicyHolder(), is(nullValue()));
        assertThat(homePolicy.getConstructionMaterial(), is("钢材"));
        assertThat(homePolicy.getBuildingType(), is("别墅"));
        assertThat(homePolicy.getBedroomsType(), is("三室及以上"));
        assertThat(homePolicy.getAddress(), is("Address Line"));
    }
}