package org.helmo.mma.admin.infrastructures.mapper;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import org.helmo.mma.admin.domains.reservation.ReservationRequest;

import java.time.LocalDateTime;

/**
 * Mapper to convert a reservation request to an iCal event.
 */
public class ReservationRequestMapper {

    /**
     * Convert a reservation request to an iCal event.
     * @param reservationRequest the reservation request
     * @return the iCal event
     */
    public VEvent toVEvent(ReservationRequest reservationRequest) {
        LocalDateTime start = LocalDateTime.of(reservationRequest.date(), reservationRequest.start());
        LocalDateTime end = LocalDateTime.of(reservationRequest.date(), reservationRequest.end());

        VEvent vEvent = new VEvent(start, end, reservationRequest.description());
        UidGenerator uidGenerator = new RandomUidGenerator();
        Uid uid = uidGenerator.generateUid();
        vEvent.add(uid);
        vEvent.add(new Location(reservationRequest.idRoom()));
        vEvent.add(new Organizer(reservationRequest.matriculeUser()));
        return vEvent;
    }
}
