package org.helmo.mma.admin.infrastructures.mapper;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.infrastructures.dto.CSVRoomDto;

import java.util.ArrayList;

public class RoomMapper {
    public Room toRoom(CSVRoomDto csvRoomDto) {
        return new Room(
                csvRoomDto.id(),
                csvRoomDto.name(),
                Integer.parseInt(csvRoomDto.capacity()),
                new ArrayList<>()
        );
    }
}
