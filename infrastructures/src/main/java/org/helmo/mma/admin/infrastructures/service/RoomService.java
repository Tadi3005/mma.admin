package org.helmo.mma.admin.infrastructures.service;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.storage.DataStorage;

import java.util.List;

public class RoomService {
    private final DataStorage dataStorage;

    public RoomService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public List<Room> findAll() {
        return dataStorage.getRoomDao().findAll();
    }
}
