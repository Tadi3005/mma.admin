package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;

/**
 * Validator to check if the room is available.
 */
public class RoomAvailabilityValidator implements ReservationValidator {
    @Override
    public ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room) {
        if (workingDateSlots.isAvailable(reservationRequest.start(), reservationRequest.end())) {
            return ReservationStatus.SUCCESS;
        }
        return ReservationStatus.ROOM_NOT_AVAILABLE;
    }
}
