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
        AbstractCommodity bed = new Bed(BedType.DOUBLE);
        commodities.add(bed);

        //  set of bookings
        LocalDate firstFromDate = LocalDate.of(2019, 04, 30);
        LocalDate firstToDate = LocalDate.of(2019, 05, 6);
        LocalDate secondFromDate = LocalDate.of(2019, 05, 15);
        LocalDate secondToDate = LocalDate.of(2019, 05, 20);


        bookings = new HashSet<>();
        bookings.add(new Booking(0L, firstFromDate, firstToDate));
        bookings.add(new Booking(1L, secondFromDate, secondToDate));

        // set of maintenance dates
        Set<LocalDate> maintenanceDates = new HashSet<>();
        LocalDate maintenanceDate = LocalDate.of(2019, 5, 21);
        maintenanceDates.add(maintenanceDate);


        //  array list of rooms
        rooms = new ArrayList<>();
        rooms.add(new Room(commodities, maintenanceDates, bookings));

        hotel = new Hotel("Bordeaux", rooms);

        manager = new Manager("John", "Johnson", hotel);

    }

    @Test
    void createBooking() {

        // given
        LocalDate fromDate = LocalDate.of(2019, 10, 3);
        LocalDate toDate = LocalDate.of(2019, 5, 14);

        // when
        int roomID = manager.createBooking(fromDate, toDate, 2, 586);

        //then
        assertThat("The Booking was successful", roomID, is(equalTo(1)));

    }
}