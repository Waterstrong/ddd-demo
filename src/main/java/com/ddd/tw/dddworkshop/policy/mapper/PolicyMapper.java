package com.ddd.tw.dddworkshop.policy.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import com.ddd.tw.dddworkshop.policy.command.BuyPolicyCommand;
import com.ddd.tw.dddworkshop.policy.model.Policy;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface PolicyMapper {
    PolicyMapper INSTANCE = getMapper(PolicyMapper.class);

    Policy mapToPolicy(BuyPolicyCommand CarPolicy);
}
