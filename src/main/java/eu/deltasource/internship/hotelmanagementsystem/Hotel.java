package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class hotel has 2 private members - name and rooms
 * methods - getters, setters, constructors and others
 */


public class Hotel {

    private String name;
    private List<Room> rooms;

    /**
     * Parameterized constructor
     */

    public Hotel(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }


    /**
     * Default constructor
     */

    public Hotel() {

    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    /**
     * Search for available rooms by checking:
     * - if a room has beds
     * - if at least one of the beds is appropriate for numOfPeople
     * - for that room - free dates
     *
     * @param fromDate    date
     * @param toDate      date
     * @param numOfPeople number of people
     * @return list of available rooms
     */

    public List<Room> findAvailableRooms(LocalDate fromDate, LocalDate toDate, int numOfPeople) {

        List<Room> availableRooms = new ArrayList<>();
        Set<Booking> availableBookings = new HashSet<>();

        for (Room room : rooms) {
            for (AbstractCommodity commodity : room.getCommodities()) {
                if (commodity instanceof Bed) {
                    if (((Bed) commodity).getNumberOfPersonas() == numOfPeople) {
                        availableBookings = room.findAvailableDatesForIntervalAndSize(fromDate, toDate);
                        if (room.getBookings().size() == 0 || availableBookings != null) {
                            availableRooms.add(new Room(room.getRoomNum(), room.getCommodities(), room.getMaintenanceDates(),
                                    availableBookings));
                        }
                    }
                }

            }
        }

        return availableRooms;

    }


    /**
     * calls createBooking()
     *
     * @param newBooking the new booking
     * @param room       the room that will be booked
     * @return the number of the room that has been booked
     */


    public int createReservation(Booking newBooking, Room room) {
        return room.createBooking(newBooking);
    }
}
