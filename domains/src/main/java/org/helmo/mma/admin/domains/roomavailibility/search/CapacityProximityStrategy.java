package org.helmo.mma.admin.domains.roomavailibility.search;

import org.helmo.mma.admin.domains.roomavailibility.RoomAvailabilityProposal;
import org.helmo.mma.admin.domains.roomavailibility.SearchRoomAvailabilityRequest;

public class CapacityProximityStrategy implements ScoringStrategy {
    @Override
    public double calculateScore(RoomAvailabilityProposal proposal, SearchRoomAvailabilityRequest request) {
        long capacityDifference = Math.abs(proposal.room().getCapacity() - request.numberOfPeople());
        return 1.0 / (1 + capacityDifference);
    }
}
