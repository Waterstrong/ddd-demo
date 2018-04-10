package com.ddd.tw.dddworkshop.policy.mapper;

import static com.ddd.tw.dddworkshop.policy.mapper.CarPolicyMapper.INSTANCE;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.CarPolicy;

public class CarPolicyMapperTest {

    @Test
    public void shouldMapCarPolicyCommandToCarPolicy() {
        CarPolicyCommand carPolicyCommand = CarPolicyCommand.builder()
                .policyStartDate("2018-04-10")
                .quoteId("123456").quotePremium(1024.0)
                .yearOfMake("2010-2014").brand("BMW").model("C650")
                .parkingAddress("Street").kilosEachYear("750-1499km")
                .driverDateOfBirth("1980-10-15").driverGender("Female")
                .build();

        CarPolicy carPolicy = INSTANCE.mapToCarPolicy(carPolicyCommand);

        assertThat(carPolicy.getPolicyStartDate(), is("2018-04-10"));
        assertThat(carPolicy.getPolicyNumber(), is(nullValue()));
        assertThat(carPolicy.getQuotation().getQuoteId(), is("123456"));
        assertThat(carPolicy.getQuotation().getPremium(), is(1024.0));
        assertThat(carPolicy.getYearOfMake(), is("2010-2014"));
        assertThat(carPolicy.getBrand(), is("BMW"));
        assertThat(carPolicy.getModel(), is("C650"));
        assertThat(carPolicy.getParkingAddress(), is("Street"));
        assertThat(carPolicy.getKilosEachYear(), is("750-1499km"));
        assertThat(carPolicy.getDriverDetail().getDateOfBirth(), is("1980-10-15"));
        assertThat(carPolicy.getDriverDetail().getGender(), is("Female"));
    }
}