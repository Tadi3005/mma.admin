package org.helmo.mma.admin.infrastructures.mapper.ical;

import net.fortuna.ical4j.model.component.VEvent;
import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.User;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Mapper to convert an iCal event to a domain event.
 */
public class EventMapperIcal {
    /**
     * Convert an iCal event to a domain event.
     * @param icalEventDto the iCal event
     * @return the domain event
     */
    public Event toEvent(VEvent icalEventDto) {
        String uuid = getUuid(icalEventDto);
        String organizer = getOrganizer(icalEventDto);
        LocalDate date = getDate(icalEventDto);
        LocalTime start = getStart(icalEventDto);
        LocalTime end = getEnd(icalEventDto);
        String summary = getSummary(icalEventDto);
        String description = getDescription(icalEventDto);
        String location = getLocation(icalEventDto);
        return new Event(uuid, date, start, end, summary, description, new User(organizer), location);
    }

    private String getDescription(VEvent icalEventDto) {
        return icalEventDto.getDescription().isPresent() ? icalEventDto.getDescription().get().getValue() : "Description inconnue";
    }

    private static String getLocation(VEvent icalEventDto) {
        return icalEventDto.getLocation().isPresent() ? icalEventDto.getLocation().get().getValue() : "Lieu inconnu";
    }

    private static String getSummary(VEvent icalEventDto) {
        return icalEventDto.getSummary().isPresent() ? icalEventDto.getSummary().get().getValue() : "Description inconnue";
    }

    private static LocalTime getEnd(VEvent icalEventDto) {
        return icalEventDto.getDateTimeEnd().isPresent() ? LocalTime.from(icalEventDto.getDateTimeEnd().get().getDate()) : LocalTime.now();
    }

    private static LocalTime getStart(VEvent icalEventDto) {
        return icalEventDto.getDateTimeStart().isPresent() ? LocalTime.from(icalEventDto.getDateTimeStart().get().getDate()) : LocalTime.now();
    }

    private static LocalDate getDate(VEvent icalEventDto) {
        return icalEventDto.getDateTimeStart().isPresent() ? LocalDate.from(icalEventDto.getDateTimeStart().get().getDate()) : LocalDate.now();
    }

    private static String getOrganizer(VEvent icalEventDto) {
        return icalEventDto.getOrganizer().isPresent() ? icalEventDto.getOrganizer().get().getValue() : "Organisateur inconnu";
    }

    private static String getUuid(VEvent icalEventDto) {
        return icalEventDto.getUid().isPresent() ? icalEventDto.getUid().get().getValue() : "UUID inconnu";
    }
}
