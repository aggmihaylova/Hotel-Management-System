package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    @Test
    void getAllFreeRooms() {

        ArrayList<Room> freeRoom = new ArrayList<Room>();
        freeRoom.add(new Room(12, true));
        freeRoom.add(new Room(3, false));
        freeRoom.add(new Room(7, true));
        freeRoom.add(new Room(37, false));
        freeRoom.add(new Room(21, false));
        Hotel hotel = new Hotel("Hotel", freeRoom);


        assertEquals(3, hotel.getAllFreeRooms().size());

    }

    @Test
    void bookFirstFreeRoom() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(12, true));
        rooms.add(new Room(3, false));
        rooms.add(new Room(7, false));
        Hotel hotel = new Hotel("Hotel", rooms);

        hotel.bookFirstFreeRoom();

        assertEquals(true, rooms.get(1).isBooked());


    }


    @Test
    void bookRoomByNum() throws IllegalArgumentException {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(12, true));
        rooms.add(new Room(3, false));
        rooms.add(new Room(7, true));
        Hotel hotel = new Hotel("Hotel", rooms);

        Assertions.assertThrows(IllegalArgumentException.class, () -> hotel.bookRoomByNum(18));

    }

    @Test
    void clearRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(12, true));
        rooms.add(new Room(3, false));
        rooms.add(new Room(7, true));

        Hotel hotel = new Hotel("Hotel", rooms);

        hotel.clearRooms();

        for (Room room : rooms)
            assertEquals(false, room.isBooked());


    }
}