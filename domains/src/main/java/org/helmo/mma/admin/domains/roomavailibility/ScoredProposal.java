package org.helmo.mma.admin.domains.roomavailibility;

import org.helmo.mma.admin.domains.Room;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScoredProposal {
    private final RoomAvailabilityProposal proposal;
    private final double score;

    public ScoredProposal(RoomAvailabilityProposal proposal, double score) {
        this.proposal = proposal;
        this.score = score;
    }

    public Room getRoom() {
        return proposal.room();
    }

    public LocalDate getDate() {
        return proposal.date();
    }

    public LocalTime getStart() {
        return proposal.start();
    }

    public LocalTime getEnd() {
        return proposal.end();
    }

    public Duration getDuration() {
        return proposal.duration();
    }

    public double getScore() {
        return score;
    }
}
