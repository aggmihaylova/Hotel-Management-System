package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a manger who manages a hotel
 */
public class Manager {

    private String firstName;
    private String lastName;
    private Hotel hotel;


    /**
     * @param firstName manager's first name
     * @param lastName  manager's last name
     */
    public Manager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @param firstName manager's first name
     * @param lastName  manager's last name
     * @param hotel     the hotel managed by the manager
     */
    public Manager(String firstName, String lastName, Hotel hotel) {
        this(firstName, lastName);
        this.hotel = hotel;
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
     * Creates booking
     *
     * @param fromDate       date
     * @param toDate         date
     * @param numberOfPeople number of people
     * @param reserveID      ID of the booking
     * @return the number of the room that has beed booked
     * @throws NoRoomsAvailableException if there is no appropriate room
     */
    public int createBooking(LocalDate fromDate, LocalDate toDate, int numberOfPeople, int reserveID) {

        List<Room> freeRooms = hotel.findAvailableRooms(fromDate, toDate, numberOfPeople);

        if (freeRooms.size() == 0) {
            throw new NoRoomsAvailableException("There is no appropriate room for you! ");
        } else
            return hotel.createReservation((new Booking(reserveID, fromDate, toDate)), freeRooms.get(0));
    }
}
