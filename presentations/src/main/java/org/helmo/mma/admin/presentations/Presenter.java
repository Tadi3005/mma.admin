/*
 * This source file is an example
 */
package org.helmo.mma.admin.presentations;

import org.helmo.mma.admin.domains.*;
import org.helmo.mma.admin.domains.repository.CalendarRepository;
import org.helmo.mma.admin.domains.repository.RoomRepository;
import org.helmo.mma.admin.domains.repository.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presenter {
    private final CalendarRepository calendarRepository;
    private final List<Room> rooms;
    private final List<User> users;
    private final View view;
    private final Map<Room, WorkingDateSlots> roomWorkingDateSlots;

    public Presenter(View view, CalendarRepository calendarRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.view = view;
        this.calendarRepository = calendarRepository;
        this.rooms = roomRepository.findAll();
        this.users = userRepository.findAll();
        this.roomWorkingDateSlots = new HashMap<>();
    }

    public void start() {
        fillSlotsWithEventsAt(LocalDate.now());
        loadDailyCalendar(LocalDate.now());
    }

    /**
     * Fill the slots of each room with the events at the given date
     * @param date the date to get the events
     */
    public void fillSlotsWithEventsAt(LocalDate date) {
        List<Event> events = calendarRepository.getEvents(date);
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

    private void loadDailyCalendar(LocalDate date) {
        view.display("Disponnibilites des salles pour le " + date);
        CalendarBuilder calendarBuilder = new CalendarBuilder(roomWorkingDateSlots);
        view.display(calendarBuilder.getCalendar());
    }
}
