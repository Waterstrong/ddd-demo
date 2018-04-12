package com.ddd.tw.dddworkshop.policy.mapper;

import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.ddd.tw.dddworkshop.policy.command.HomePolicyCommand;
import com.ddd.tw.dddworkshop.policy.domain.HomePolicy;

@Mapper(componentModel = "spring")
public interface HomePolicyMapper {
    HomePolicyMapper INSTANCE = getMapper(HomePolicyMapper.class);

    @Mappings({
            @Mapping(target = "policyNumber", ignore = true),
            @Mapping(target = "policyHolder", ignore = true)
    })
    HomePolicy mapToHomePolicy(HomePolicyCommand homePolicy);
}
