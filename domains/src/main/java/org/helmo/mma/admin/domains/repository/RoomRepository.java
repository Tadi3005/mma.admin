package org.helmo.mma.admin.domains.repository;

import org.helmo.mma.admin.domains.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> findAll();
}
