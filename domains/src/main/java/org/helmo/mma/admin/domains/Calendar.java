package org.helmo.mma.admin.domains;

import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationService;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The calendar of the application
 */
public class Calendar {
    private final Map<Room, WorkingDateSlots> roomWorkingDateSlots;
    private final List<Room> rooms;
    private final List<User> users;
    private final ReservationService reservationService;

    /**
     * Create a calendar with rooms, users and a reservation service
     * @param rooms the rooms
     * @param users the users
     * @param reservationService the reservation service
     */
    public Calendar(List<Room> rooms, List<User> users, ReservationService reservationService) {
        this.rooms = rooms;
        this.users = users;
        this.reservationService = reservationService;
        this.roomWorkingDateSlots = new HashMap<>();
    }

    /**
     * Load the calendar with a date and events
     * @param date the date of the calendar
     * @param events the events of the calendar
     */
    public void loadCalendar(LocalDate date, List<Event> events) {
        fillSlotsWithEventsAt(date, events);
    }

    /**
     * Fill the slots of the rooms with events
     * @param date the date of the events
     * @param events the events to fill the slots with
     */
    private void fillSlotsWithEventsAt(LocalDate date, List<Event> events) {
        for (Room room : rooms) {
            WorkingDateSlots workingDateSlots = new WorkingDateSlots(date);
            workingDateSlots.initSlots();

            events.stream()
                    .filter(event -> event.hasSame(room))
                    .peek(room::addEvent)
                    .forEach(workingDateSlots::fillSlot);

            roomWorkingDateSlots.put(room, workingDateSlots);
        }
    }

    /**
     * Add a reservation to the calendar
     * @param reservationRequest the reservation request
     * @return the status of the reservation
     */
    public ReservationStatus addReservation(ReservationRequest reservationRequest) {
        Room room = rooms.stream().filter(r -> r.getId().equals(reservationRequest.idRoom())).findFirst().orElse(Room.NOT_FOUND);
        if (room == Room.NOT_FOUND) {
            return ReservationStatus.ROOM_NOT_FOUND;
        }
        WorkingDateSlots workingDateSlots = roomWorkingDateSlots.get(room);
        return reservationService.addReservation(reservationRequest, workingDateSlots, users, room);
    }

    /**
     * Get the room working date slots
     * @return the room working date slots
     */
    public Map<Room, WorkingDateSlots> getRoomWorkingDateSlots() {
        return roomWorkingDateSlots;
    }

    /**
     * Find an event in the calendar
     * @param searchReservationRequest the search reservation request
     * @return the event found or Event.NOT_FOUND
     */
    public Event findEvent(SearchReservationRequest searchReservationRequest) {
        Room room = rooms.stream().filter(r -> r.getId().equals(searchReservationRequest.roomId())).findFirst().orElse(Room.NOT_FOUND);
        if (room == Room.NOT_FOUND) {
            return Event.NOT_FOUND;
        }
        return room.findEventAt(searchReservationRequest.date(), searchReservationRequest.hour());
    }

    /**
     * Find a user by its matricule
     * @param user the user to find
     * @return the user found or User.NOT_FOUND
     */
    public User findUser(User user) {
        return users.stream()
                .filter(u -> u.matricule().equals(user.email()) || u.matricule().equals(user.matricule()))
                .findFirst()
                .orElse(User.NOT_FOUND);
    }
}
