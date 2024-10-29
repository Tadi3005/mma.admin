package org.helmo.mma.admin.domains.reservation.validator;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.util.List;
import java.util.Map;

public class RoomAvailabilityValidator implements ReservationValidator{
    @Override
    public ReservationStatus validate(ReservationRequest reservationRequest, Map<Room, WorkingDateSlots> roomWorkingDateSlots, List<User> users, List<Room> rooms) {
        for (Room room : rooms) {
            if (room.id().equals(reservationRequest.idRoom())) {
                return ReservationStatus.SUCCESS;
            }
        }
        return ReservationStatus.ROOM_NOT_FOUND;
    }
}
