package eu.deltasource.internship.hotelmanagementsystem;


import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 *
 *    UNDER CONSTRUCTION
 *
 *
 *
 */



public class TestScenario {



    @Test
    public void test() throws Exception {
/*
        // given

        // bookings for the first room

        Set<Booking> bookingsFirstRoom = new HashSet<>();
        bookingsFirstRoom.add(new Booking(9503123552L, LocalDate.of(2019, 5, 17),
                LocalDate.of(2019, 6, 6), "John Miller"));
        bookingsFirstRoom.add(new Booking(9412032156L, LocalDate.of(2019, 6, 25),
                LocalDate.of(2019, 7, 13), "Peter Johnson"));

        // bookings for the second room

        Set<Booking> bookingsSecondRoom = new HashSet<>();
        bookingsSecondRoom.add(new Booking(9621123421L, LocalDate.of(2019, 7, 22),
                LocalDate.of(2019, 7, 23), "Maya Mayova"));


        //bookings the third room

        Set<Booking> bookingsThirdRoom = new HashSet<>();
        bookingsThirdRoom.add(new Booking(0L, LocalDate.of(2019, 7, 19),
                LocalDate.of(2019, 7, 21), " "));


        Manager manager = new Manager("Pete", "Hennessy");

        // commodities for the first room

        Set<AbstractCommodity> firstRoom = new HashSet<>();
        firstRoom.add(new Bed(563,  2));
        firstRoom.add(new Toilet(385, true, "Green"));
        firstRoom.add(new Shower(375, true));

        // commodities for the first room

        Set<AbstractCommodity> secondRoom = new HashSet<>();
        secondRoom.add(new Bed(921,  3));
        secondRoom.add(new Bed(563,  2));
        secondRoom.add(new Bed(755,  5));
        secondRoom.add(new Toilet(342, false, "Blue"));
        secondRoom.add(new Toilet(281, true, "Red"));
        secondRoom.add(new Shower(444, true));

        // commodities for the third room

        Set<AbstractCommodity> thirdRoom = new HashSet<>();
        thirdRoom.add(new Bed(121,  4));
        thirdRoom.add(new Bed(654, 7));
        thirdRoom.add(new Toilet(383, false, "Yellow"));
        thirdRoom.add(new Shower(434, true));


        Set<LocalDate> maintenanceDatesSet = new HashSet<>();
        maintenanceDatesSet.add(LocalDate.of(2019, 04, 06));


        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, firstRoom, maintenanceDatesSet, bookingsFirstRoom, (short) 1));
        rooms.add(new Room(2, secondRoom, maintenanceDatesSet, bookingsSecondRoom, (short) 3));
        rooms.add(new Room(3, thirdRoom, maintenanceDatesSet, bookingsThirdRoom, (short) 2));


        Hotel hotel = new Hotel("Rose", (ArrayList<Room>) rooms);
        manager.setHotel(hotel);

        // required intervals

        Booking firstBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 20),
                LocalDate.of(2019, 7, 25), "Mariya");
        Booking secondBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 22),
                LocalDate.of(2019, 7, 23), "Ivan");
        Booking thirdBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 19),
                LocalDate.of(2019, 7, 21), "Peter");

        // requested beds

        Bed firstRoomBed = new Bed(213, 2);
        Bed secondRoomBed = new Bed(326,  10);
        Bed thirdRoomBed = new Bed(381, 4);

        //  when 1
 //       Room room = manager.tryReservation(firstBookingInterval, firstRoomBed);

        // then
    //    assertThat("Unavailable room with bed for 2", room, is(not(equalTo(null))));
      //  room.createBooking(firstBookingInterval);


        // when 2
     //   room = manager.tryReservation(secondBookingInterval, secondRoomBed);

        // then
        //assertThat("Available room for n ", room, is(equalTo(null)));


        // when 3
       // room = manager.tryReservation(thirdBookingInterval, thirdRoomBed);

        //then
      //  assertThat("Free date", room, is(equalTo(null))); // busy date

    }
*/
    }
}

