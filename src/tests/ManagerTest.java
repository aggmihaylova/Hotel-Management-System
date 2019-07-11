package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Hotel;
import project.Room;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {


    @BeforeEach
    void setUp() {
        System.out.println("Beginning of the test");
    }


    @Test
    public void bookRoomNum() throws Exception {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(12, true));
        rooms.add(new Room(3, false));
        rooms.add(new Room(7, true));
        Hotel hotel = new Hotel("Hotel", rooms);

        Assertions.assertThrows(Exception.class, () -> hotel.bookRoomByNum(7));

    }

    @Test
    public void bookFreeRoom() {

        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(12, true));
        rooms.add(new Room(3, false));
        rooms.add(new Room(7, false));
        Hotel hotel = new Hotel("Hotel", rooms);

        hotel.bookFirstFreeRoom();

        assertEquals(true, rooms.get(1).isBooked());

    }

    @Test
    public void clearFreeRooms() {

        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(12, true));
        rooms.add(new Room(3, false));
        rooms.add(new Room(7, true));

        Hotel hotel = new Hotel("Hotel", rooms);

        hotel.clearRooms();

        for (Room room : rooms)
            assertEquals(false, room.isBooked());

    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test");
    }
}