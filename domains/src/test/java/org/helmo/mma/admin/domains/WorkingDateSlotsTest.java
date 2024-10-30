package org.helmo.mma.admin.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class WorkingDateSlotsTest {
    private WorkingDateSlots workingDateSlots;

    @BeforeEach
    public void setUp() {
        workingDateSlots = new WorkingDateSlots(LocalDate.now());
        workingDateSlots.initSlots();
    }

    @Test
    public void testIsAvailable_NoOccupiedSlots() {
        assertTrue(workingDateSlots.isAvailable(LocalTime.of(9, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void testIsAvailable_OccupiedSlotsOutsideRange() {
        Event event = new Event("Test", LocalDate.now(), LocalTime.of(8, 0), LocalTime.of(8, 30), "Test", new User("email@test"), "LocalTest");
        workingDateSlots.fillSlot(event);
        assertTrue(workingDateSlots.isAvailable(LocalTime.of(9, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void testIsAvailable_OccupiedSlotsWithinRange() {
        Event event = new Event("Test", LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(9, 30), "Test", new User("email@test"), "LocalTest");
        workingDateSlots.fillSlot(event);
        assertFalse(workingDateSlots.isAvailable(LocalTime.of(9, 0), LocalTime.of(10, 0)));
    }

    @Test
    public void testIsAvailable_StartBeforeStart() {
        Event event = new Event("Test", LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(9, 30), "Test", new User("email@test"), "LocalTest");
        workingDateSlots.fillSlot(event);
        assertTrue(workingDateSlots.isAvailable(LocalTime.of(8, 0), LocalTime.of(9, 0)));
    }

    @Test
    public void testIsAvailable_StartAfterEnd() {
        Event event = new Event("Test", LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(9, 30), "Test", new User("email@test"), "LocalTest");
        workingDateSlots.fillSlot(event);
        assertTrue(workingDateSlots.isAvailable(LocalTime.of(10, 0), LocalTime.of(9, 0)));
    }

    @Test
    public void testIsAvailable_StartEndWithinWorkingHours() {
        Event event = new Event("Test", LocalDate.now(), LocalTime.of(16, 0), LocalTime.of(16, 30), "Test", new User("email@test"), "LocalTest");
        workingDateSlots.fillSlot(event);
        assertFalse(workingDateSlots.isAvailable(LocalTime.of(16, 0), LocalTime.of(16, 30)));
    }

}