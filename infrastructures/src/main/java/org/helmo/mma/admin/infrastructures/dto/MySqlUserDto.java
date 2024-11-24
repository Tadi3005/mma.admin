package org.helmo.mma.admin.infrastructures.dto;

public record MySqlUserDto(
        String matricule,
        String fullName,
        String email
) {
}
