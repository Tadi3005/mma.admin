package org.helmo.mma.admin.presentations.viewmodel;

import org.helmo.mma.admin.domains.Room;
import org.helmo.mma.admin.domains.WorkingDateSlots;

import java.util.HashMap;
import java.util.Map;

public class CalendarViewModel {

    private final Map<Room, WorkingDateSlots> roomWorkingDateSlots;

    public CalendarViewModel(Map<Room, WorkingDateSlots> roomWorkingDateSlots) {
        this.roomWorkingDateSlots = roomWorkingDateSlots;
    }


    public String[] getHours() {
        String[] hours = new String[WorkingDateSlots.END_DAY.getHour() - WorkingDateSlots.START_DAY.getHour()];
        for (int i = WorkingDateSlots.START_DAY.getHour(); i < WorkingDateSlots.END_DAY.getHour(); i++) {
            hours[i - WorkingDateSlots.START_DAY.getHour()] = String.valueOf(i);
        }
        return hours;
    }

    public Map<String, Boolean[]> getRoomOccupation() {
        Map<String, Boolean[]> data = new HashMap<>();
        for (Room room : roomWorkingDateSlots.keySet()) {
            Boolean[] slots = new Boolean[roomWorkingDateSlots.get(room).getSlots().size()];
            for (int i = 0; i < slots.length; i++) {
                slots[i] = roomWorkingDateSlots.get(room).getSlots().get(i).isFree();
            }
            data.put(room.getId(), slots);
        }
        return data;
    }
}
