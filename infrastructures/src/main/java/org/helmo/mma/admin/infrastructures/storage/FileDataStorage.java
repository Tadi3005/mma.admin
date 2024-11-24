package org.helmo.mma.admin.infrastructures.storage;

import org.helmo.mma.admin.domains.dao.CalendarDao;
import org.helmo.mma.admin.domains.storage.DataStorage;
import org.helmo.mma.admin.infrastructures.dao.calendar.CalendarDaoIcal;
import org.helmo.mma.admin.domains.dao.RoomDao;
import org.helmo.mma.admin.infrastructures.dao.room.RoomDaoCsv;
import org.helmo.mma.admin.domains.dao.UserDao;
import org.helmo.mma.admin.infrastructures.dao.user.UserDaoCsv;
import org.helmo.mma.admin.infrastructures.mapper.ical.EventMapperIcal;
import org.helmo.mma.admin.infrastructures.mapper.ical.ReservationRequestMapperIcal;
import org.helmo.mma.admin.infrastructures.mapper.csv.RoomMapperCsv;
import org.helmo.mma.admin.infrastructures.mapper.csv.UserMapperCsv;

public class FileDataStorage implements DataStorage {
    private final UserDao userDao;
    private final RoomDao roomDao;
    private final CalendarDao calendarDao;

    public FileDataStorage(String path) {
        this.userDao = new UserDaoCsv(path + "users.csv", new UserMapperCsv());
        this.roomDao = new RoomDaoCsv(path + "rooms.csv", new RoomMapperCsv());
        this.calendarDao = new CalendarDaoIcal(path + "calendar.ics", new EventMapperIcal(), new ReservationRequestMapperIcal());
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
