package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;

/**
 * Validator to check if the reservation is outside the opening hours.
 */
public class OutsideOpeningValidator implements ReservationValidator {

    @Override
    public ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room) {
        if (reservationRequest.start().isBefore(WorkingDateSlots.START_DAY) || reservationRequest.end().isAfter(WorkingDateSlots.END_DAY)) {
            return ReservationStatus.OUTSIDE_OPENING;
        }
        return ReservationStatus.SUCCESS;
    }
}
