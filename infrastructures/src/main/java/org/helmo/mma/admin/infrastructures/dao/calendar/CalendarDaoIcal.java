package org.helmo.mma.admin.infrastructures.dao.calendar;

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
import org.helmo.mma.admin.domains.dao.CalendarDao;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.infrastructures.mapper.ical.EventMapperIcal;
import org.helmo.mma.admin.infrastructures.mapper.ical.ReservationRequestMapperIcal;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarDaoIcal implements CalendarDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Path path;
    private final EventMapperIcal eventMapper;
    private final ReservationRequestMapperIcal reservationRequestMapper;

    /**
     * Create a calendar repository with a path to an iCal file.
     * @param path the path to the iCal file
     */
    public CalendarDaoIcal(String path, EventMapperIcal eventMapper, ReservationRequestMapperIcal reservationRequestMapper) {
        this.path = Paths.get(path);
        if (Files.notExists(this.path)) {
            createIcalFile(path);
        }
        this.eventMapper = eventMapper;
        this.reservationRequestMapper = reservationRequestMapper;
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
    public List<Event> findEventsAt(LocalDate date) {
        List<Event> events = new ArrayList<>();
        try (var reader = Files.newBufferedReader(this.path)) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(reader);

            for (Object component : calendar.getComponents()) {
                if (component instanceof VEvent) {
                    Event event = eventMapper.toEvent((VEvent) component);
                    if (event.date().isEqual(date)) {
                        events.add(event);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while reading the iCal file: {}", e.getMessage());
            return List.of();
        }
        return events;
    }

    /**
     * Add a reservation to the iCal file
     * @param reservationRequest the reservation request
     */
    @Override
    public void addReservation(ReservationRequest reservationRequest) {
        try (var reader = Files.newBufferedReader(this.path)) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(reader);

            VEvent event = reservationRequestMapper.toVEvent(reservationRequest);
            calendar.add(event);

            try (var writer = Files.newBufferedWriter(this.path)) {
                CalendarOutputter outputter = new CalendarOutputter();
                outputter.output(calendar, writer);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while adding a reservation to the iCal file: {}", e.getMessage());
        }
    }
}
