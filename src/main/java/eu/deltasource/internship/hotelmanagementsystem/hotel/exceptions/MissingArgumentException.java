package eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions;

/**
 * Exception for null values
 */

public class MissingArgumentException extends RuntimeException {

    public MissingArgumentException(String message) {
        super(message);
    }
}
