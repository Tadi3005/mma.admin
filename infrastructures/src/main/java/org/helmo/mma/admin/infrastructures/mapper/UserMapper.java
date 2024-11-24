package org.helmo.mma.admin.infrastructures.mapper;

import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.infrastructures.dto.MySqlUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    MySqlUserDto toMySqlUserDto(User user);

    User toUser(MySqlUserDto mySqlUserDto);
}
