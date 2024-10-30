package org.helmo.mma.admin.infrastructures.mapper;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.infrastructures.dto.CSVRoomDto;

import java.util.ArrayList;

/**
 * Mapper to convert a CSV room DTO to a room.
 */
public class RoomMapper {
    /**
     * Convert a CSV room DTO to a room.
     * @param csvRoomDto the CSV room DTO
     * @return the room
     */
    public Room toRoom(CSVRoomDto csvRoomDto) {
        return new Room(
                csvRoomDto.id(),
                csvRoomDto.name(),
                Integer.parseInt(csvRoomDto.capacity()),
                new ArrayList<>()
        );
    }
}
