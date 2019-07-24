package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.NoRoomsAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTest {

    private List<Room> rooms;
    private Set<AbstractCommodity> commodities = new HashSet<>();
    private Set<Booking> bookings = new HashSet<>();
    Set<LocalDate> maintenanceDates = new HashSet<>();
    private Hotel hotel;
    private Manager manager;

    @BeforeEach
    public void SetUp() {
        Bed bed = new Bed(commodities.size() + 1, BedType.DOUBLE);
        commodities.add(bed);

        //  set of bookings
        LocalDate firstFromDate = LocalDate.of(2019, 04, 30);
        LocalDate firstToDate = LocalDate.of(2019, 05, 6);
        LocalDate secondFromDate = LocalDate.of(2019, 05, 15);
        LocalDate secondToDate = LocalDate.of(2019, 05, 20);

        long firstGuestID = 9405154582L;
        long secondGuestID = 9407124563L;

        Booking bookingFirst = new Booking(firstGuestID, firstFromDate, firstToDate);
        Booking bookingSecond = new Booking(secondGuestID, secondFromDate, secondToDate);

        bookings.add(bookingFirst);
        bookings.add(bookingSecond);

        //  array list of rooms
        rooms = new ArrayList<>();
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));

        hotel = new Hotel("Bordeaux", rooms);

        manager = new Manager("John", "Johnson", hotel);
    }

    @Test
    public void checkIfBookingSuccessful() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 10, 3);
        LocalDate toDate = LocalDate.of(2019, 10, 14);
        int numberOfPeople = 2;
        int reserveID = 586;
        int expectedRoomID = 1;

        // when
        int roomID = manager.createBooking(fromDate, toDate, numberOfPeople, reserveID, 3);

        //then
        assertThat(roomID, is(equalTo(expectedRoomID)));
    }

    @Test
    public void checkIfBookingUnsuccessful() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 5, 3);
        LocalDate toDate = LocalDate.of(2019, 5, 14);
        int numberOfPeople = 7;
        int reserveID = 657;

        // when and then
        assertThrows(NoRoomsAvailableException.class, () ->
                manager.createBooking(fromDate, toDate, numberOfPeople, reserveID, 3));
    }

    @Test
    public void checkInvalidHotel() {
        // given
        hotel = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> manager.setHotel(hotel));
    }

    @Test
    public void checkValidFullName() {
        // given
        String firstName = "John";
        String lastName = "Johnson";

        // when
        manager.setFirstName(firstName);
        manager.setLastName(lastName);

        //then
        assertThat(manager.getFirstName(), equalTo(firstName));
        assertThat(manager.getLastName(), equalTo(lastName));
    }

    @Test
    public void checkInvalidFullName() {

        // given
        String firstName = new String();
        String lastName = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> manager.setFirstName(firstName));
        assertThrows(MissingArgumentException.class, () -> manager.setLastName(lastName));
    }
}
