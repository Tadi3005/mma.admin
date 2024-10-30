package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;

/**
 * Validator to check if the reservation is valid.
 */
@FunctionalInterface
public interface ReservationValidator {
    /**
     * Validate the reservation.
     * @param reservationRequest the reservation request
     * @param workingDateSlots the working date slots
     * @param users the users
     * @param room the room
     * @return the status of the reservation
     */
    ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room);
}
