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

    private String hotelName = "Rose";
    private Set<LocalDate> maintenanceDates = new HashSet<>();
    private List<Room> rooms;
    private Set<AbstractCommodity> commodities = new HashSet<>();
    private Set<Booking> bookings = new HashSet<>();
    private Hotel hotel = new Hotel(hotelName);
    private Manager manager;
    private LocalDate intervalFrom = LocalDate.of(2020, 1, 1);
    private LocalDate intervalTo = LocalDate.of(2020, 1, 2);
    private int roomID;

    @BeforeEach
    public void SetUp() {

        LocalDate firstFromDate = LocalDate.of(2019, 04, 30);
        LocalDate firstToDate = LocalDate.of(2019, 05, 6);

        String guestName = "John Miller";
        String guestID = "9405123453";
        Booking booking = new Booking(bookings.size() + 1, guestName, guestID, firstFromDate, firstToDate);
        bookings.add(booking);

        rooms = new ArrayList<>();

        manager = new Manager("John", "Johnson", hotel);
    }

    @Test
    public void createBookingCaseOne() {
        // given
        AbstractCommodity commodity = new Bed(commodities.size() + 1, BedType.DOUBLE);
        commodities.add(commodity);
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));
        int numberOfPeople = 2, days = 1;
        String guestID = "9603134632";
        hotel.setRooms(rooms);
        manager.setHotel(hotel);
        String guestName = "Kate Johnson";

        // when
        roomID = manager.createBooking(intervalFrom, intervalTo, numberOfPeople, guestID, days, guestName);

        //then
        assertEquals(rooms.get(0).getID(), roomID);
    }

    @Test
    public void createBookingCaseTwo() {
        // given
        hotel.setRooms(rooms);
        manager.setHotel(hotel);
        int numberOfPeople = 2, days = 1;
        String guestID = "9604123452";
        String guestName = "Kate Johnson";

        // when and then
        assertThrows(NoRoomsAvailableException.class,
                () -> manager.createBooking(intervalFrom, intervalTo, numberOfPeople, guestID, days, guestName));
    }

    @Test
    public void createBookingCaseThree() {
        // given
        AbstractCommodity commodity = new Bed(commodities.size() + 1, BedType.SINGLE);
        commodities.add(commodity);
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));
        hotel.setRooms(rooms);
        manager.setHotel(hotel);
        int numberOfPeople = 7, days = 1;
        String guestID = "9604123462";
        String guestName="Kate Johnson";

        // when and then
        assertThrows(NoRoomsAvailableException.class,
                () -> manager.createBooking(intervalFrom, intervalTo, numberOfPeople, guestID, days,guestName));
    }
}