package org.helmo.mma.admin.domains;

import java.time.LocalTime;

/**
 * A slot of a working date
 */
public class Slot {
    private final LocalTime start;
    private final LocalTime end;
    private boolean free;


    /**
     * Create a slot with a start time
     * @param start the start time of the slot
     */
    public Slot(LocalTime start) {
        this.start = start;
        this.free = true;
        this.end = start.plus(WorkingDateSlots.SLOT_DURATION);
    }

    /**
     * Check if the slot is free
     * @return true if the slot is free, false otherwise
     */
    public boolean isFree() {
        return free;
    }

    /**
     * Get the start time of the slot
     * @return the start time of the slot
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Change the slot to occupied
     */
    public void occupe() {
        this.free = false;
    }

    /**
     * Check if the slot is between two times
     * @param start the start time
     * @param end the end time
     * @return true if the slot is between the two times, false otherwise
     */
    public boolean isBetween(LocalTime start, LocalTime end) {
        return this.start.isBefore(end) && this.end.isAfter(start);
    }
}
