package tests;

import commodities.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Hotel;
import project.Room;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    // other are getters and setters

    @Test
    void makeReservation() {

        ArrayList<Room> rooms = new ArrayList<>();

        rooms.add(new Room(3, null, null, null, (short) 3));

        Booking newBooking = new Booking(123521L, LocalDate.of(2018, 4, 12), LocalDate.of(2018, 4, 16), "Helene Joe");

        Hotel hotel = new Hotel("SomeName", rooms);


        assertEquals(null, hotel.bookRoomByDate(newBooking, 5));


    }
}