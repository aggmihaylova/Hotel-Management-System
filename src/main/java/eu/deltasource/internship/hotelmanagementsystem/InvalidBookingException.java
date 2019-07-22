package eu.deltasource.internship.hotelmanagementsystem;

/**
 * Exception for unsuccessful booking
 */
public class InvalidBookingException extends RuntimeException {

   public InvalidBookingException(String message){
        super(message);
    }
}
