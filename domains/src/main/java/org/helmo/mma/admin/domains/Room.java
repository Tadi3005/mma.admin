package org.helmo.mma.admin.domains;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room
 */
public class Room {
    private final String id;
    private final String name;
    private final int capacity;
    private final List<Event> events;

    public static final Room NOT_FOUND = new Room("NOT_FOUND", "NOT_FOUND", 0, List.of());

    /**
     * To create a room with events
     * @param id the id of the room
     * @param name the name of the room
     * @param capacity the capacity of the room
     * @param events the events of the room
     */
    public Room(String id, String name, int capacity, List<Event> events) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.events = events;
    }

    /**
     * To add an event to the room
     * @param event the event to add
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * To get the id of the room
     * @return the id of the room
     */
    public String getId() {
        return id;
    }

    /**
     * To get the name of the room
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * To get the capacity of the room
     * @return the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * To get the events of the room
     * @param date the date
     * @param time the time
     * @return the event at the given date and time
     */
    public Event findEventAt(LocalDate date, LocalTime time) {
        return events.stream()
                .filter(event -> event.isDuring(date, time))
                .findFirst()
                .orElse(Event.NOT_FOUND);
    }
}
