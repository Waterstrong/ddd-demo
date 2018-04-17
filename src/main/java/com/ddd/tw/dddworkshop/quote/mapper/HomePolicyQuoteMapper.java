package com.ddd.tw.dddworkshop.quote.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import com.ddd.tw.dddworkshop.quote.command.HomeDetailCommand;
import com.ddd.tw.dddworkshop.quote.model.HomePolicyQuote;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface HomePolicyQuoteMapper {
    HomePolicyQuoteMapper INSTANCE = getMapper(HomePolicyQuoteMapper.class);

    HomePolicyQuote mapToHomePolicyQuote(HomeDetailCommand homeDetailCommand);
}
