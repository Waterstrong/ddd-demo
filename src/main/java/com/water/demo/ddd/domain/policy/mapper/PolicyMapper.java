package com.water.demo.ddd.domain.policy.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import com.water.demo.ddd.domain.policy.command.BuyPolicyCommand;
import com.water.demo.ddd.domain.policy.model.Policy;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface PolicyMapper {
    PolicyMapper INSTANCE = getMapper(PolicyMapper.class);

    Policy mapToPolicy(BuyPolicyCommand CarPolicy);
}
