package com.water.demo.ddd.quote.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.mapstruct.factory.Mappers.getMapper;

import org.mapstruct.Mapper;
import com.water.demo.ddd.quote.command.GenerateHomePolicyQuoteCommand;
import com.water.demo.ddd.quote.model.HomePolicyQuote;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface HomePolicyQuoteMapper {
    HomePolicyQuoteMapper INSTANCE = getMapper(HomePolicyQuoteMapper.class);

    HomePolicyQuote mapToHomePolicyQuote(GenerateHomePolicyQuoteCommand generateHomePolicyQuoteCommand);
}
