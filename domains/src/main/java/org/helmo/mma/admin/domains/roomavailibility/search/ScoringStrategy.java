package org.helmo.mma.admin.domains.roomavailibility.search;

import org.helmo.mma.admin.domains.roomavailibility.RoomAvailabilityProposal;
import org.helmo.mma.admin.domains.roomavailibility.SearchRoomAvailabilityRequest;

public interface ScoringStrategy {
    double calculateScore(RoomAvailabilityProposal proposal, SearchRoomAvailabilityRequest request);
}
