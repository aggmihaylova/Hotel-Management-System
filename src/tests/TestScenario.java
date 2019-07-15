package tests;

import org.junit.jupiter.api.Test;
import project.*;
import commodities.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


/**
 * under construction
 */


public class TestScenario {


    @Test
    public void test() {

        // bookings for the first room


        Set<Booking> bookingsFirstRoom = new HashSet<>();
        bookingsFirstRoom.add(new Booking(9503123552L, LocalDate.of(2019, 5, 17),
                LocalDate.of(2019, 7, 6), "John Miller"));
        bookingsFirstRoom.add(new Booking(9412032156L, LocalDate.of(2019, 5, 25),
                LocalDate.of(2019, 7, 13), "Peter Johnson"));

        // bookings for the second room

        Set<Booking> bookingsSecondRoom = new HashSet<>();
        bookingsSecondRoom.add(new Booking(9621123421L, LocalDate.of(2019, 7, 22),
                LocalDate.of(2019, 7, 23), "Maya Mayova"));


        //bookings the third room

        Set<Booking> bookingsThirdRoom = new HashSet<>();
        bookingsThirdRoom.add(new Booking(0L, LocalDate.of(2019, 7, 20),
                LocalDate.of(2019, 7, 25), " "));


        Manager manager = new Manager("Pete", "Hennessy");

        // commodities for the first room

        Set<AbstractCommodity> firstRoom = new HashSet<>();
        firstRoom.add(new Bed(563, 3.5, 2.4, 2));
        firstRoom.add(new Toilet(385, true, "Green"));
        firstRoom.add(new Shower(375, true));

        // commodities for the first room

        Set<AbstractCommodity> secondRoom = new HashSet<>();
        secondRoom.add(new Bed(921, 2.5, 2.2, 1));
        secondRoom.add(new Bed(563, 3.5, 2.4, 2));
        secondRoom.add(new Bed(755, 2.5, 2.2, 1));
        secondRoom.add(new Toilet(342, false, "Blue"));
        secondRoom.add(new Toilet(281, true, "Red"));
        secondRoom.add(new Shower(444, true));

        // commodities for the third room

        Set<AbstractCommodity> thirdRoom = new HashSet<>();
        thirdRoom.add(new Bed(121, 2.5, 2.2, 1));
        thirdRoom.add(new Bed(654, 3.5, 2.4, 2));
        thirdRoom.add(new Toilet(383, false, "Yellow"));
        thirdRoom.add(new Shower(434, true));


        Set<LocalDate> maintenanceDatesSet = new HashSet<>();
        maintenanceDatesSet.add(LocalDate.of(2019, 04, 06));


        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, firstRoom, maintenanceDatesSet, bookingsFirstRoom, (short) 1));
        rooms.add(new Room(2, secondRoom, maintenanceDatesSet, bookingsSecondRoom, (short) 3));
        rooms.add(new Room(3, thirdRoom, maintenanceDatesSet, bookingsThirdRoom, (short) 2));


        Hotel hotel = new Hotel("Rose", rooms);
        manager.setHotel(hotel);

        Booking firstBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 20),
                LocalDate.of(2019, 7, 25), "Mariya");
        Booking secondBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 22),
                LocalDate.of(2019, 7, 23), "Ivan");
        Booking thirdBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 19),
                LocalDate.of(2019, 7, 21), "Peter");


 //       assertEquals(false, manager.makeReservation(firstBookingInterval, 2, 5));
   //     assertEquals(true, manager.makeReservation(secondBookingInterval, 1, 1));
     //   assertEquals(true, manager.makeReservation(thirdBookingInterval, 2, 15));

    }

}


