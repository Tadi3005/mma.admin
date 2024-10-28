package org.helmo.mma.admin.infrastructures.mapper;

import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.infrastructures.dto.CSVUserDto;

public class UserMapper {
    public User toUser(CSVUserDto userDto) {
        return new User(userDto.matricule(), userDto.fullName(), userDto.email());
    }
}
