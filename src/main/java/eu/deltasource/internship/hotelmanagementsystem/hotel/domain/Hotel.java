package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
        this(name);
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

    /**
     * Method that initializes/sets the list of rooms
     *
     * @param rooms list of rooms
     * @throw MissingArgumentException if the list of rooms is null
     */
    public void setRooms(List<Room> rooms) {
        if (rooms == null) {
            throw new MissingArgumentException("Invalid rooms !");
        }
        this.rooms = new ArrayList<>(rooms);
    }

    /**
     * Method that initializes/sets the hotel's name
     *
     * @param name hotel's name
     * @throw MissingArgumentException if the name is null
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
     * @param fromDate    starting date
     * @param toDate      ending date
     * @param numOfPeople number of people
     * @return list of available rooms
     */
    public List<Room> findAvailableRooms(LocalDate fromDate, LocalDate toDate, int numOfPeople, int days) {

        List<Room> availableRooms = new ArrayList<>();
        Set<Booking> availableBookings = new HashSet<>();

        for (Room room : rooms) {
            if ((room.getCapacity() >= numOfPeople)) {
                findFreeBookings(room, availableBookings, availableRooms, fromDate, toDate, days);
            }
        }
        return availableRooms;
    }

    private void findFreeBookings(Room room, Set<Booking> availableBookings, List<Room> availableRooms,
                                  LocalDate fromDate, LocalDate toDate, int days) {
        if (room.getBookings().size() == 0) {
            addAvailableRooms(availableRooms, room);
        } else {
            availableBookings = room.findAvailableBookings(fromDate, toDate, days);
            checkAvailableBookings(availableBookings, availableRooms, room);
        }
    }

    private void addAvailableRooms(List<Room> availableRooms, Room room) {
        availableRooms.add(room);
    }

    private void checkAvailableBookings(Set<Booking> availableBookings, List<Room> availableRooms, Room room) {
        if (availableBookings.size() != 0) {
            addAvailableRooms(availableRooms, room);
        }
    }

    /**
     * calls createBooking() method
     *
     * @param newBooking the new booking
     * @param room       the room that will be booked
     * @return ID of the room that has been booked
     * @throw MissingArgumentException if the booking or the room is null
     */
    public int createReservation(Booking newBooking, Room room) {
        if (newBooking == null || room == null) {
            throw new MissingArgumentException("Invalid argument !");
        }
        return room.createBooking(newBooking);
    }
}