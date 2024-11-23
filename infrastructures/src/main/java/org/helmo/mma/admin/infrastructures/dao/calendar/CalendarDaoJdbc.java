package org.helmo.mma.admin.infrastructures.dao.calendar;


import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.dao.CalendarDao;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class CalendarDaoJdbc implements CalendarDao {

    public CalendarDaoJdbc(Connection connection) {
    }

    @Override
    public List<Event> findEventsAt(LocalDate date) {
        return List.of();
    }

    @Override
    public List<Event> findEventsBetween(LocalDate date, LocalDate endDate) {
        return List.of();
    }

    @Override
    public void addReservation(ReservationRequest reservationRequest) {

    }
}
