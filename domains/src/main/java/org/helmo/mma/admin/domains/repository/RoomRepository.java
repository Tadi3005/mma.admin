package org.helmo.mma.admin.domains.repository;

import org.helmo.mma.admin.domains.Room;

import java.util.List;

/**
 * Repository to access the rooms.
 */
public interface RoomRepository {
    /**
     * Find all the rooms.
     * @return the rooms
     */
    List<Room> findAll();
}
