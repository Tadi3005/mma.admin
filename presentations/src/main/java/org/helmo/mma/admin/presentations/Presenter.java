package org.helmo.mma.admin.presentations;

import org.helmo.mma.admin.domains.*;
import org.helmo.mma.admin.domains.converter.Converter;
import org.helmo.mma.admin.domains.converter.DateConverter;
import org.helmo.mma.admin.domains.converter.IntConverter;
import org.helmo.mma.admin.domains.converter.LocalTimeConverter;
import org.helmo.mma.admin.domains.exception.ConversionException;
import org.helmo.mma.admin.domains.repository.CalendarRepository;
import org.helmo.mma.admin.domains.repository.RoomRepository;
import org.helmo.mma.admin.domains.repository.UserRepository;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;
import org.helmo.mma.admin.domains.reservation.ReservationService;
import org.helmo.mma.admin.domains.reservation.ReservationStatus;

import java.time.LocalDate;

/**
 * The presenter of the application.
 */
public class Presenter {
    private final CalendarRepository calendarRepository;
    private final View view;
    private final Calendar calendar;

    /**
     * Create a presenter with a view, repositories, and a reservation service.
     * @param view the view
     * @param calendarRepository the calendar repository
     * @param roomRepository the room repository
     * @param userRepository the user repository
     * @param reservationService the reservation service
     */
    public Presenter(View view, CalendarRepository calendarRepository, RoomRepository roomRepository, UserRepository userRepository, ReservationService reservationService) {
        this.view = view;
        this.calendarRepository = calendarRepository;
        this.calendar = new Calendar(roomRepository.findAll(), userRepository.findAll(), reservationService);
    }

    /**
     * Start the program.
     */
    public void start() {
        loadCalendar(LocalDate.now());
    }

    private void loadCalendar(LocalDate date) {
        calendar.loadCalendar(date, calendarRepository.findEventsAt(date));
        displayDailyCalendar(date);
    }

    private void displayDailyCalendar(LocalDate date) {
        view.display("Disponibilites des salles pour le " + date);
        CalendarBuilder calendarBuilder = new CalendarBuilder(calendar.getRoomWorkingDateSlots());
        view.display(calendarBuilder.getCalendar());
        view.display("1. Changer de date");
        view.display("2. Encodage d'un reservation");
        view.display("3. Consulter une réservation");
        view.display("4. Quitter");
        handleMenu(date, askAndConvert("Votre choix: ", new IntConverter()));
    }

    private void handleMenu(LocalDate date, int choice) {
        switch (choice) {
            case 1 -> loadCalendar(askAndConvert("Entrez la nouvelle date (yyyy-MM-dd): ", new DateConverter()));
            case 2 -> enterReservation(date);
            case 3 -> consultReservation(date);
            case 4 -> view.display("Quitter");
            default -> {
                view.display("Choix invalide");
                displayDailyCalendar(date);
            }
        }
    }

    private void consultReservation(LocalDate date) {
        LocalDate reservationDate = askAndConvert("Entrez la date de la réservation (yyyy-MM-dd): ", new DateConverter());
        SearchReservationRequest searchReservationRequest = new SearchReservationRequest(
                reservationDate,
                view.ask("Entrez l'id de la salle: "),
                askAndConvert("Entrez l'heure de la réservation (hh:mm): ", new LocalTimeConverter()));
        calendar.loadCalendar(reservationDate, calendarRepository.findEventsAt(reservationDate));
        Event event = calendar.findEvent(searchReservationRequest);
        if (event == Event.NOT_FOUND) {
            view.display("Aucune réservation trouvée\n");
        } else {
            view.display("Salle : " + event.location());
            view.display("Date : " + event.date() + " "  + event.start() + "-" + event.end());
            view.display("Description : " + event.description());
            User user = calendar.findUser(event.organizer());
            view.display("Organisateur : " + user.fullName() + "\n");
        }
        loadCalendar(date);
    }

    private void enterReservation(LocalDate date) {
        ReservationRequest reservationRequest = new ReservationRequest(
                view.ask("Entrez l'id de la salle: "),
                view.ask("Entrez le matricule de l'utilisateur: "),
                askAndConvert("Entrez le jour de la réservation (yyyy-MM-dd): ", new DateConverter()),
                askAndConvert("Entrez l'heure de début (hh:mm): ", new LocalTimeConverter()),
                askAndConvert("Entrez l'heure de fin (hh:mm): ", new LocalTimeConverter()),
                view.ask("Entrez la description: "),
                askAndConvert("Entrez le nombre de participants: ", new IntConverter())
        );
        calendar.loadCalendar(date, calendarRepository.findEventsAt(reservationRequest.date()));
        ReservationStatus status = calendar.addReservation(reservationRequest);

        if (status == ReservationStatus.SUCCESS) {
            view.display("Reservation ajoutee avec succes\n");
            calendarRepository.addReservation(reservationRequest);
            loadCalendar(reservationRequest.date());
        } else {
            view.display("Impossible d'ajouter la réservation: " + status.getMessage() + "\n");
            loadCalendar(date);
        }
    }

    /**
     * Generic method to prompt the user, convert input, and handle conversion errors.
     * @param message The message to display to the user.
     * @param converter The converter to apply to the user input.
     * @param <T> The target type of the conversion.
     * @return The converted value.
     */
    private <T> T askAndConvert(String message, Converter<T> converter) {
        while (true) {
            try {
                return converter.convert(view.ask(message));
            } catch (ConversionException e) {
                view.displayError(e.getMessage());
            }
        }
    }
}
