package org.helmo.mma.admin.domains;

import java.time.LocalDate;
import java.time.LocalTime;

public record SearchReservationRequest(LocalDate date, String roomId, LocalTime hour) {

}
