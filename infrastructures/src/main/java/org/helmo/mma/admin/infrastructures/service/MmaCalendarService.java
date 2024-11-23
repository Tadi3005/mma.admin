package org.helmo.mma.admin.infrastructures.service;

import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.service.CalendarService;
import org.helmo.mma.admin.domains.storage.DataStorage;

import java.time.LocalDate;
import java.util.List;

public class MmaCalendarService implements CalendarService {
    private final DataStorage dataStorage;

    public MmaCalendarService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public List<Event> findEventsAt(LocalDate date) {
        return dataStorage.getCalendarDao().findEventsAt(date);
    }

    @Override
    public void addReservation(ReservationRequest reservationRequest) {
        dataStorage.getCalendarDao().addReservation(reservationRequest);
    }
}
