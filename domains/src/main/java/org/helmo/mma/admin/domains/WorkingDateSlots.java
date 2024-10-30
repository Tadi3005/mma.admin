package org.helmo.mma.admin.domains;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The slots of a working date
 */
public class WorkingDateSlots implements Iterable<Slot> {
    private final LocalDate date;
    private final List<Slot> slots;
    public static final LocalTime START_DAY = LocalTime.of(8, 0);
    public static final LocalTime END_DAY = LocalTime.of(17, 0);
    public static final Duration SLOT_DURATION = Duration.ofMinutes(30);

    /**
     * Create a working date with a date
     * @param date the date of the day
     */
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
            slots.add(new Slot(time));
            time = time.plus(SLOT_DURATION);
        }
    }

    /**
     * Fill a slot with an event
     * @param event the event to fill the slot with
     */
    public void fillSlot(Event event) {
        slots.stream()
                .filter(slot -> slot.isBetween(event.start(), event.end()))
                .forEach(Slot::occupe);
    }

    /**
     * Get the slots of the day
     * @return the slots of the day
     */
    public List<Slot> getSlots() {
        return slots;
    }

    /**
     * Check if a slot is available between two times
     * @param start the start time
     * @param end the end time
     * @return true if the slot is available, false otherwise
     */
    public boolean isAvailable(LocalTime start, LocalTime end) {
        return slots.stream().noneMatch(slot -> slot.isBetween(start, end) && !slot.isFree());
    }

    /**
     * Get the date of the day
     * @return the date of the day
     */
    @Override
    public Iterator<Slot> iterator() {
        return slots.iterator();
    }


}
