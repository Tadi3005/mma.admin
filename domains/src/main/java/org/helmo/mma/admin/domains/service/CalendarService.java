package org.helmo.mma.admin.domains.service;

import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {
    List<Event> findEventsAt(LocalDate date);

    void addReservation(ReservationRequest reservationRequest);
}
