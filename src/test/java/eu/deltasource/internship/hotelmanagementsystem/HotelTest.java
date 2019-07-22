package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class HotelTest {

    private Hotel hotel;
    private Set<AbstractCommodity> commodities;
    private Set<Booking> bookings;
    private List<Room> room;

    @BeforeEach
    void setUp() {

        Set<AbstractCommodity> commodities = new HashSet<>();
        Set<Booking> bookings = new HashSet<>();
        List<Room> rooms = new ArrayList<>();

        // set of bookings
        long guestID = 9504124582L;
        Booking newBooking = new Booking(guestID);
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 27);
        newBooking.saveDate(fromDate, toDate);

        bookings.add(newBooking);

        // set of commodities
        Bed bed = new Bed(commodities.size() + 1, BedType.TRIPLE);
        commodities.add(bed);

        // set of maintenance dates
        Set<LocalDate> maintenanceDates = new HashSet<>();
        LocalDate maintenanceDate = LocalDate.of(2019, 5, 21);
        maintenanceDates.add(maintenanceDate);

        // array list of rooms
        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));
        rooms.get(0).saveCapacity(bed);

        hotel = new Hotel("Bordeaux", rooms);
    }

    @Test
    public void checkAndFindAvailableRooms() {
        // given
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 12);
        int numberOfPeople = 3;

        // when
        List<Room> freeRoom = hotel.findAvailableRooms(from, to, numberOfPeople);

        // then
        assertThat("There are no available rooms", freeRoom, is(not(equalTo(null))));
    }

    @Test
    public void checkAndFindUnavailableRooms() {
        LocalDate from = LocalDate.of(2019, 3, 3);
        LocalDate to = LocalDate.of(2019, 3, 12);
        int numberOfPeople = 12;
        int size = 0;

        // when
        List<Room> freeRoom = hotel.findAvailableRooms(from, to, numberOfPeople);


        // then
        assertThat("There are available rooms", freeRoom.size(), is((equalTo(size))));
    }
}
