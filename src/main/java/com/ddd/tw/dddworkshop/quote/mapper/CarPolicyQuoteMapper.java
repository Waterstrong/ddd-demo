package com.ddd.tw.dddworkshop.quote.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import com.ddd.tw.dddworkshop.quote.command.CarDetailCommand;
import com.ddd.tw.dddworkshop.quote.model.CarPolicyQuote;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface CarPolicyQuoteMapper {
    CarPolicyQuoteMapper INSTANCE = getMapper(CarPolicyQuoteMapper.class);

    CarPolicyQuote mapToCarPolicyQuote(CarDetailCommand carDetailCommand);
}
