package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PastReservationValidator implements ReservationValidator{
    @Override
    public ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room) {
        if (LocalDateTime.of(reservationRequest.date(), reservationRequest.start()).isBefore(LocalDateTime.now())) {
            return ReservationStatus.IN_PAST;
        }
        return ReservationStatus.SUCCESS;
    }
}
