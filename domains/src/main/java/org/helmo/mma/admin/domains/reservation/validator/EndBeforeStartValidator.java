package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;

public class EndBeforeStartValidator implements ReservationValidator {

    @Override
    public ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots WorkingDateSlots, List<User> users, Room room) {
        if (reservationRequest.start().isAfter(reservationRequest.end())) {
            return ReservationStatus.END_BEFORE_START;
        }
        return ReservationStatus.SUCCESS;
    }
}
