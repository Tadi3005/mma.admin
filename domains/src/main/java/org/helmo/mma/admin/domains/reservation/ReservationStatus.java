package org.helmo.mma.admin.domains.reservation;

public enum ReservationStatus {
    ROOM_NOT_FOUND("Room not available"),
    SUCCESS("Reservation successful");

    private final String message;

    ReservationStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
