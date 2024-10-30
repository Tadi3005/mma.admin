package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;

public class RoomCapacityValidator implements ReservationValidator {
    @Override
    public ReservationStatus validate(ReservationRequest reservationRequest, WorkingDateSlots workingDateSlots, List<User> users, Room room) {
        if (room.getCapacity() < reservationRequest.numberOfParticipants()) {
            return ReservationStatus.ROOM_CAPACITY_NOT_ENOUGH;
        }
        return ReservationStatus.SUCCESS;
    }
}
