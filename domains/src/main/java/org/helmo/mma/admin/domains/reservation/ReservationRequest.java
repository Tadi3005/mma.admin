package org.helmo.mma.admin.domains.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Request to make a reservation.
 * @param idRoom the id of the room
 * @param matriculeUser the matricule of the user
 * @param date the date of the reservation
 * @param start the start time of the reservation
 * @param end the end time of the reservation
 * @param description the description of the reservation
 * @param numberOfParticipants the number of participants
 */
public record ReservationRequest(String idRoom,
                                 String matriculeUser,
                                 LocalDate date,
                                 LocalTime start,
                                 LocalTime end,
                                 String description,
                                 int numberOfParticipants)
{

}
