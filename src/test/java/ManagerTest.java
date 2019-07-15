package test.java;

import main.hotel.service.domain.commodities.AbstractCommodity;
import main.hotel.service.domain.commodities.Bed;
import main.hotel.service.domain.commodities.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.Hotel;
import main.java.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

class ManagerTest {

    // other methods are getters, setters and makeReservation(..) just calls another method

    Hotel hotel;
    Set<AbstractCommodity> abstractCommoditySet;
    Set<Booking> bookings;
    Booking booking;
    List<Room> rooms;

    @BeforeEach
    void setUp() {

        // creating hashset of bookings

        bookings = new HashSet<>();
        bookings.add(new Booking(9413043456L, LocalDate.of(2019, 05, 25),
                LocalDate.of(2019, 05, 27), "John"));

        // creating hashset of commodities

        abstractCommoditySet = new HashSet<>();
        abstractCommoditySet.add(new Bed(321, 3.5, 2.4, 2));

        // creating array list of rooms
        rooms = new ArrayList<>();
        rooms.add(new Room(32, abstractCommoditySet, null, bookings, (short) 5));

        hotel = new Hotel("Bordeaux", rooms);
    }

    @Test
    void tryReservation() throws Exception {

        // given
        Bed bed = new Bed(321, 3.5, 2.4, 2);
        Booking newBooking = new Booking(9312043211L, LocalDate.of(2019, 5, 10),
                LocalDate.of(2019, 5, 14), "David");

        // when
        Room targetRoom ;
        targetRoom = hotel.bookRoomByDate(newBooking, bed);

        // then

        assertNotNull(targetRoom);

    }
}