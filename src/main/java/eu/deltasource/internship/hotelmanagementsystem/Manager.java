package eu.deltasource.internship.hotelmanagementsystem;


import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Manger has 3 private members
 * <p>
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
     */


    public Manager(String firstName, String lastName, Hotel hotel) {
        this(firstName, lastName);
        this.hotel = hotel;
    }

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

    public int createBooking(LocalDate fromDate, LocalDate toDate, int numberOfPeople, int resereveID) throws UnavailableRooms {

        List<Room> freeRooms = new ArrayList<>();
        freeRooms = hotel.findAvailableRooms(fromDate, toDate, numberOfPeople);

        if (freeRooms.size() == 0) {
            throw new UnavailableRooms("There is no appropriate room for you! ");
        } else {
            return hotel.createReservation((new Booking(resereveID, fromDate, toDate)), freeRooms.get(0));

        }
    }

}


