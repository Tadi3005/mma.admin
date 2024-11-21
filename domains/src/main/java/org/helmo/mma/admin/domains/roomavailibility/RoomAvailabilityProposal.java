package org.helmo.mma.admin.domains.roomavailibility;

import org.helmo.mma.admin.domains.Room;

import java.time.LocalDate;
import java.time.LocalTime;

public record RoomAvailabilityProposal(Room room, LocalDate date, LocalTime start, LocalTime end) {
}
