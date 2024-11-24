package org.helmo.mma.admin.infrastructures.mapper;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.infrastructures.dto.MySqlRoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    MySqlRoomDto toMySqlRoomDto(Room room);

    Room toRoom(MySqlRoomDto mySqlRoomDto);
}
