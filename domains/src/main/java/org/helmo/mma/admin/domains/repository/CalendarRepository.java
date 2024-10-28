package org.helmo.mma.admin.domains.repository;

import org.helmo.mma.admin.domains.Event;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository {
    List<Event> getEvents(LocalDate date);
}
