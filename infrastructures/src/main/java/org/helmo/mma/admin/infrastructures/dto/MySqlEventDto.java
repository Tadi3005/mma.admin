package org.helmo.mma.admin.infrastructures.dto;

public record MySqlEventDto(String id, String date, String start, String end, String description, MySqlUserDto organizer, String location) {

}
