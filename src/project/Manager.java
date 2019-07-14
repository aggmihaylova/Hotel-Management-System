package project;

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

    /**
     * Create new booking
     * @param newBooking  booking interval
     * @param countBeds the requested number of beds
     * @param days  number of booked days
     * @return the result of booking - successful or not
     */


    public boolean makeReservation(Booking newBooking, int countBeds, int days) {

        Room searchedRoom = hotel.bookRoomByDate(newBooking, countBeds);

        if (searchedRoom != null) {
            hotel.addReservation(newBooking, searchedRoom);
            return true;
        } else {
            System.out.println("Unavailable count of beds or the room has already been booked !");
            return false;
        }
    }

}




