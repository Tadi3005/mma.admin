package org.helmo.mma.admin.domains.roomavailibility;
import java.time.Duration;
import java.time.LocalDate;

public record SearchRoomAvailabilityRequest(LocalDate date, Integer numberOfPeople, Duration duration) {
}
