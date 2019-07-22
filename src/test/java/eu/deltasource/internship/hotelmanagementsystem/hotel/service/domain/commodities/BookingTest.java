package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

import eu.deltasource.internship.hotelmanagementsystem.InvalidBookingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @BeforeEach

    @Test
    void isDateValid() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 5, 12);
        LocalDate toDate = LocalDate.of(2019, 5, 23);
        Booking newBooking = new Booking(9304124583L);

        // when
        boolean result = newBooking.saveDate(fromDate, toDate);

        // then
        assertEquals(true, result);
    }


    @Test
    void isDateInvalid() {
        // given
        LocalDate fromDate = LocalDate.of(2020, 5, 12);
        LocalDate toDate = LocalDate.of(2019, 5, 23);
        Booking newBooking = new Booking(9304124583L);

        // when and then
        assertThrows(InvalidBookingException.class, () -> newBooking.saveDate(fromDate, toDate));
    }
}