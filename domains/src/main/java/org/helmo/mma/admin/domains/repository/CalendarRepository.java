package org.helmo.mma.admin.domains.repository;

import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository {
    List<Event> findEventsAt(LocalDate date);
    void addReservation(ReservationRequest reservationRequest);
}
