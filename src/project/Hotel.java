package project;


import commodities.AbstractCommodity;
import commodities.Bed;
import commodities.Booking;

import java.util.ArrayList;
import java.util.spi.AbstractResourceBundleProvider;


public class Hotel {

    private String name;
    private ArrayList<Room> rooms;

    public Hotel(String name, ArrayList<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public Hotel() {

    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * under construction
     *
     * @param newBooking
     * @param beds
     * @return
     */
    public Room bookRoomByDate(Booking newBooking, Bed beds) {

        Room targetRoom = new Room();

        targetRoom = findRoomByBeds(beds);

        if (targetRoom != null) {
            if (targetRoom.checkForAvailability(newBooking))
                return targetRoom;
        }
        return null;
    }


    public Room findRoomByBeds(Bed beds) {

        for (Room room : rooms) {
            for (AbstractCommodity abstractCommodity : room.getCommodities()) {
                if (abstractCommodity instanceof Bed)
                    if (((Bed) abstractCommodity).getNumberOfPersonas() == beds.getNumberOfPersonas())
                        return room;
            }
        }

        return null;
    }

    /**
     * create reservation
     *
     * @param newBooking the new reservation
     * @param room       - the room that will be booked
     */


    public void addReservation(Booking newBooking, Room room) {
        room.createBooking(newBooking);
    }
}
