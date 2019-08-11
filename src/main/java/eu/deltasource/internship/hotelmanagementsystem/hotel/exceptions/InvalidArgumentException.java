package eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions;

/**
 * Exception for null values
 */

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String message) {
        super(message);
    }
}
