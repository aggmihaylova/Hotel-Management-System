package project;

import commodities.Bed;
import commodities.Booking;

public class Manager {

    private String firstName;
    private String lastName;
    private Hotel hotel;


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

    public Room tryReservation(Booking newBooking, Bed beds) {
        Room targetRoom = hotel.bookRoomByDate(newBooking, beds);

        if (targetRoom == null)
            return null;
        return targetRoom;

    }


    public void makeReservation(Booking newBooking, Room targetRoom) {

        hotel.addReservation(newBooking, targetRoom);
    }

}


