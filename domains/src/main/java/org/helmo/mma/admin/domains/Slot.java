package org.helmo.mma.admin.domains;

import java.time.LocalTime;

public class Slot {
    private final LocalTime end;
    private LocalTime start;
    private boolean free;

    public Slot(LocalTime time, LocalTime plus) {
        this.start = time;
        this.end = plus;
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
