package project;


import commodities.Booking;

import java.util.ArrayList;


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
     * @param countBeds
     * @return
     */
    public Room bookRoomByDate(Booking newBooking, int countBeds) {

        Room targetRoom = new Room();

        targetRoom = findRoomByBeds(countBeds);

        if (targetRoom != null) {
            if (targetRoom.checkForAvailability(newBooking))
                return targetRoom;
        }
        return null;
    }

    /**
     * try to find room by
     *
     * @param countBeds the required number of beds
     * @return the room which provides the required number of beds
     * @throws Exception if there is no room with countBeds
     */
    public Room findRoomByBeds(int countBeds) {

        for (Room room : rooms) {
            if (room.getCountBeds() == countBeds) {

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
