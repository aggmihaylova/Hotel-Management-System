package eu.deltasource.internship.hotelmanagementsystem;


import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Manger has 3 private members
 * methods - getters, setters, constructors and others
 */

public class Manager {

    private String firstName;
    private String lastName;
    private Hotel hotel;


    /**
     * Parametrized constructor
     *
     * @param firstName manager's first name
     * @param lastName  manager's last name
     * @param hotel     the hotel managed by the manager
     */


    public Manager(String firstName, String lastName, Hotel hotel) {
        this(firstName, lastName);
        this.hotel = hotel;
    }

    /**
     * Parametrized constructor
     *
     * @param firstName manager's first name
     * @param lastName  manager's last name
     */

    public Manager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

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
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    /**
     * Creates bookings
     *
     * @param fromDate       date
     * @param toDate         date
     * @param numberOfPeople number of people
     * @param reserveID      ID of the booking
     * @return the number of the room that has beed booked
     * @throws UnavailableRooms if there is no appropriate room
     */

    public int createBooking(LocalDate fromDate, LocalDate toDate, int numberOfPeople, int reserveID) throws UnavailableRooms {

        List<Room> freeRooms = new ArrayList<>();
        freeRooms = hotel.findAvailableRooms(fromDate, toDate, numberOfPeople);

        if (freeRooms.size() == 0) {
            throw new UnavailableRooms("There is no appropriate room for you! ");
        } else {
            return hotel.createReservation((new Booking(reserveID, fromDate, toDate)), freeRooms.get(0));

        }
    }

}


