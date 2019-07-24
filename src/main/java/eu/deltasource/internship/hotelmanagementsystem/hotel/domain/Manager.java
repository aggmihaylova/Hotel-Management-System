package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.NoRoomsAvailableException;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a manager who manages a hotel
 */
public class Manager {

    private String firstName;
    private String lastName;
    private Hotel hotel;

    /**
     * This is a constructor
     *
     * @param firstName manager's first name
     * @param lastName  manager's last name
     */
    public Manager(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * This is a constructor
     *
     * @param firstName manager's first name
     * @param lastName  manager's last name
     * @param hotel     the hotel managed by the manager
     */
    public Manager(String firstName, String lastName, Hotel hotel) {
        setFirstName(firstName);
        setLastName(lastName);
        setHotel(hotel);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new MissingArgumentException("Invalid first name !");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new MissingArgumentException("Invalid last name !");
        }
        this.lastName = lastName;
    }

    public void setHotel(Hotel hotel) {
        if (hotel == null) {
            throw new MissingArgumentException("Invalid hotel");
        }
        this.hotel = hotel;
    }

    /**
     * Creates booking
     *
     * @param fromDate       date
     * @param toDate         date
     * @param numberOfPeople number of people
     * @param guestID      ID of the guest
     * @return the number of the room that has beed booked
     * @throws NoRoomsAvailableException if there is no appropriate room
     */
    public int createBooking(LocalDate fromDate, LocalDate toDate, int numberOfPeople, long guestID, int days) {

        checkIfDatesValid(fromDate, toDate, days);

        List<Room> freeRooms = hotel.findAvailableRooms(fromDate, toDate, numberOfPeople, days);

        if (freeRooms.size() == 0) {
            throw new NoRoomsAvailableException("There is no appropriate room for you! ");
        } else {
            LocalDate from = fromDate;
            LocalDate to = from.plusDays(days);
            Booking newBooking = new Booking(guestID, from, to);
            return hotel.createReservation(newBooking, freeRooms.get(0));
        }
    }

    private void checkIfDatesValid(LocalDate fromDate, LocalDate toDate, int days) {
        if (fromDate == null || toDate == null || days <= 0) {
            throw new InvalidBookingException("Invalid dates !");
        }
    }
}