package org.helmo.mma.admin.domains.reservation;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.validator.ReservationValidator;

import java.util.List;

/**
 * Service to manage the reservations.
 */
public class ReservationService {
    private final List<ReservationValidator> reservationValidators;

    /**
     * Create a reservation service with reservation validators.
     * @param reservationValidators the reservation validators
     */
    public ReservationService(List<ReservationValidator> reservationValidators) {
        this.reservationValidators = reservationValidators;
    }

    /**
     * Add a reservation.
     * @param reservationRequest the reservation request
     * @param workingDateSlots the working date slots
     * @param users the users
     * @param room the room
     * @return the status of the reservation
     */
    public ReservationStatus addReservation(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room) {
        for (ReservationValidator reservationValidator : reservationValidators) {
            ReservationStatus status = reservationValidator.validate(reservationRequest, workingDateSlots, users, room);
            if (status != ReservationStatus.SUCCESS) {
                return status;
            }
        }
        return ReservationStatus.SUCCESS;
    }
}
