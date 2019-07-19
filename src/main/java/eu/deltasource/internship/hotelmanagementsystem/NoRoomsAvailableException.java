package eu.deltasource.internship.hotelmanagementsystem;

/**
 * Exception when no available rooms are found in the Hotel
 */
public class NoRoomsAvailableException extends RuntimeException {

    NoRoomsAvailableException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
