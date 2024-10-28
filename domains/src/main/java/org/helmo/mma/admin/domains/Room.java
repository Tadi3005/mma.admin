package org.helmo.mma.admin.domains;

import java.util.List;

public record Room(String id, String name, int capacity, List<Event> events) {

}
