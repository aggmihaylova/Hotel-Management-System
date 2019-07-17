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
 * <p>
 * methods - getters, setters, constructors and others
 */


public class Hotel {

    private String name;
    private List<Room> rooms;

    /**
     * parameterized constructor
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

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }


    public List<Room> findAvailableRooms(LocalDate fromDate, LocalDate toDate, int numOfPeople) {

        List<Room> availableRooms = new ArrayList<>();
        Set<Booking> availableBookings = new HashSet<>();

        for (Room room : rooms) {
            for (AbstractCommodity commodity : room.getCommodities()) {
                if (commodity instanceof Bed) {
                    if (findAppropriateBedSize(((Bed) commodity), numOfPeople)) {
                        availableBookings = room.findAvailableDatesForIntervalAndSize(fromDate, toDate);
                        if (room.getBookings().size() == 0 || availableBookings != null) {
                            availableRooms.add(new Room(room.getRoomNum(), room.getCommodities(), room.getMaintenanceDates(),
                                    availableBookings, room.getCountBeds()));
                        }
                    }
                }

            }
        }

        return availableRooms;

    }


    /**
     * check if there is a bed in current room that can
     *
     * @param bed
     * @param numOfPeople
     * @return
     */

    private boolean findAppropriateBedSize(Bed bed, int numOfPeople) {

        if (bed.getNumberOfPersonas() >= numOfPeople)
            return true;
        return false;

    }

    /**
     * calls createBooking() because doesn't have direct access to the room method
     *
     * @param newBooking the new booking
     * @param room       the room that will be booked
     * @return the number of the room that has been booked
     */


    public int createReservation(Booking newBooking, Room room) {
        return room.createBooking(newBooking);
    }
}
