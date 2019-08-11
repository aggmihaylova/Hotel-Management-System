package eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions;

/**
 * Exception when no available rooms are found in the Hotel
 */
public class NoRoomsAvailableException extends RuntimeException {

    public NoRoomsAvailableException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
