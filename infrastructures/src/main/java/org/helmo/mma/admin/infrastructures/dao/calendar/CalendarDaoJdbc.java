package org.helmo.mma.admin.infrastructures.dao.calendar;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.Event;
import org.helmo.mma.admin.domains.User;
import org.helmo.mma.admin.domains.dao.CalendarDao;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarDaoJdbc implements CalendarDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Connection connection;

    public CalendarDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Event> findEventsAt(LocalDate date) {
        List<Event> events = new ArrayList<>();
        String query = """
        SELECT * FROM Reservation
        JOIN Room ON Reservation.id_room = Room.id
        JOIN Member ON Reservation.matricule_user = Member.matricule
        WHERE date = ?
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(date));
            try (var resulset = statement.executeQuery()) {
                while (resulset.next()) {
                    var event = new Event(
                            resulset.getString("Reservation.id_reservation"),
                            resulset.getDate("Reservation.date").toLocalDate(),
                            resulset.getTime("Reservation.time_start").toLocalTime(),
                            resulset.getTime("Reservation.time_end").toLocalTime(),
                            resulset.getString("Reservation.summary"),
                            resulset.getString("Reservation.description"),
                            new User(
                                    resulset.getString("Member.matricule"),
                                    resulset.getString("Member.fullname"),
                                    resulset.getString("Member.email")
                            ),
                            resulset.getString("Room.id")
                    );
                    events.add(event);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Cannot find events at date", e);
            return List.of();
        }
        return events;
    }

    @Override
    public void addReservation(ReservationRequest reservationRequest) {
        String query = """
        INSERT INTO Reservation (date, time_start, time_end, summary, description, id_room, matricule_user)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(reservationRequest.date()));
            var startDateTime = java.sql.Timestamp.valueOf(reservationRequest.date().atTime(reservationRequest.start()));
            var endDateTime = java.sql.Timestamp.valueOf(reservationRequest.date().atTime(reservationRequest.end()));
            statement.setTimestamp(2, startDateTime);
            statement.setTimestamp(3, endDateTime);
            statement.setString(4, reservationRequest.summary());
            statement.setString(5, reservationRequest.description());
            statement.setString(6, reservationRequest.idRoom());
            statement.setString(7, reservationRequest.matriculeUser());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Cannot add reservation", e);
        }
    }
}
