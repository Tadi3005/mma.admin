package org.helmo.mma.admin.domains.roomavailibility.search;

import org.helmo.mma.admin.domains.roomavailibility.RoomAvailabilityProposal;
import org.helmo.mma.admin.domains.roomavailibility.SearchRoomAvailabilityRequest;

public class DurationProximityStrategy implements ScoringStrategy {
    @Override
    public double calculateScore(RoomAvailabilityProposal proposal, SearchRoomAvailabilityRequest request) {
        long durationDifference = Math.abs(proposal.duration().toMinutes() - request.duration().toMinutes());
        return 1.0 / (1 + durationDifference);
    }
}
