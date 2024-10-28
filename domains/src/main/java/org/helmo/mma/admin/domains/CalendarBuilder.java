package org.helmo.mma.admin.domains;

import java.util.Map;

public class CalendarBuilder {
    private final Map<Room, WorkingDateSlots> roomWorkingDateSlots;
    private String calendar;

    public CalendarBuilder(Map<Room, WorkingDateSlots> roomWorkingDateSlots) {
        this.roomWorkingDateSlots = roomWorkingDateSlots;
    }

    public void build() {
        this.buildHeader();
        for (Room room : roomWorkingDateSlots.keySet()) {
            this.buildLineOf(room);
        }
    }

    private void buildLineOf(Room room) {

    }

    private void buildHeader() {

    }

    public String getCalendar() {
        this.build();
        return calendar;
    }
}
