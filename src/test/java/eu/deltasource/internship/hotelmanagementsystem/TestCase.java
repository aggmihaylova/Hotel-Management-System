package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.BedSize;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestCase {


    private List<Room> rooms;
    private Set<AbstractCommodity> commodities;
    private Set<Booking> bookings;
    private Hotel hotel;
    private Manager manager;
    private Booking interval;
    private int roomID;


    @BeforeEach
    public void SetUp() {

        // create HashSet of commodities (in this case - only one bed)
        commodities = new HashSet<>();


        // create HashSet of bookings
        bookings = new HashSet<>();

        // add bookings
        bookings.add(new Booking(0L, LocalDate.of(2019, 04, 30),
                LocalDate.of(2019, 05, 6)));
        bookings.add(new Booking(1L, LocalDate.of(2019, 05, 15),
                LocalDate.of(2019, 05, 20)));

        // user interval
        interval = new Booking(9505124521L, LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 1, 1));

        rooms = new ArrayList<>();

        hotel = new Hotel("Bordeaux", rooms);

        manager = new Manager("John", "Johnson");

    }

    @Test
    void createBookingCaseOne() throws UnavailableRooms {

        // given
        AbstractCommodity commodity = new Bed(123, BedSize.DOUBLE);
        commodities.add(commodity);
        rooms.add(new Room(101, commodities, null, bookings, (short) 1));
        manager.setHotel(hotel);


        // when
        roomID = manager.createBooking(interval.getFrom(), interval.getTo(), 2, 586);

        //then
        assertEquals(101, roomID);

    }

    @Test
    void createBookingCaseTwo() throws UnavailableRooms {

        // given
        hotel = new Hotel("Bordeaux", rooms);
        manager.setHotel(hotel);

        // when and then
        UnavailableRooms unavailableRooms = assertThrows(UnavailableRooms.class,
                () -> manager.createBooking(interval.getFrom(), interval.getTo(), 2, 586));


    }

    @Test
    void createBookingCaseThree() throws UnavailableRooms {

        // given and when
        AbstractCommodity commodity = new Bed(482, BedSize.SINGLE);
        commodities.add(commodity);
        rooms.add(new Room(101, commodities, null, bookings, (short) 1));
        hotel = new Hotel("Bordeaux", rooms);
        manager.setHotel(hotel);

        // when and then
        assertThrows(UnavailableRooms.class,
                () -> manager.createBooking(interval.getFrom(), interval.getTo(), 2, 586));

    }

    @AfterEach
    public void TearDown() {
        System.out.println("End of the test");
    }

}





