package org.helmo.mma.admin.domains;

import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationService;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar {
    private final Map<Room, WorkingDateSlots> roomWorkingDateSlots;
    private final List<Room> rooms;
    private final List<User> users;
    private final ReservationService reservationService;

    public Calendar(List<Room> rooms, List<User> users, ReservationService reservationService) {
        this.rooms = rooms;
        this.users = users;
        this.reservationService = reservationService;
        this.roomWorkingDateSlots = new HashMap<>();
    }

    public void loadCalendar(LocalDate date, List<Event> events) {
        fillSlotsWithEventsAt(date, events);
    }

    private void fillSlotsWithEventsAt(LocalDate date, List<Event> events) {
        for (Room room : rooms) {
            WorkingDateSlots workingDateSlots = new WorkingDateSlots(date);
            workingDateSlots.initSlots();
            for (Event event : events) {
                if (event.hasSame(room)) {
                    workingDateSlots.fillSlot(event);
                }
            }
            roomWorkingDateSlots.put(room, workingDateSlots);
        }
    }

    public ReservationStatus addReservation(ReservationRequest reservationRequest) {
        return reservationService.addReservation(reservationRequest, roomWorkingDateSlots, users, rooms);
    }

    public Map<Room, WorkingDateSlots> getRoomWorkingDateSlots() {
        return roomWorkingDateSlots;
    }
}
