package org.helmo.mma.admin.domains.reservation;

import org.helmo.mma.admin.domains.WorkingDateSlots;

/**
 * Status of the reservation.
 */
public enum ReservationStatus {
    ROOM_NOT_FOUND("La salle n'existe pas"),
    SUCCESS("La réservation a été effectuée avec succès"),
    END_BEFORE_START("La date de fin est avant la date de début"),
    EVENT_CONFLICT("Un événement est dèjà présent pendant cette plage horaire"),
    USER_NOT_FOUND("L'utilisateur n'existe pas"),
    ROOM_CAPACITY_NOT_ENOUGH("La capacité de la salle est insuffisante"),
    ROOM_NOT_AVAILABLE("La salle n'est pas disponible"),
    OUTSIDE_OPENING("La réservation est en dehors des heures d'ouverture (" + WorkingDateSlots.START_DAY + " - " + WorkingDateSlots.END_DAY + ")"),
    IN_PAST("La réservation est dans le passé");

    private final String message;

    /**
     * Create a new ReservationStatus.
     * @param message The message of the status.
     */
    ReservationStatus(String message) {
        this.message = message;
    }

    /**
     * Get the message of the status.
     * @return The message of the status.
     */
    public String getMessage() {
        return message;
    }
}
