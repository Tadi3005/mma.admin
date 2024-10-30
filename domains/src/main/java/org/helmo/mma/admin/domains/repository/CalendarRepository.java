package org.helmo.mma.admin.domains.repository;

import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository to access the calendar.
 */
public interface CalendarRepository {
    /**
     * Find the events at a specific date.
     * @param date the date
     * @return the events
     */
    List<Event> findEventsAt(LocalDate date);

    /**
     * Add a reservation.
     * @param reservationRequest the reservation request
     */
    void addReservation(ReservationRequest reservationRequest);
}
