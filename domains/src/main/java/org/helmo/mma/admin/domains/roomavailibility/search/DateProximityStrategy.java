package org.helmo.mma.admin.domains.roomavailibility.search;

import org.helmo.mma.admin.domains.roomavailibility.RoomAvailabilityProposal;
import org.helmo.mma.admin.domains.roomavailibility.SearchRoomAvailabilityRequest;

public class DateProximityStrategy implements ScoringStrategy{
    @Override
    public double calculateScore(RoomAvailabilityProposal proposal, SearchRoomAvailabilityRequest request) {
        long dateDifference = Math.abs(proposal.date().toEpochDay() - request.date().toEpochDay());
        return 1.0 / (1 + dateDifference);
    }
}
