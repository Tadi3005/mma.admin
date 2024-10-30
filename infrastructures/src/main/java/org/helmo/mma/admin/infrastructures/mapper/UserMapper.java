package org.helmo.mma.admin.infrastructures.mapper;

import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.infrastructures.dto.CSVUserDto;

/**
 * Mapper to convert a CSV user DTO to a user.
 */
public class UserMapper {
    /**
     * Convert a CSV user DTO to a user.
     * @param userDto the CSV user DTO
     * @return the user
     */
    public User toUser(CSVUserDto userDto) {
        return new User(userDto.matricule(), userDto.fullName(), userDto.email());
    }
}
