package main.java;


import main.hotel.service.domain.commodities.AbstractCommodity;
import main.hotel.service.domain.commodities.Bed;
import main.hotel.service.domain.commodities.Booking;

import java.util.ArrayList;
import java.util.List;

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

    public Hotel(String name, ArrayList<Room> rooms) {
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

    /**
     * @param newBooking requested interval
     * @param beds       requested bed
     * @return target rom
     */
    public Room bookRoomByDate(Booking newBooking, Bed beds) throws Exception {

        Room targetRoom = new Room();

        try {
            targetRoom = findRoomByBeds(beds);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        if (targetRoom.checkForAvailability(newBooking))
            return targetRoom;
        return null;
    }

    /**
     * Search room by a bed's capacity
     *
     * @param bed requested bed
     * @return target room
     * @throws Exception if there is not appropriate room
     */


    public Room findRoomByBeds(Bed bed) throws Exception {

        for (Room room : rooms) {
            for (AbstractCommodity abstractCommodity : room.getCommodities()) {
                if (abstractCommodity instanceof Bed) {
                    if (((Bed) abstractCommodity).getNumberOfPersonas() == bed.getNumberOfPersonas())
                        return room;
                }
            }
        }

        throw new Exception("There is no room with bed for " + bed.getNumberOfPersonas());
    }

    /**
     * create reservation
     *
     * @param newBooking the new reservation
     * @param room       the room that will be booked
     */

    public void createReservation(Booking newBooking, Room room) {
        room.createBooking(newBooking);
    }
}
