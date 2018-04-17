package com.ddd.tw.dddworkshop.policy.mapper;

import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.ddd.tw.dddworkshop.policy.command.CarPolicyCommand;
import com.ddd.tw.dddworkshop.policy.model.CarPolicy;

@Mapper
public interface CarPolicyMapper {
    CarPolicyMapper INSTANCE = getMapper(CarPolicyMapper.class);

    @Mappings({
            @Mapping(source = "driverDateOfBirth", target = "driverDetail.dateOfBirth"),
            @Mapping(source = "driverGender", target = "driverDetail.gender"),
            @Mapping(target = "policyNumber", ignore = true),
            @Mapping(target = "policyHolder", ignore = true)
    })
    CarPolicy mapToCarPolicy(CarPolicyCommand CarPolicy);
}
