package eu.deltasource.internship.hotelmanagementsystem.hotel.othertests;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Booking;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Hotel;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Manager;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Room;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.NoRoomsAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CaseTest {

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

        long guestID = 9405123453L;
        Booking booking = new Booking(guestID, firstFromDate, firstToDate);
        bookings.add(booking);

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
    public void createBookingCaseOne() {
        // given
        AbstractCommodity commodity = new Bed(commodities.size() + 1, BedType.DOUBLE);
        commodities.add(commodity);
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));
        rooms.get(0).saveCapacity(commodity);
        hotel.setRooms(rooms);
        manager.setHotel(hotel);
        int numberOfPeople = 2;
        int reserveID = 586;

        // when
        roomID = manager.createBooking(intervalFrom, intervalTo, numberOfPeople, reserveID, 2);

        //then
        assertEquals(rooms.get(0).getID(), roomID);
    }

    @Test
    public void createBookingCaseTwo() {
        // given
        hotel.setRooms(rooms);
        manager.setHotel(hotel);
        int numberOfPeople = 2;
        int reserveID = 586;

        // when and then
        assertThrows(NoRoomsAvailableException.class, () -> manager.createBooking(intervalFrom, intervalTo, numberOfPeople, reserveID, 2));
    }

    @Test
    public void createBookingCaseThree() {
        // given
        AbstractCommodity commodity = new Bed(commodities.size() + 1, BedType.SINGLE);
        commodities.add(commodity);
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));
        rooms.get(0).saveCapacity(commodity);
        hotel.setRooms(rooms);
        manager.setHotel(hotel);
        int numberOfPeople = 7;
        int reserveID = 324;

        // when and then
        assertThrows(NoRoomsAvailableException.class, () -> manager.createBooking(intervalFrom, intervalTo, numberOfPeople, reserveID, 1));
    }
}