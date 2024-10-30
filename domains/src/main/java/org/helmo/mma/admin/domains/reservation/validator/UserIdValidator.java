package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;
import java.util.Map;

/**
 * Validator to check if the user id is valid.
 */
public class UserIdValidator implements ReservationValidator {
    @Override
    public ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room) {
        for (User user : users) {
            if (user.matricule().equals(reservationRequest.matriculeUser())) {
                return ReservationStatus.SUCCESS;
            }
        }
        return ReservationStatus.USER_NOT_FOUND;
    }
}
