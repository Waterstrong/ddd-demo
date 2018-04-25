package com.water.demo.ddd.user.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.water.demo.ddd.user.command.RegisterCommand;
import com.water.demo.ddd.user.model.User;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToUser(RegisterCommand command);
}
