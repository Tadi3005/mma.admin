package org.helmo.mma.admin.infrastructures.storage;

import org.helmo.mma.admin.domains.dao.CalendarDao;
import org.helmo.mma.admin.domains.storage.DataStorage;
import org.helmo.mma.admin.infrastructures.dao.calendar.CalendarDaoIcal;
import org.helmo.mma.admin.domains.dao.RoomDao;
import org.helmo.mma.admin.infrastructures.dao.room.RoomDaoCsv;
import org.helmo.mma.admin.domains.dao.UserDao;
import org.helmo.mma.admin.infrastructures.dao.user.UserDaoCsv;
import org.helmo.mma.admin.infrastructures.mapper.EventMapper;
import org.helmo.mma.admin.infrastructures.mapper.ReservationRequestMapper;
import org.helmo.mma.admin.infrastructures.mapper.RoomMapper;
import org.helmo.mma.admin.infrastructures.mapper.UserMapper;

public class FileDataStorage implements DataStorage {
    private final UserDao userDao;
    private final RoomDao roomDao;
    private final CalendarDao calendarDao;

    public FileDataStorage(String path) {
        try {
            this.userDao = new UserDaoCsv(path + "users.csv", new UserMapper());
            this.roomDao = new RoomDaoCsv(path + "rooms.csv", new RoomMapper());
            this.calendarDao = new CalendarDaoIcal(path + "calendar.ics", new EventMapper(), new ReservationRequestMapper());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the data storage", e);
        }
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public RoomDao getRoomDao() {
        return roomDao;
    }

    @Override
    public CalendarDao getCalendarDao() {
        return calendarDao;
    }
}
