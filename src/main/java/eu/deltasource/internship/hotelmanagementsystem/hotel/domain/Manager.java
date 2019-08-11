package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a manager who manages a hotel
 */
@Getter
public class Manager {

    private String firstName;
    private String lastName;
    private Hotel hotel;

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
     * Initializes manager's first name
     *
     * @param firstName manager's first name
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidArgumentException("Invalid first name !");
        }
        this.firstName = firstName;
    }

    /**
     * Initializes manager's last name
     *
     * @param lastName manager's first name
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidArgumentException("Invalid last name !");
        }
        this.lastName = lastName;
    }

    /**
     * Initializes the hotel
     *
     * @param hotel the hotel
     */
    public void setHotel(Hotel hotel) {
        if (hotel == null) {
            throw new InvalidArgumentException("Invalid hotel");
        }
        this.hotel = hotel;
    }

    /**
     * Removes booking
     *
     * @param room    the room that has been booked
     * @param booking the booking that must be removed/canceled
     */
    public void removeExistingBooking(Room room, Booking booking) {
        if (room == null || booking == null) {
            throw new InvalidArgumentException("Invalid room or booking");
        }
        hotel.removeCurrentBooking(room, booking);
    }

    /**
     * Creates booking
     *
     * @param fromDate       starting date
     * @param toDate         ending date
     * @param numberOfPeople number of people
     * @param guestID        ID of the guest
     * @return the number of the room that has been booked
     */
    public int createBooking(LocalDate fromDate, LocalDate toDate, int numberOfPeople, String guestID, int days, String guestName) {
        datesValidation(fromDate, toDate, days);

        List<Room> freeRooms = hotel.findAvailableRooms(fromDate, toDate, numberOfPeople, days);

        if (freeRooms.isEmpty()) {
            throw new NoRoomsAvailableException("There is no appropriate room for you! ");
        }
        return hotel.createReservation(chooseBooking(freeRooms.get(0),
                guestID, fromDate, toDate, days, guestName), freeRooms.get(0));

    }

    private void datesValidation(LocalDate fromDate, LocalDate toDate, int days) {
        if (fromDate == null || toDate == null || days <= 0) {
            throw new InvalidBookingException("Invalid dates !");
        }
    }

    private Booking chooseBooking(Room room, String guestID, LocalDate from, LocalDate to, int days, String guestName) {

        Set<Booking> bookings = room.findAvailableBookings(from, to, days);

        if (bookings.isEmpty()) {
            return createNewBooking(room, guestID, from, from.plusDays(days), guestName);
        } else {
            List<Booking> freeBookings = new ArrayList<>(room.findAvailableBookings(from, to, days));
            LocalDate fromDate = freeBookings.get(0).getFrom();
            LocalDate toDate = freeBookings.get(0).getTo();
            return createNewBooking(room, guestID, fromDate, toDate, guestName);
        }
    }

    private Booking createNewBooking(Room room, String guestID, LocalDate from, LocalDate to, String guestName) {
        return (new Booking((room.getBookings().size() + 1), guestName, guestID, from, to));
    }
}