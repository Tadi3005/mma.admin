package org.helmo.mma.admin.domains;

import java.util.List;

public record Room(String id, String name, int capacity, List<Event> events) {

    public static final Room NOT_FOUND = new Room("NOT_FOUND", "NOT_FOUND", 0, List.of());
}
