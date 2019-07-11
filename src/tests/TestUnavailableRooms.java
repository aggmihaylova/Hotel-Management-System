package tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class TestUnavailableRooms {

    @BeforeEach
    void setUp() {
        System.out.println("Beginning of the test");
    }

    ArrayList<Room> rooms = new ArrayList<Room>();
    Hotel hotel = new Hotel("Rose", rooms);
    Manager manager = new Manager("Peter", "Johnson");

    @Test
    public void testForBookedRooms() {


        rooms.add(new Room(73, false));
        rooms.add(new Room(84, false));
        manager.setHotel(hotel);

        manager.bookRoomNum(73);
        manager.bookRoomNum(84);


        assertEquals(0, hotel.getAllFreeRooms().size());


    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test");
    }

}
