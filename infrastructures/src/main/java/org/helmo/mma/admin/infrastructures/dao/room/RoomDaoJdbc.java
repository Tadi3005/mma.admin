package org.helmo.mma.admin.infrastructures.dao.room;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.dao.RoomDao;
import org.helmo.mma.admin.infrastructures.dto.MySqlRoomDto;
import org.helmo.mma.admin.infrastructures.mapper.RoomMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoJdbc implements RoomDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection;

    public RoomDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MySqlRoomDto mySqlRoomDto = new MySqlRoomDto(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("capacity"));
//                    RoomMapper roomMapper = RoomMapper.INSTANCE;
//                    Room room = roomMapper.toRoom(mySqlRoomDto);
                    Room room = new Room(mySqlRoomDto.id(), mySqlRoomDto.name(), mySqlRoomDto.capacity(), new ArrayList<>());
                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot find all rooms", e);
        }
        return rooms;
    }
}
