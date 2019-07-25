package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;

import java.time.LocalDate;
import java.util.*;

/**
 * Represents a room in a hotel
 */
public class Room {

    private int ID;
    private int capacity;
    private Set<AbstractCommodity> commodities;
    private Set<LocalDate> maintenanceDates;
    private Set<Booking> bookings;

    /**
     * This is a constructor
     *
     * @param ID               room's ID
     * @param commodities      room commodities
     * @param maintenanceDates room maintenance dates
     * @param bookings         room bookings
     */
    public Room(int ID, Set<AbstractCommodity> commodities, Set<LocalDate> maintenanceDates, Set<Booking> bookings) {
        this.ID = ID;
        setCommodities(commodities);
        findCapacity(commodities);
        setMaintenanceDates(maintenanceDates);
        setBookings(bookings);
    }

    /**
     * This is a constructor
     *
     * @param ID          room's ID
     * @param commodities room commodities
     */
    public Room(int ID, Set<AbstractCommodity> commodities) {
        this.ID = ID;
        setCommodities(commodities);
        findCapacity(commodities);
        maintenanceDates = new HashSet<>();
        bookings = new HashSet<>();
    }

    public int getID() {
        return ID;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<AbstractCommodity> getCommodities() {
        return commodities;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    /**
     * Method that initializes/set the set of maintenance dates
     *
     * @param maintenanceDates the maintenance dates
     * @throw MissingArgumentException if the set is null
     */
    public void setMaintenanceDates(Set<LocalDate> maintenanceDates) {
        if (maintenanceDates == null) {
            throw new MissingArgumentException("Invalid set of maintenance dates !");
        }
        this.maintenanceDates = new HashSet<>(maintenanceDates);
    }

    /**
     * Method that initializes/set the set of bookings
     *
     * @param bookings set of bookings
     * @throw MissingArgumentException if the set  is null
     */
    public void setBookings(Set<Booking> bookings) {
        if (bookings == null) {
            throw new MissingArgumentException("Invalid set of bookings !");
        }
        this.bookings = new HashSet<>(bookings);
    }

    /**
     * Method that initializes/set the set of commodities
     *
     * @param commodities manager's first name
     * @throw MissingArgumentException if the set is null
     */
    public void setCommodities(Set<AbstractCommodity> commodities) {
        if (commodities == null) {
            throw new MissingArgumentException("Invalid set of commodities !");
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
            throw new MissingArgumentException("Invalid commodity !");
        }
        calculateCapacity(commodity);
        commodities.add(commodity);
    }

    /**
     * Calculates the capacity
     *
     * @param commodity
     */
    public void calculateCapacity(AbstractCommodity commodity) {
        if (commodity instanceof Bed)
            capacity += ((Bed) commodity).getBedType().getSize();
    }

    /**
     * Calls calculateCapacity() method for each commodity
     *
     * @param commodities set of commodities
     */
    private void findCapacity(Set<AbstractCommodity> commodities) {
        for (AbstractCommodity commodity : commodities)
            calculateCapacity(commodity);
    }

    /**
     * Prepares room
     *
     * @param date - the date on which the room is ready to be booked
     */
    public void prepareRoom(LocalDate date) {
        for (LocalDate maintenanceDate : maintenanceDates) ;
        maintenanceDates.add(date);
    }

    /**
     * Adds new booking to the set of bookings
     *
     * @param newBooking new reservation
     * @return the number of the room that has been booked
     * @throw InvalidBookingException if the @param is null
     */
    public int createBooking(Booking newBooking) {
        if (newBooking == null) {
            throw new InvalidBookingException("Invalid date");
        }
        bookings.add(newBooking);
        prepareRoom(newBooking.getFrom());
        return ID;
    }

    /**
     * Removes booking
     *
     * @param removeBooking the booking that will be removed
     * @throw InvalidBookingException - if the bookings does not exists
     */
    public void removeBooking(Booking removeBooking) {
        if (checkForAvailability(removeBooking)) {
            throw new InvalidBookingException("Such booking does not exist!");
        } else {
            bookings.remove(removeBooking);
        }
    }

    /**
     * Checks if the booking exists in the set of bookings
     *
     * @param newBooking requested booking
     * @return true if the booking can be made
     */
    public boolean checkForAvailability(Booking newBooking) {
        for (Booking book : bookings) {
            if (book.equals(newBooking)) {
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
                if (freeBookings.size() == 0) {
                    addFreeBookingsIfNoTOverlapping(fromDate.plusDays(days), fromDate, bookedDate.getFrom(), freeBookings);
                } else {
                    addFreeBookingsIfNoTOverlapping(previousBookingToDate.plusDays(days), previousBookingToDate, bookedDate.getFrom(), freeBookings);
                }
                previousBookingToDate = bookedDate.getTo();
            }
        }

        if (previousBookingToDate.isBefore(toDate)) {
            addFreeBookingsIfNoTOverlapping(previousBookingToDate.plusDays(days), previousBookingToDate, toDate, freeBookings);
        }

        return freeBookings;
    }

    private void addFreeBookings(LocalDate from, LocalDate to, Set<Booking> freeBookings) {
        Booking newBooking = new Booking(from, to);
        freeBookings.add(newBooking);
    }

    private void addFreeBookingsIfNoTOverlapping(LocalDate fromPlusDays, LocalDate from, LocalDate bookedFrom, Set<Booking> freeBookings) {
        if (fromPlusDays.isBefore(bookedFrom) || fromPlusDays.isEqual(bookedFrom)) {
            addFreeBookings(from, bookedFrom, freeBookings);
        }
    }

    private boolean checkIntervalMatch(LocalDate requestedFromDate, LocalDate roomBookingFromDate, LocalDate requestedToDate, LocalDate roomBookingToDate) {
        if (requestedFromDate.isBefore(roomBookingFromDate) && requestedToDate.isAfter(roomBookingToDate))
            return true;
        return false;
    }
}