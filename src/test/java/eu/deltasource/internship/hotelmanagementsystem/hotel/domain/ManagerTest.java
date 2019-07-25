package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
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

    private List<Room> rooms = new ArrayList<>();
    private Set<AbstractCommodity> commodities = new HashSet<>();
    private Set<Booking> bookings = new HashSet<>();
    private String hotelName = "Rose";
    private Set<LocalDate> maintenanceDates = new HashSet<>();
    private Hotel hotel = new Hotel(hotelName);
    private Manager manager;
    private Bed bed = new Bed(commodities.size() + 1, BedType.DOUBLE);

    @BeforeEach
    public void SetUp() {

        // creating and adding bookings in the set of bookings
        LocalDate firstFromDate = LocalDate.of(2019, 04, 30);
        LocalDate firstToDate = LocalDate.of(2019, 05, 6);
        LocalDate secondFromDate = LocalDate.of(2019, 05, 15);
        LocalDate secondToDate = LocalDate.of(2019, 05, 20);

        long firstGuestID = 9405154582L;
        long secondGuestID = 9407124563L;

        Booking firstBooking = new Booking(bookings.size() + 1, "John Miller", firstGuestID, firstFromDate, firstToDate);
        bookings.add(firstBooking);

        Booking secondBooking = new Booking(bookings.size() + 1, "Peter Jackson", secondGuestID, secondFromDate, secondToDate);
        bookings.add(secondBooking);

        // adding commodity in the set of commodities
        commodities.add(bed);

        // adding room in the set of rooms
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));

        // set a hotel with rooms
        hotel.setRooms(rooms);

        // creating manager
        manager = new Manager("John", "Johnson", hotel);
    }

    @Test
    public void checkIfBookingSuccessfull() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 5, 7);
        LocalDate toDate = LocalDate.of(2019, 5, 25);
        int numberOfPeople = 2, expectedRoomID = 1;
        long guestID = 9405121343L;

        // when
        int roomID = manager.createBooking(fromDate, toDate, numberOfPeople, guestID, 3);

        //then
        assertThat(roomID, is(equalTo(expectedRoomID)));
    }

    @Test
    public void checkIfBookingUnsuccessful() {
        // given I
        LocalDate fromDate = LocalDate.of(2019, 5, 3);
        LocalDate toDate = LocalDate.of(2019, 5, 14);
        int numberOfPeople = 7, days = 3;
        long guestID = 9503123452L;

        // when and then I
        assertThrows(NoRoomsAvailableException.class, () -> manager.createBooking(fromDate, toDate, numberOfPeople, guestID, days));

        // given II
        LocalDate from = null;

        //when and then II
        assertThrows(InvalidBookingException.class, () -> manager.createBooking(from, toDate, numberOfPeople, guestID, days));
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
