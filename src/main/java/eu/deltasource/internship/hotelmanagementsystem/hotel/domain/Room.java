package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidArgumentException;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
/**
 * Represents a room in a hotel
 */
public class Room {

    private int ID;
    private Set<AbstractCommodity> commodities;
    private Set<LocalDate> maintenanceDates;
    private Set<Booking> bookings;

    /**
     * This is a constructor
     *
     * @param ID          room's ID
     * @param commodities room commodities
     */
    public Room(int ID, Set<AbstractCommodity> commodities) {
        this.ID = ID;
        setCommodities(commodities);
        maintenanceDates = new HashSet<>();
        bookings = new HashSet<>();
    }

    /**
     * Initializes the set of commodities
     *
     * @param commodities manager's first name
     */
    public void setCommodities(Set<AbstractCommodity> commodities) {
        if (commodities == null || commodities.contains(null)) {
            throw new InvalidArgumentException("Invalid set of commodities !");
        }
        this.commodities = new HashSet<>(commodities);
    }

    /**
     * Adds a commodity
     *
     * @param commodity the new commodity
     */
    public void addCommodity(AbstractCommodity commodity) {
        if (commodity == null) {
            throw new InvalidArgumentException("Invalid commodity !");
        }
        commodities.add(commodity);
    }

    /**
     * Calculates room's capacity
     *
     * @return room's capacity
     */
    public int getCapacity() {
        int capacity = 0;
        for (AbstractCommodity commodity : commodities) {
            if (commodity instanceof Bed) {
                capacity += ((Bed) commodity).getBedType().getSize();
            }
        }
        return capacity;
    }

    /**
     * Adds new booking to the set of bookings
     *
     * @param booking new reservation
     * @return the number of the room that has been booked
     */
    public int createBooking(Booking booking) {
        if (booking == null) {
            throw new InvalidBookingException("Invalid date");
        }
        bookings.add(booking);
        prepareRoom(booking.getFrom());
        return ID;
    }

    /**
     * Removes booking
     *
     * @param removeBooking the booking that will be removed
     */
    public void removeBooking(Booking removeBooking) {
        if (bookings.contains(removeBooking)) {
            bookings.remove(removeBooking);
        } else {
            throw new InvalidBookingException("Such booking does not exist!");
        }
    }

    /**
     * Checks if the booking exists in the set of bookings in order to create booking
     *
     * @param newBooking requested booking
     * @return true if the room can be booked for this period because there is no duplicate bookings
     */
    public boolean checkForAvailability(Booking newBooking) {
        for (Booking booking : bookings) {
            if (booking.equals(newBooking)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Searches for free bookings /dates/
     *
     * @param fromDate date
     * @param toDate   date
     * @param days     duration of the booking
     * @return available bookings /dates/
     */
    public Set<Booking> findAvailableBookings(LocalDate fromDate, LocalDate toDate, int days) {

        LocalDate previousBookingToDate = fromDate;
        Set<Booking> freeBookings = new HashSet<>();

        for (Booking bookedDate : bookings) {
            if (!checkIntervalMatch(fromDate, bookedDate.getFrom(), toDate, bookedDate.getTo())) {
                continue;
            } else {
                if (freeBookings.isEmpty()) {
                    addFreeBookingsIfNotOverlapping(fromDate.plusDays(days), fromDate, bookedDate.getFrom(), freeBookings);
                } else {
                    addFreeBookingsIfNotOverlapping(previousBookingToDate.plusDays(days), previousBookingToDate, bookedDate.getFrom(), freeBookings);
                }
                previousBookingToDate = bookedDate.getTo();
            }
        }

        if (previousBookingToDate.isBefore(toDate)) {
            addFreeBookingsIfNotOverlapping(previousBookingToDate.plusDays(days), previousBookingToDate, toDate, freeBookings);
        }

        return freeBookings;
    }

    private void addFreeBookings(LocalDate from, LocalDate to, Set<Booking> freeBookings) {
        Booking newBooking = new Booking(from, to);
        freeBookings.add(newBooking);
    }

    private void addFreeBookingsIfNotOverlapping(LocalDate fromPlusDays, LocalDate from, LocalDate bookedFrom, Set<Booking> freeBookings) {
        if (fromPlusDays.isBefore(bookedFrom) || fromPlusDays.isEqual(bookedFrom)) {
            addFreeBookings(from, bookedFrom, freeBookings);
        }
    }

    private boolean checkIntervalMatch(LocalDate requestedFromDate, LocalDate roomBookingFromDate, LocalDate requestedToDate, LocalDate roomBookingToDate) {
        return (requestedFromDate.isBefore(roomBookingFromDate) && requestedToDate.isAfter(roomBookingToDate));
    }

    private void prepareRoom(LocalDate date) {
        for (AbstractCommodity commodity : commodities) {
            commodity.prepare();
        }
        maintenanceDates.add(date);
    }
}