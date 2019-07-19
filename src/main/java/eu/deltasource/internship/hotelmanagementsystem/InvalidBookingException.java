package eu.deltasource.internship.hotelmanagementsystem;

/**
 * Exception for unsuccessful bookings
 */
public class InvalidBookingException extends RuntimeException {

   public InvalidBookingException(String message){
        super(message);
    }
}
