package org.helmo.mma.admin.domains.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequest(String idRoom,
                                 String matriculeUser,
                                 LocalDate date,
                                 LocalTime start,
                                 LocalTime end,
                                 String description,
                                 int numberOfParticipants)
{

}
