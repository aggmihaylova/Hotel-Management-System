package test.java;

import main.hotel.service.domain.commodities.AbstractCommodity;
import main.hotel.service.domain.commodities.Bed;
import main.hotel.service.domain.commodities.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.Hotel;
import main.java.Room;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class HotelTest {

    Set<AbstractCommodity> abstractCommoditySet;
    Set<Booking> bookings;
    Booking booking;
    short countBeds;
    List<Room> rooms;
    Hotel hotel;
    Bed bed;

    @BeforeEach
    void setUp() {
        System.out.println("Beginning of the test");
        // set of bookings

        bookings = new HashSet<>();
        bookings.add(new Booking(9413043456L, LocalDate.of(2019, 05, 23),
                LocalDate.of(2019, 05, 27), "John"));

        // set of commodities
        abstractCommoditySet = new HashSet<>();
        abstractCommoditySet.add(new Bed(321, 3.5, 2.4, 5));


        // array list of rooms
        rooms = new ArrayList<>();
        rooms.add(new Room(32, abstractCommoditySet, null, bookings, (short) 1));

        hotel = new Hotel("Rose", rooms);
    }

    @Test
    void bookRoomByDate() throws Exception {

//given
        Booking newBooking = new Booking(9312043211L, LocalDate.of(2019, 5, 23),
                LocalDate.of(2019, 5, 27), "Peter");

        // when
        Room room = hotel.bookRoomByDate(newBooking, bed);


        //  then
        assertEquals(null, room);

    }

    @Test
    void findRoomByBeds() throws Exception {

        //given
        Booking book = new Booking(9413043456L, LocalDate.of(2019, 05, 15),
                LocalDate.of(2019, 05, 18), "Mariya");
        //when
        Room target = hotel.bookRoomByDate(book, bed);

        // then
        assertEquals(null, target);

    }


    @AfterEach
    void tearDown() {
        System.out.println("End of the test");
    }
}