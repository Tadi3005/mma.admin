package org.helmo.mma.admin.domains;

import java.time.LocalDate;
import java.time.LocalTime;

public record Event (String id, LocalDate date, LocalTime start, LocalTime end, String description, User organizer, String location) {
    public boolean hasSame(Room room) {
        return this.location.equals(room.id());
    }
}
