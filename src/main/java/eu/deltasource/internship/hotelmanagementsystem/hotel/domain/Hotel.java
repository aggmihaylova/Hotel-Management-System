package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;

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
     * This is a constructor
     *
     * @param name  hotel's name
     * @param rooms hotel rooms
     */
    public Hotel(String name, List<Room> rooms) {
        setName(name);
        setRooms(rooms);
    }

    /**
     * This is constructor
     *
     * @param name hotel's name
     */
    public Hotel(String name) {
        setName(name);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getName() {
        return name;
    }

    /**
     * Checks if the rooms are valid
     *
     * @param rooms hotel rooms
     */
    public void setRooms(List<Room> rooms) {
        if (rooms == null) {
            throw new MissingArgumentException("Invalid rooms !");
        }
        this.rooms = new ArrayList<>(rooms);
    }

    /**
     * Checks if the name is valid
     *
     * @param name hotel's name
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new MissingArgumentException("Invalid name !");
        }
        this.name = name;
    }

    /**
     * Searches for available rooms
     *
     * @param fromDate    date
     * @param toDate      date
     * @param numOfPeople number of people
     * @return list of available rooms
     */
    public List<Room> findAvailableRooms(LocalDate fromDate, LocalDate toDate, int numOfPeople, int days) {

        List<Room> availableRooms = new ArrayList<>();
        Set<Booking> availableBookings;

        for (Room room : rooms) {
            if ((findCommoditiesRooms(room.getCommodities(), numOfPeople))) {
                availableBookings = room.findAvailableDatesForIntervalAndSize(fromDate, toDate, days);
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
            availableRooms.add(room);
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