package org.helmo.mma.admin.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.helmo.mma.admin.domains.Calendar;
import org.helmo.mma.admin.domains.repository.CalendarRepository;
import org.helmo.mma.admin.domains.reservation.ReservationService;
import org.helmo.mma.admin.domains.reservation.validator.*;
import org.helmo.mma.admin.domains.roomavailibility.SearchRoomAvailabilityService;
import org.helmo.mma.admin.domains.roomavailibility.search.*;
import org.helmo.mma.admin.domains.service.CalendarService;
import org.helmo.mma.admin.infrastructures.IcalCalendarRepository;
import org.helmo.mma.admin.infrastructures.factory.FileDataStorageFactory;
import org.helmo.mma.admin.infrastructures.factory.SQLDataSorageFactory;
import org.helmo.mma.admin.infrastructures.service.MmaCalendarService;
import org.helmo.mma.admin.infrastructures.service.RoomService;
import org.helmo.mma.admin.infrastructures.service.UserService;
import org.helmo.mma.admin.infrastructures.storage.FileDataStorage;
import org.helmo.mma.admin.presentations.Presenter;
import org.helmo.mma.admin.presentations.View;
import org.helmo.mma.admin.views.CLIView;

import java.util.LinkedList;
import java.util.List;

public class Program {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            View view = new CLIView(args, "dir");
            String resource = view.getResource();

            FileDataStorageFactory fileDataStorage = new FileDataStorageFactory(resource);
            UserService userService = new UserService(fileDataStorage.createDataStorage());
            RoomService roomService = new RoomService(fileDataStorage.createDataStorage());
            CalendarService calendarService = new MmaCalendarService(fileDataStorage.createDataStorage());
            List<ReservationValidator> reservationValidators = initReservationValidators();
            ReservationService reservationService = new ReservationService(reservationValidators);
            Calendar calendar = new Calendar(roomService.findAll(), userService.findAll(), reservationService);
            List<ScoringStrategy> scoringStrategies = initScoringStrategies();
            ScoreCalculator calculator = new ScoreCalculator(scoringStrategies);
            SearchRoomAvailabilityService searchRoomAvailabilityService = new SearchRoomAvailabilityService(calculator);
            Presenter presenter = new Presenter(view, calendarService, calendar, searchRoomAvailabilityService);
            presenter.start();
        } catch (Exception e) {
            LOGGER.error("An error occurred", e);
        }
    }

    private static List<ScoringStrategy> initScoringStrategies() {
        List<ScoringStrategy> scoringStrategies = new LinkedList<>();
        scoringStrategies.add(new DateProximityStrategy());
        scoringStrategies.add(new CapacityProximityStrategy());
        scoringStrategies.add(new DurationProximityStrategy());
        return scoringStrategies;
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
