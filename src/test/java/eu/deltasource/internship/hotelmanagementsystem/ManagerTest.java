package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ManagerTest {

    private List<Room> rooms;
    private Set<AbstractCommodity> commodities;
    private Set<Booking> bookings;
    private Hotel hotel;
    private Manager manager;

    @BeforeEach
    public void SetUp() {

        //  set of commodities
        commodities = new HashSet<>();
        Bed bed = new Bed(commodities.size() + 1, BedType.DOUBLE);
        commodities.add(bed);

        //  set of bookings
        LocalDate firstFromDate = LocalDate.of(2019, 04, 30);
        LocalDate firstToDate = LocalDate.of(2019, 05, 6);
        LocalDate secondFromDate = LocalDate.of(2019, 05, 15);
        LocalDate secondToDate = LocalDate.of(2019, 05, 20);

        // set of bookings
        bookings = new HashSet<>();
        Booking bookingFirst = new Booking(9405154582L);
        bookingFirst.saveDate(firstFromDate, firstToDate);
        Booking bookingSecond = new Booking(9503071345L);
        bookingSecond.saveDate(secondFromDate, secondToDate);
        bookings.add(bookingFirst);
        bookings.add(bookingSecond);

        // set of maintenance dates
        Set<LocalDate> maintenanceDates = new HashSet<>();
        LocalDate maintenanceDate = LocalDate.of(2019, 5, 21);
        maintenanceDates.add(maintenanceDate);

        //  array list of rooms
        rooms = new ArrayList<>();
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));
        rooms.get(0).saveCapacity(bed);

        hotel = new Hotel("Bordeaux", rooms);

        manager = new Manager("John", "Johnson", hotel);
    }

    @Test
    void checkIfBookingSuccessful() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 10, 3);
        LocalDate toDate = LocalDate.of(2019, 5, 14);
        int numberOfPeople = 2;
        int reserveID = 586;
        int expectedRoomID = 1;

        // when
        int roomID = manager.createBooking(fromDate, toDate, numberOfPeople, reserveID);

        //then
        assertThat("The Booking was successful", roomID, is(equalTo(expectedRoomID)));
    }

    @Test
    void checkIfBookingUnsuccessful() {

        // given
        LocalDate fromDate = LocalDate.of(2019, 10, 3);
        LocalDate toDate = LocalDate.of(2019, 5, 14);
        int numberOfPeople = 7;
        int reserveID = 657;

        // when and then
        assertThrows(NoRoomsAvailableException.class, () -> manager.createBooking(fromDate, toDate, numberOfPeople, reserveID));
    }
}