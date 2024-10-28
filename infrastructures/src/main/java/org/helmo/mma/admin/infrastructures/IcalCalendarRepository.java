package org.helmo.mma.admin.infrastructures;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale;
import net.fortuna.ical4j.model.property.immutable.ImmutableVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.repository.CalendarRepository;
import org.helmo.mma.admin.infrastructures.mapper.EventMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IcalCalendarRepository implements CalendarRepository {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String path;
    private final EventMapper eventMapper;

    public IcalCalendarRepository(String path) {
        if (Files.notExists(Paths.get(path))) {
            createIcalFile(path);
        }
        this.path = path;
        this.eventMapper = new EventMapper();
    }

    /**
     * Create a new iCal file at the given path
     * @param path the path where the iCal file will be created
     */
    private void createIcalFile(String path) {
        LOGGER.info("Creating a new iCal file at {}", path);
        Calendar calendar = new Calendar();
        calendar.add(new ProdId("-//Helmo//MMA//EN"));
        calendar.add(ImmutableVersion.VERSION_2_0);
        calendar.add(ImmutableCalScale.GREGORIAN);

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path))) {
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, writer);
        } catch (IOException e) {
            LOGGER.error("An error occurred while creating the iCal file: {}", e.getMessage());
        }
    }

    /**
     * Get the events from the iCal file for the given date
     * @param date the date for which the events are retrieved
     * @return the list of events for the given date
     */
    @Override
    public List<Event> getEvents(LocalDate date) {
        List<Event> events = new ArrayList<>();
        try (var reader = Files.newBufferedReader(Paths.get(path))) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(reader);

            for (Object component : calendar.getComponents()) {
                if (component instanceof VEvent) {
                    Event event = eventMapper.toEvent((VEvent) component);
                    events.add(event);
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while reading the iCal file: {}", e.getMessage());
            return List.of();
        }
        return events;
    }
}
