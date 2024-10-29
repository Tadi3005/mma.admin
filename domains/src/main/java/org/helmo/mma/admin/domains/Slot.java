package org.helmo.mma.admin.domains;

import java.time.LocalTime;

public class Slot {
    private final LocalTime start;
    private boolean free;

    public Slot(LocalTime time) {
        this.start = time;
        this.free = true;
    }

    public boolean isFree() {
        return free;
    }

    public LocalTime getStart() {
        return start;
    }

    public void occupe() {
        this.free = false;
    }
}
