package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidArgumentException;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a hotel with rooms
 */
@Getter
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
     * Initializes the list of rooms
     *
     * @param rooms list of rooms
     */
    public void setRooms(List<Room> rooms) {
        if (rooms == null || rooms.contains(null)) {
            throw new InvalidArgumentException("Invalid rooms");
        }
        this.rooms = new ArrayList<>(rooms);
    }

    /**
     * Initializes the hotel's name
     *
     * @param name hotel's name
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidArgumentException("Invalid name");
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

        for (Room room : rooms) {
            if ((room.getCapacity() >= numOfPeople)) {
                findAvailableBookings(room, availableRooms, fromDate, toDate, days);
            }
        }
        return availableRooms;
    }

    /**
     * Removes booking
     *
     * @param room    the room that has that booking
     * @param booking the booking that must be removed
     */
    public void removeCurrentBooking(Room room, Booking booking) {
        if (booking == null || room == null) {
            throw new InvalidArgumentException("Invalid argument");
        }
        room.removeBooking(booking);
    }

    /**
     * Creates booking
     *
     * @param booking the new booking
     * @param room    the room that is going to be booked
     * @return ID of the room that has been booked
     */
    public int createReservation(Booking booking, Room room) {
        if (booking == null || room == null) {
            throw new InvalidArgumentException("Invalid argument");
        }
        return room.createBooking(booking);
    }

    private void findAvailableBookings(Room room, List<Room> availableRooms, LocalDate fromDate, LocalDate toDate, int days) {
        Set<Booking> availableBookings;

        if (room.getBookings().isEmpty()) {
            addToAvailableRooms(availableRooms, room);
        } else {
            availableBookings = room.findAvailableBookings(fromDate, toDate, days);
            checkAvailableBookings(availableBookings, availableRooms, room);
        }
    }

    private void addToAvailableRooms(List<Room> availableRooms, Room room) {
        availableRooms.add(room);
    }

    private void checkAvailableBookings(Set<Booking> availableBookings, List<Room> availableRooms, Room room) {
        if (!availableBookings.isEmpty()) {
            addToAvailableRooms(availableRooms, room);
        }
    }
}