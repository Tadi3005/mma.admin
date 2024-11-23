package org.helmo.mma.admin.domains.storage;


import org.helmo.mma.admin.domains.dao.CalendarDao;
import org.helmo.mma.admin.domains.dao.RoomDao;
import org.helmo.mma.admin.domains.dao.UserDao;

public interface DataStorage {

    UserDao getUserDao();

    RoomDao getRoomDao();

    CalendarDao getCalendarDao();
}
