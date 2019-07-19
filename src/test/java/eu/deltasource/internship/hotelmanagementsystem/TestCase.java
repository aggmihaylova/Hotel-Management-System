package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.*;
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

    private Set<LocalDate> maintenanceDates;
    private List<Room> rooms;
    private Set<AbstractCommodity> commodities;
    private Set<Booking> bookings;
    private Hotel hotel;
    private Manager manager;
    private LocalDate intervalFrom;
    private LocalDate intervalTo;
    private int roomID;

    @BeforeEach
    public void SetUp() {

        // set of commodities
        commodities = new HashSet<>();

        // set of bookings
        bookings = new HashSet<>();

        LocalDate firstFromDate = LocalDate.of(2019, 04, 30);
        LocalDate firstToDate = LocalDate.of(2019, 05, 6);

        bookings.add(new Booking(0L, firstFromDate, firstToDate));

        // requested interval
        intervalFrom = LocalDate.of(2019, 1, 1);
        intervalTo = LocalDate.of(2019, 1, 2);

        // set of maintenance dates
        maintenanceDates = new HashSet<>();
        LocalDate maintenanceDate = LocalDate.of(2019, 5, 21);
        maintenanceDates.add(maintenanceDate);

        rooms = new ArrayList<>();

        hotel = new Hotel("Bordeaux");

        manager = new Manager("John", "Johnson", hotel);
    }

    @Test
    void createBookingCaseOne() {
        // given
        AbstractCommodity commodity = new Bed(BedType.DOUBLE);
        commodities.add(commodity);
        rooms.add(new Room(commodities, maintenanceDates, bookings));
        hotel.setRooms(rooms);
        manager.setHotel(hotel);

        // when
        roomID = manager.createBooking(intervalFrom, intervalTo, 2, 586);

        //then
        assertEquals(rooms.get(0).getID(), roomID);
    }

    @Test
    void createBookingCaseTwo() {
        // given
        hotel.setRooms(rooms);
        manager.setHotel(hotel);

        // when and then
        assertThrows(NoRoomsAvailableException.class, () -> manager.createBooking(intervalFrom, intervalTo, 2, 586));
    }

    @Test
    void createBookingCaseThree() {
        // given
        AbstractCommodity commodity = new Bed(BedType.SINGLE);
        commodities.add(commodity);
        rooms.add(new Room(101, commodities, maintenanceDates, bookings));
        hotel.setRooms(rooms);
        manager.setHotel(hotel);

        // when and then
        assertThrows(NoRoomsAvailableException.class, () -> manager.createBooking(intervalFrom, intervalTo, 2, 586));
    }
}
