package org.helmo.mma.admin.domains.roomavailibility;
import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.Slot;
import org.helmo.mma.admin.domains.WorkingDateSlots;

import java.time.LocalDate;
import java.util.*;

public class SearchRoomAvailabilityService {
    public static final int NUMBER_OF_DAYS = 3;
    private final List<RoomAvailabilityProposal> proposals;

    public SearchRoomAvailabilityService() {
        proposals = new ArrayList<>();
    }

    public List<RoomAvailabilityProposal> searchRoomAvailability(Map<LocalDate, Map<Room, WorkingDateSlots>> calendars, SearchRoomAvailabilityRequest searchRoomAvailabilityRequest) {
        proposals.clear();
        for (LocalDate date : calendars.keySet()) {
            for (Room room : calendars.get(date).keySet()) {
                if (!isGoodCapacityRoom(searchRoomAvailabilityRequest, room)) continue;
                WorkingDateSlots workingDateSlots = calendars.get(date).get(room);
                proposals.addAll(searchRoomAvailabilityForRoom(workingDateSlots, searchRoomAvailabilityRequest, room));
            }
        }
        return proposals;
    }

    private List<RoomAvailabilityProposal> searchRoomAvailabilityForRoom(WorkingDateSlots workingDateSlots, SearchRoomAvailabilityRequest searchRoomAvailabilityRequest, Room room) {
        List<RoomAvailabilityProposal> proposals = new ArrayList<>();
        List<Slot> slots = workingDateSlots.getSlots();
        List<Slot> freeSlots = new LinkedList<>();
        for (Slot slot : slots) {
            if (slot.isFree()) {
                freeSlots.add(slot);
            } else {
                if (calculateDuration(freeSlots) >= searchRoomAvailabilityRequest.duration().toMinutes()) {
                    proposals.add(new RoomAvailabilityProposal(room, workingDateSlots.getDate(), freeSlots.getFirst().getStart(), freeSlots.getLast().getEnd()));
                }
                freeSlots.clear();
            }
        }
        if (calculateDuration(freeSlots) >= searchRoomAvailabilityRequest.duration().toMinutes()) {
            proposals.add(new RoomAvailabilityProposal(room, workingDateSlots.getDate(), freeSlots.getFirst().getStart(), freeSlots.getLast().getEnd()));
        }
        return proposals;
    }

    private long calculateDuration(List<Slot> freeSlots) {
        return freeSlots.stream().mapToLong(slot -> slot.getEnd().toSecondOfDay() - slot.getStart().toSecondOfDay()).sum();
    }

    private boolean isGoodCapacityRoom(SearchRoomAvailabilityRequest searchRoomAvailabilityRequest, Room room) {
        return searchRoomAvailabilityRequest.numberOfPeople() <= room.getCapacity();
    }
}
