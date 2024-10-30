package org.helmo.mma.admin.domains;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String id;
    private final String name;
    private final int capacity;
    private final List<Event> events;

    public static final Room NOT_FOUND = new Room("NOT_FOUND", "NOT_FOUND", 0, List.of());

    public Room(String id, String name, int capacity, List<Event> events) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.events = new ArrayList<>(events);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}
