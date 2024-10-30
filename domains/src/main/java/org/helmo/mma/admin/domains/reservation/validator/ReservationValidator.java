package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface ReservationValidator {
    ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room);
}
