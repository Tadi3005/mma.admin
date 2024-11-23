package org.helmo.mma.admin.domains.dao;

import org.helmo.mma.admin.domains.Room;

import java.util.List;

public interface RoomDao {
    List<Room> findAll();
}
