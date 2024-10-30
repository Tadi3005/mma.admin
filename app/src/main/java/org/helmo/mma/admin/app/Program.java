package org.helmo.mma.admin.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.repository.CalendarRepository;
import org.helmo.mma.admin.domains.repository.RoomRepository;
import org.helmo.mma.admin.domains.repository.UserRepository;
import org.helmo.mma.admin.domains.reservation.ReservationService;
import org.helmo.mma.admin.domains.reservation.validator.*;
import org.helmo.mma.admin.infrastructures.CSVRoomRepository;
import org.helmo.mma.admin.infrastructures.CSVUserRepository;
import org.helmo.mma.admin.infrastructures.IcalCalendarRepository;
import org.helmo.mma.admin.presentations.Presenter;
import org.helmo.mma.admin.presentations.View;
import org.helmo.mma.admin.views.CLIView;

import java.util.LinkedList;
import java.util.List;

public class Program {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            View view = new CLIView(args);
            String directory = view.getDirectory();
            UserRepository userRepository = new CSVUserRepository(directory + "users.csv");
            RoomRepository roomRepository = new CSVRoomRepository(directory + "rooms.csv");
            CalendarRepository calendarRepository = new IcalCalendarRepository(directory + "calendar.ics");
            List<ReservationValidator> reservationValidators = initReservationValidators();
            ReservationService reservationService = new ReservationService(reservationValidators);
            Presenter presenter = new Presenter(view, calendarRepository, roomRepository, userRepository, reservationService);
            presenter.start();
        } catch (Exception e) {
            LOGGER.error("An error occurred", e);
        }
    }

    private static List<ReservationValidator> initReservationValidators() {
        List<ReservationValidator> reservationValidators = new LinkedList<>();
        reservationValidators.add(new UserIdValidator());
        reservationValidators.add(new RoomCapacityValidator());
        reservationValidators.add(new RoomAvailabilityValidator());
        reservationValidators.add(new EndBeforeStartValidator());
        reservationValidators.add(new OutsideOpeningValidator());
        reservationValidators.add(new PastReservationValidator());
        return reservationValidators;
    }
}
