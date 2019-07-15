package main.java;

import main.hotel.service.domain.commodities.Bed;
import main.hotel.service.domain.commodities.Booking;

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

    public Manager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hotel = new Hotel();

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
     * Checks if the booking can be made
     *
     * @param newBooking requested booking
     * @param bed        requested bed
     * @return target room which is going to be booked
     * @throws Exception if the room has already been booked or there is no appropriate room
     */

    public Room tryReservation(Booking newBooking, Bed bed) throws Exception {

        Room targetRoom;

        try {
            targetRoom = hotel.bookRoomByDate(newBooking, bed);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }

        return targetRoom;
    }

    /**
     * Make reservation
     *
     * @param newBooking date's interval
     * @param targetRoom the book that is going to be booked
     */

    public void makeReservation(Booking newBooking, Room targetRoom) {

        hotel.createReservation(newBooking, targetRoom);
    }

}


