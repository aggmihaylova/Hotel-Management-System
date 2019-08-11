package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.NoRoomsAvailableException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerTest {
    private String hotelName = "Rose";
    private Hotel hotel = new Hotel(hotelName);
    private Manager manager;


    @BeforeEach
    public void SetUp() {
        manager = new Manager("John", "Johnson", hotel);
    }

    @Test
    public void createBookingSuccessfully() {
        // given
        initializeManagerFields();
        LocalDate fromDate = LocalDate.of(2019, 5, 7);
        LocalDate toDate = LocalDate.of(2019, 5, 25);
        int numberOfPeople = 2, expectedRoomID = 1;
        String guestID = "9405121343";
        String guestName = "John Miller";

        // when
        int roomID = manager.createBooking(fromDate, toDate, numberOfPeople, guestID, 3, guestName);

        //then
        assertThat(roomID, is(equalTo(expectedRoomID)));
    }

    @Test
    public void createBookingUnsuccessfully() {
        // given
        List<Room> rooms = new ArrayList<>();
        hotel.setRooms(rooms);
        LocalDate fromDate = LocalDate.of(2019, 5, 3);
        LocalDate from = null;
        LocalDate toDate = LocalDate.of(2019, 5, 14);
        int numberOfPeople = 7, days = 3;
        String guestID = "9503123452";
        String guestName = "John Miller";

        // when and then
        assertThrows(NoRoomsAvailableException.class,
                () -> manager.createBooking(fromDate, toDate, numberOfPeople, guestID, days, guestName));
        assertThrows(InvalidBookingException.class,
                () -> manager.createBooking(from, toDate, numberOfPeople, guestID, days, guestName));
    }

    @Test
    public void hotelNullCheck() {
        // given
        hotel = null;

        // when and then
        assertThrows(InvalidArgumentException.class, () -> manager.setHotel(hotel));
    }

    @Test
    public void managerValidFullName() {
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
    public void managerInvalidFullName() {
        // given
        String firstName = new String();
        String lastName = null;

        // when and then
        assertThrows(InvalidArgumentException.class, () -> manager.setFirstName(firstName));
        assertThrows(InvalidArgumentException.class, () -> manager.setLastName(lastName));
    }

    @Test
    public void roomThatHasNoBookings() {
        //given
        Set<AbstractCommodity> commodities = new HashSet<>();
        commodities.add(new Bed(commodities.size() + 1, BedType.KING));
        List<Room> rooms = new ArrayList<>();
        LocalDate fromDate = LocalDate.of(2019, 04, 30);
        LocalDate toDate = LocalDate.of(2019, 05, 6);
        int roomID = rooms.size() + 1, days = 3, numOfPeople = 2;
        rooms.add(new Room(roomID, commodities));
        Hotel hotel = new Hotel("Rose", rooms);
        Manager manager = new Manager("John", "Peterson", hotel);
        String guestID = "9504243212";
        String guestName = "John Miller";

        // when
        int bookedRoomID = manager.createBooking(fromDate, toDate, numOfPeople, guestID, days, guestName);

        //then
        assertTrue(bookedRoomID == roomID);
    }

    @AfterEach
    public void tearDown() {
        manager = null;
    }

    public void initializeManagerFields() {
        // dates
        LocalDate firstFromDate = LocalDate.of(2019, 04, 30);
        LocalDate firstToDate = LocalDate.of(2019, 05, 6);
        LocalDate secondFromDate = LocalDate.of(2019, 05, 15);
        LocalDate secondToDate = LocalDate.of(2019, 05, 20);

        String firstGuestID = "9405154582";
        String secondGuestID = "9407124563";

        // bookings
        Booking firstBooking = new Booking(1, "John Miller", firstGuestID, firstFromDate, firstToDate);
        Booking secondBooking = new Booking(2, "Peter Jackson", secondGuestID, secondFromDate, secondToDate);


        // adding commodity in the set of commodities
        Set<AbstractCommodity> commodities = new HashSet<>();
        Bed bed = new Bed(commodities.size() + 1, BedType.DOUBLE);
        commodities.add(bed);

        // adding room in the set of rooms
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(rooms.size() + 1, commodities));

        hotel.setRooms(rooms);
    }
}