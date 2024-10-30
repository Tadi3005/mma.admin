package org.helmo.mma.admin.domains.reservation;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.validator.ReservationValidator;

import java.util.List;
import java.util.Map;

public class ReservationService {
    private final List<ReservationValidator> reservationValidators;

    public ReservationService(List<ReservationValidator> reservationValidators) {
        this.reservationValidators = reservationValidators;
    }

    public ReservationStatus addReservation(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room) {
        // TODO: Search WorkingDateSlots for the room peut etre faire ce traitement dans le calendar
        for (ReservationValidator reservationValidator : reservationValidators) {
            ReservationStatus status = reservationValidator.validate(reservationRequest, workingDateSlots, users, room);
            if (status != ReservationStatus.SUCCESS) {
                return status;
            }
        }
        return ReservationStatus.SUCCESS;
    }
}
