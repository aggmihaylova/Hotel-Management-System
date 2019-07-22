package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a hotel with rooms
 */
public class Hotel {

    private String name;
    private List<Room> rooms;

    /**
     * @param name  hotel's name
     * @param rooms hotel rooms
     */
    public Hotel(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = new ArrayList<>(rooms);
    }

    public Hotel(String name) {
        this.name = name;
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

    public void setRooms(List<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
    }

    /**
     * Searches for available rooms
     *
     * @param fromDate    date
     * @param toDate      date
     * @param numOfPeople number of people
     * @return list of available rooms
     */
    public List<Room> findAvailableRooms(LocalDate fromDate, LocalDate toDate, int numOfPeople) {

        List<Room> availableRooms = new ArrayList<>();
        Set<Booking> availableBookings;

        for (Room room : rooms) {
            if ((findCommoditiesRooms(room.getCommodities(), numOfPeople))) {
                availableBookings = room.findAvailableDatesForIntervalAndSize(fromDate, toDate);
                addAvailableRoom(availableBookings, availableRooms, room);
            }
        }
        return availableRooms;
    }

    /**
     * Checks if the room has bed/beds with certain capacity
     *
     * @param commodities all commodities in a room
     * @param numOfPeople number of people
     * @return true/false for having/not having beds
     */
    private boolean findCommoditiesRooms(Set<AbstractCommodity> commodities, int numOfPeople) {
        for (AbstractCommodity commodity : commodities) {
            if ((commodity instanceof Bed)) {
                if (((Bed) commodity).getBedType().getSize() == numOfPeople) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds room to the array list of available rooms
     *
     * @param availableBookings free dates for the room
     * @param availableRooms    list of available rooms
     * @param room              added room
     */
    private void addAvailableRoom(Set<Booking> availableBookings, List<Room> availableRooms, Room room) {
        if (room.getBookings().size() == 0 || availableBookings != null) {
            availableRooms.add(new Room(room.getID(), room.getCommodities(), room.getMaintenanceDates(),
                    availableBookings));
        }
    }

    /**
     * calls createBooking() method
     *
     * @param newBooking the new booking
     * @param room       the room that will be booked
     * @return ID of the room that has been booked
     */
    public int createReservation(Booking newBooking, Room room) {
        return room.createBooking(newBooking);
    }
}