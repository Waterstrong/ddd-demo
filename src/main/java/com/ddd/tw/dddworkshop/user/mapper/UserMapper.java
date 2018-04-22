package com.ddd.tw.dddworkshop.user.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ddd.tw.dddworkshop.user.command.RegisterCommand;
import com.ddd.tw.dddworkshop.user.model.User;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToUser(RegisterCommand command);
}
