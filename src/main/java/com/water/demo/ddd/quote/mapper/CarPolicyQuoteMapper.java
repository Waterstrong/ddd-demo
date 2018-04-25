package com.water.demo.ddd.quote.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import com.water.demo.ddd.quote.command.GenerateCarPolicyQuoteCommand;
import com.water.demo.ddd.quote.model.CarPolicyQuote;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface CarPolicyQuoteMapper {
    CarPolicyQuoteMapper INSTANCE = getMapper(CarPolicyQuoteMapper.class);

    CarPolicyQuote mapToCarPolicyQuote(GenerateCarPolicyQuoteCommand generateCarPolicyQuoteCommand);
}
