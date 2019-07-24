package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;
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

public class HotelTest {

    private Hotel hotel;
    private Set<AbstractCommodity> commodities = new HashSet<>();
    private Set<Booking> bookings = new HashSet<>();
    private List<Room> rooms = new ArrayList<>();
    private Set<LocalDate> maintenanceDates = new HashSet<>();

    @BeforeEach
    public void setUp() {
        // set of bookings
        long guestID = 9504124582L;
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 27);
        Booking newBooking = new Booking(guestID, fromDate, toDate);

        Bed bed = new Bed(commodities.size() + 1, BedType.TRIPLE);
        commodities.add(bed);

        bookings.add(newBooking);

        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));

        hotel = new Hotel("Bordeaux", rooms);
    }

    @Test
    public void checkForAvailableRooms() {
        // given
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 12);
        int numberOfPeople = 3;
        int sizeOfFreeRooms = 1;
        List<Room> freeRooms;

        // when
        freeRooms = hotel.findAvailableRooms(from, to, numberOfPeople, 5);

        // then
        assertThat("There is no available room", freeRooms.size(), is(equalTo(sizeOfFreeRooms)));
    }

    @Test
    public void checkUnavailableRooms() {
        // given
        LocalDate from = LocalDate.of(2019, 3, 3);
        LocalDate to = LocalDate.of(2019, 3, 12);
        int numberOfPeople = 12;
        int size = 0;

        // when
        List<Room> freeRoom = hotel.findAvailableRooms(from, to, numberOfPeople, 6);

        // then
        assertThat("There are available room", freeRoom.size(), is(equalTo(size)));
    }

    @Test
    public void checkRoomsNull() {
        //given
        List<Room> newRooms = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> hotel.setRooms(newRooms));
    }

    @Test
    public void checkInvalidHotelName() {
        //given
        String hotelName = null;
        String nameHotel = "";

        //when 1 and then 1
        assertThrows(MissingArgumentException.class, () -> hotel.setName(hotelName));

        // when 2 and then 2
        assertThrows(MissingArgumentException.class, () -> hotel.setName(nameHotel));
    }
}
