package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {


    @Test
    void checkIfFromIsBeforeTo() {
        // given
        LocalDate from = LocalDate.of(2019, 8, 10);
        LocalDate to = LocalDate.of(2019, 8, 14);
        String guestName = "Mariya M";
        Long guestID = 9405142382L, ID = 1L;

        //when
        Booking newBooking = new Booking(ID, guestName, guestID, from, to);

        //then
        assertTrue(newBooking.getFrom().equals(from));

    }

    @Test
    void checkIfFromIsAfterTo() {
        // given
        LocalDate from = LocalDate.of(2019, 8, 24);
        LocalDate to = LocalDate.of(2019, 8, 14);
        String guestName = "Mariya M";
        Long guestID = 9405142382L, ID = 1L;

        //when and then
        assertThrows(InvalidBookingException.class, () -> new Booking(ID, guestName, guestID, from, to));
    }
}