package org.helmo.mma.admin.domains.dao;

import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;

import java.time.LocalDate;
import java.util.List;

public interface CalendarDao {
    /**
     * Find the events at a specific date.
     * @param date the date
     * @return the events
     */
    List<Event> findEventsAt(LocalDate date);

    /**
     * Find the events between two dates.
     * @param date the start date
     * @param endDate the end date
     * @return the events
     */
    List<Event> findEventsBetween(LocalDate date, LocalDate endDate);

    /**
     * Add a reservation.
     * @param reservationRequest the reservation request
     */
    void addReservation(ReservationRequest reservationRequest);
}
