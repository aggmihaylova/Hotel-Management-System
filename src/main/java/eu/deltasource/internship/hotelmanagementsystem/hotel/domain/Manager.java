package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.NoRoomsAvailableException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        this(firstName, lastName);
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

    /**
     * Removes booking
     *
     * @param room    the room that has been booked
     * @param booking the booking that must be removed/canceled
     */
    public void removeCreatedBooking(Room room, Booking booking) {
        if (room == null || booking == null) {
            throw new MissingArgumentException("Invalid room or booking");
        }
        hotel.removeCurrentBooking(room, booking);
    }

    /**
     * Method that initializes/sets the manager's first name
     *
     * @param firstName manager's first name
     * @throw InvalidBookingException if the first name is null
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new MissingArgumentException("Invalid first name !");
        }
        this.firstName = firstName;
    }

    /**
     * Method that initializes/sets the manager's last name
     *
     * @param lastName manager's first name
     * @throw InvalidBookingException if the last name is null
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new MissingArgumentException("Invalid last name !");
        }
        this.lastName = lastName;
    }

    /**
     * Method that initializes/sets the hotel
     *
     * @param hotel the hotel
     * @throw InvalidBookingException if the hotel is null
     */
    public void setHotel(Hotel hotel) {
        if (hotel == null) {
            throw new MissingArgumentException("Invalid hotel");
        }
        this.hotel = hotel;
    }

    /**
     * Creates booking
     *
     * @param fromDate       starting date
     * @param toDate         ending date
     * @param numberOfPeople number of people
     * @param guestID        ID of the guest
     * @return the number of the room that has been booked
     * @throws NoRoomsAvailableException if there is no appropriate room
     */
    public int createBooking(LocalDate fromDate, LocalDate toDate, int numberOfPeople, String guestID,
                             int days, String guestName) {

        checkIfDatesValid(fromDate, toDate, days);

        List<Room> freeRooms = hotel.findAvailableRooms(fromDate, toDate, numberOfPeople, days);

        if (freeRooms.size() == 0) {
            throw new NoRoomsAvailableException("There is no appropriate room for you! ");
        } else {
            return hotel.createReservation(chooseBooking(freeRooms.get(0),
                    guestID, fromDate, toDate, days, guestName), freeRooms.get(0));
        }
    }

    private void checkIfDatesValid(LocalDate fromDate, LocalDate toDate, int days) {
        if (fromDate == null || toDate == null || days <= 0) {
            throw new InvalidBookingException("Invalid dates !");
        }
    }

    private Booking chooseBooking(Room room, String guestID, LocalDate from, LocalDate to, int days, String guestName) {

        Set<Booking> bookings = room.findAvailableBookings(from, to, days);

        if (bookings.size() == 0) {
            return createNewBooking(room, guestID, from, from.plusDays(days), guestName);
        } else {
            List<Booking> freeBookings = new ArrayList<>(room.findAvailableBookings(from, to, days));
            LocalDate fromDate = freeBookings.get(0).getFrom();
            LocalDate toDate = freeBookings.get(0).getTo();

            return createNewBooking(room, guestID, fromDate, toDate, guestName);
        }
    }

    private Booking createNewBooking(Room room, String guestID, LocalDate from, LocalDate to, String guestName) {

        Booking newBooking = new Booking(room.getBookings().size() + 1, guestName, guestID, from, to);

        return newBooking;
    }
}