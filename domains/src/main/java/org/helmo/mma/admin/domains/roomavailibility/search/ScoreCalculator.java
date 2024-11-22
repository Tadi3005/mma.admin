package org.helmo.mma.admin.domains.roomavailibility.search;

import org.helmo.mma.admin.domains.roomavailibility.RoomAvailabilityProposal;
import org.helmo.mma.admin.domains.roomavailibility.SearchRoomAvailabilityRequest;

import java.util.List;

public class ScoreCalculator {

    private final List<ScoringStrategy> scoringStrategies;

    public ScoreCalculator(List<ScoringStrategy> scoringStrategies) {
        this.scoringStrategies = scoringStrategies;
    }

    public double calculateScore(RoomAvailabilityProposal proposal, SearchRoomAvailabilityRequest request) {
        double score = 0;
        for (ScoringStrategy scoringStrategy : scoringStrategies) {
            score += scoringStrategy.calculateScore(proposal, request);
        }
        return score;
    }
}
