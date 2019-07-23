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

    public Set<LocalDate> getMaintenanceDates() {
        return maintenanceDates;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setMaintenanceDates(Set<LocalDate> maintenanceDates) {
        if (maintenanceDates == null) {
            throw new MissingArgumentException("Invalid set of maintenance dates !");
        }
        this.maintenanceDates = new HashSet<>(maintenanceDates);
    }

    public void setBookings(Set<Booking> bookings) {
        if (bookings == null) {
            throw new MissingArgumentException("Invalid set of bookings !");
        }
        this.bookings = new HashSet<>(bookings);
    }

    public void setCommodities(Set<AbstractCommodity> commodities) {
        if (commodities == null) {
            throw new MissingArgumentException("Invalid set of commodities !");
        }
        this.commodities = new HashSet<>(commodities);
    }


    /**
     * Adds a commodity
     *
     * @param commodity new commodity
     */
    public void addCommodity(AbstractCommodity commodity) {
        if (commodity == null) {
            throw new MissingArgumentException("Invalid commodity !");
        }
        saveCapacity(commodity);
        commodities.add(commodity);
    }

    /**
     * Calculates the capacity
     *
     * @param commodity
     */
    public void saveCapacity(AbstractCommodity commodity) {
        if (commodity instanceof Bed)
            capacity += ((Bed) commodity).getBedType().getSize();
    }

    /**
     * Calls saveCapacity() method for each commodity
     *
     * @param commodities set of commodities
     */
    public void findCapacity(Set<AbstractCommodity> commodities) {
        for (AbstractCommodity commodity : commodities)
            saveCapacity(commodity);
    }

    /**
     * Prepare room
     *
     * @param date - the date on which the room is ready to be booked
     */
    public void prepareRoom(LocalDate date) {
        if (date == null) {
            throw new MissingArgumentException("Invalid date !");
        }
        for (LocalDate maintenanceDate : maintenanceDates) ;
        maintenanceDates.add(date);
    }

    /**
     * Adds new booking to the set of bookings
     *
     * @param newBooking new reservation
     * @return the number of the room that has been booked
     */
    public int createBooking(Booking newBooking) {
        bookings.add(newBooking);
        prepareRoom(newBooking.getFrom());
        return ID;
    }

    /**
     * Removes booking
     *
     * @param removeBooking the booking that will be removed
     * @throws Exception - if the there is no such booking
     */
    public boolean removeBooking(Booking removeBooking) {
        if (!checkForAvailability(removeBooking)) {
            bookings.remove(removeBooking);
            return true;
        }
        throw new InvalidBookingException("Such booking does not exist!");
    }

    /**
     * Checks if the booking exists in the set of bookings
     *
     * @param newBooking requested booking
     * @return true if the booking can be made
     */
    public boolean checkForAvailability(Booking newBooking) {
        for (Booking book : bookings) {
            if (book.equals(newBooking))
                return false;
        }
        return true;
    }

    /**
     * Searches for free dates
     * ---------------------
     * Under construction
     * --------------------
     *
     * @param fromDate date
     * @param toDate   date
     * @return available dates
     */
    public Set<Booking> findAvailableDatesForIntervalAndSize(LocalDate fromDate, LocalDate toDate) {

        List<Booking> bookedDates = new ArrayList<>(bookings);
        Collections.sort(bookedDates);

        Set<Booking> freeDates = new HashSet<>();

        for (int i = 0; i < bookedDates.size() - 1; i++) {
            if (fromDate.isAfter(bookedDates.get(i).getTo()) && toDate.isBefore(bookedDates.get(i + 1).getFrom())) {
                freeDates.add(new Booking(0L, bookedDates.get(i).getTo(), (bookedDates.get(i + 1).getFrom())));
            }
        }
        return freeDates;
    }

    // under construction
    public void checkIfDatesAvailable(LocalDate from, LocalDate to) {
        bookings.forEach(a -> {
            from.isBefore(a.getFrom());
            to.isBefore(a.getFrom());

            from.isAfter(a.getTo());
            to.isAfter(a.getTo());
        });
    }
}