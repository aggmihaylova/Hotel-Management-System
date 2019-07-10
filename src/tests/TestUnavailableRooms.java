package tests;


import org.junit.Test;
import project.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


public class TestUnavailableRooms {

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


}
