package org.helmo.mma.admin.infrastructures.storage;

import org.helmo.mma.admin.domains.dao.CalendarDao;
import org.helmo.mma.admin.domains.storage.DataStorage;
import org.helmo.mma.admin.infrastructures.dao.calendar.CalendarDaoJdbc;
import org.helmo.mma.admin.domains.dao.RoomDao;
import org.helmo.mma.admin.infrastructures.dao.room.RoomDaoJdbc;
import org.helmo.mma.admin.domains.dao.UserDao;
import org.helmo.mma.admin.infrastructures.dao.user.UserDaoJdbc;

import java.sql.Connection;

public class SQLDataStorage implements DataStorage {
    private final UserDao userDao;
    private final RoomDaoJdbc roomDao;
    private final CalendarDaoJdbc calendarDao;

    public SQLDataStorage(Connection connection) {
        this.userDao = new UserDaoJdbc(connection);
        this.roomDao = new RoomDaoJdbc(connection);
        this.calendarDao = new CalendarDaoJdbc(connection);
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
