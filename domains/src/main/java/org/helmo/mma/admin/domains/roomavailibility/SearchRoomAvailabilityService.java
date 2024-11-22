package org.helmo.mma.admin.domains.roomavailibility;
import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.Slot;
import org.helmo.mma.admin.domains.WorkingDateSlots;
import org.helmo.mma.admin.domains.roomavailibility.search.ScoreCalculator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SearchRoomAvailabilityService {
    public static final int NUMBER_OF_DAYS = 3;
    public static final int MAX_PROPOSALS = 5; // Limite des propositions à renvoyer

    private final List<RoomAvailabilityProposal> proposals = new ArrayList<>();
    private final ScoreCalculator calculator;

    public SearchRoomAvailabilityService(ScoreCalculator calculator) {
        this.calculator = calculator;
    }

    public List<ScoredProposal> searchRoomAvailability(Map<LocalDate, Map<Room, WorkingDateSlots>> calendars, SearchRoomAvailabilityRequest searchRequest) {
        proposals.clear();

        // Collect all proposals
        List<RoomAvailabilityProposal> roomProposals = calendars.entrySet().stream()
                .flatMap(entry -> findRoomProposals(entry.getValue(), searchRequest).stream())
                .collect(Collectors.toList());

        // Score, rank, and limit proposals
        return rankAndLimitProposals(roomProposals, searchRequest);
    }

    private List<RoomAvailabilityProposal> findRoomProposals(
            Map<Room, WorkingDateSlots> rooms,
            SearchRoomAvailabilityRequest searchRequest) {
        return rooms.entrySet().stream()
                .filter(room -> isSuitableRoom(room.getKey(), searchRequest))
                .flatMap(room -> findAvailabilityForRoom(room.getKey(), room.getValue(), searchRequest).stream())
                .collect(Collectors.toList());
    }

    private boolean isSuitableRoom(Room room, SearchRoomAvailabilityRequest request) {
        return request.numberOfPeople() <= room.getCapacity();
    }

    private List<RoomAvailabilityProposal> findAvailabilityForRoom(
            Room room,
            WorkingDateSlots dateSlots,
            SearchRoomAvailabilityRequest request) {
        List<Slot> availableSlots = new ArrayList<>();
        List<RoomAvailabilityProposal> roomProposals = new ArrayList<>();

        for (Slot slot : dateSlots.getSlots()) {
            if (slot.isFree()) {
                availableSlots.add(slot);
            } else {
                processSlotGroup(room, dateSlots, availableSlots, request, roomProposals);
            }
        }
        processSlotGroup(room, dateSlots, availableSlots, request, roomProposals);
        return roomProposals;
    }

    private void processSlotGroup(
            Room room,
            WorkingDateSlots dateSlots,
            List<Slot> availableSlots,
            SearchRoomAvailabilityRequest request,
            List<RoomAvailabilityProposal> roomProposals) {
        if (calculateDuration(availableSlots) >= request.duration().toMinutes()) {
            roomProposals.add(createProposal(room, dateSlots.getDate(), availableSlots));
        }
        availableSlots.clear();
    }

    private RoomAvailabilityProposal createProposal(Room room, LocalDate date, List<Slot> slots) {
        if (slots.isEmpty()) {
            throw new IllegalArgumentException("Slots list cannot be empty when creating a proposal."); // TODO: Change
        }
        return new RoomAvailabilityProposal(
                room,
                date,
                slots.getFirst().getStart(),
                slots.getLast().getEnd()
        );
    }

    private long calculateDuration(List<Slot> slots) {
        return slots.stream()
                .mapToLong(slot -> slot.getEnd().toSecondOfDay() - slot.getStart().toSecondOfDay())
                .sum();
    }

    private List<ScoredProposal> rankAndLimitProposals(
            List<RoomAvailabilityProposal> proposals,
            SearchRoomAvailabilityRequest request) {
        return proposals.stream()
                .map(proposal -> new ScoredProposal(proposal, calculator.calculateScore(proposal, request)))
                .sorted(Comparator.comparingDouble(ScoredProposal::getScore).reversed())
                .limit(MAX_PROPOSALS)
                .collect(Collectors.toList());
    }
}