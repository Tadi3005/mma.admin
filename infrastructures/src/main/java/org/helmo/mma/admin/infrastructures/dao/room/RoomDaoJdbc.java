package org.helmo.mma.admin.infrastructures.dao.room;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.dao.RoomDao;

import java.sql.Connection;
import java.util.List;

public class RoomDaoJdbc implements RoomDao {

    public RoomDaoJdbc(Connection connection) {
    }

    @Override
    public List<Room> findAll() {
        return List.of();
    }
}
