package org.helmo.mma.admin.domains;

import java.util.Map;

public class CalendarBuilder {
    private final Map<Room, WorkingDateSlots> roomWorkingDateSlots;
    private String calendar;

    public CalendarBuilder(Map<Room, WorkingDateSlots> roomWorkingDateSlots) {
        this.roomWorkingDateSlots = roomWorkingDateSlots;
        this.calendar = "";
    }

    public void build() {
        this.buildHeader();
        for (Room room : roomWorkingDateSlots.keySet()) {
            this.buildLineOf(room);
        }
    }

    private void buildHeader() {
        StringBuilder header = new StringBuilder();
        header.append("|Salle|");
        for (int i = WorkingDateSlots.START_DAY.getHour(); i < WorkingDateSlots.END_DAY.getHour(); i++) {
            header.append(String.format("%02d", i)).append("h|").append(String.format("%3s", " ")).append("|");
        }
        this.calendar = header.toString();
    }

    private void buildLineOf(Room room) {
        StringBuilder line = new StringBuilder();
        line.append("\n|").append(room.getId()).append("  |");
        WorkingDateSlots workingDateSlots = roomWorkingDateSlots.get(room);
        for (Slot slot : workingDateSlots.getSlots()) {
            line.append(slot.isFree() ? "   |" : " X |");
        }
        this.calendar += line.toString();
    }



    public String getCalendar() {
        this.build();
        return calendar;
    }
}
