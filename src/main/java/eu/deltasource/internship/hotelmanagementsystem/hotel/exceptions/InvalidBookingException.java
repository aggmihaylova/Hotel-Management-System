package eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions;

/**
 * Exception for invalid booking
 */
public class InvalidBookingException extends RuntimeException {

    public InvalidBookingException(String message) {
        super(message);
    }
}
