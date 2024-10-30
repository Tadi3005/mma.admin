package org.helmo.mma.admin.domains;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An event
 * @param id the id of the event
 * @param date the date of the event
 * @param start the start time of the event
 * @param end the end time of the event
 * @param description the description of the event
 * @param organizer the organizer of the event
 * @param location the location of the event
 */
public record Event (String id, LocalDate date, LocalTime start, LocalTime end, String description, User organizer, String location) {
    public static final Event NOT_FOUND = new Event("NOT_FOUND", LocalDate.now(), LocalTime.now(), LocalTime.now(), "NOT_FOUND", new User("event@notfound"), "NOT_FOUND");

    /**
     * Checks if the event is in the same location as the given room.
     * @param room the room to compare with
     * @return true if the event location matches the room ID, false otherwise
     */
    public boolean hasSame(Room room) {
        return this.location.equals(room.getId());
    }

    /**
     * Checks if the event is during the given date and time.
     * @param date the date
     * @param time the time
     * @return true if the event is during the given date and time, false otherwise
     */
    public boolean isDuring(LocalDate date, LocalTime time) {
        return this.date.isEqual(date) && (time.equals(start) || (time.isAfter(start) && time.isBefore(end)) || time.equals(end));
    }
}
