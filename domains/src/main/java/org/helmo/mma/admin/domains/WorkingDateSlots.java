package org.helmo.mma.admin.domains;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkingDateSlots {
    private final LocalDate date;
    private final List<Slot> slots;
    private static final LocalTime START_DAY = LocalTime.of(8, 0);
    private static final LocalTime END_DAY = LocalTime.of(17, 0);
    private static final Duration SLOT_DURATION = Duration.ofMinutes(30);

    public WorkingDateSlots(LocalDate date) {
        this.date = date;
        this.slots = new ArrayList<>();
    }

    /**
     * Initialize the slots of the day, all slots are free
     */
    public void initSlots() {
        LocalTime time = START_DAY;
        while (time.isBefore(END_DAY)) {
            slots.add(new Slot(time, time.plus(SLOT_DURATION)));
            time = time.plus(SLOT_DURATION);
        }
    }

    /**
     * Fill a slot with an event if the slot is free
     * @param event the event to fill the slot with
     */
    public void fillSlot(Event event) {
        for (Slot slot : slots) {
            if (slot.isFree() && slot.getStart().equals(event.start())) {
                slot.occupe();
                break;
            }
        }
    }
}
