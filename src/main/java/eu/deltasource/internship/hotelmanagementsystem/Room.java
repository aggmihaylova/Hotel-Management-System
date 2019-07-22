package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;

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
     * @param ID               room's ID
     * @param commodities      room commodities
     * @param maintenanceDates room maintenance dates
     * @param bookings         room bookings
     */

    public Room(int ID, Set<AbstractCommodity> commodities, Set<LocalDate> maintenanceDates, Set<Booking> bookings) {
        this.commodities = new HashSet<>(commodities);
        this.maintenanceDates = new HashSet<>(maintenanceDates);
        this.bookings = new HashSet<>(bookings);
        this.ID = ID;
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


    public void saveCapacity(AbstractCommodity commodity) {

        if (commodity instanceof Bed)
            capacity += ((Bed) commodity).getBedType().getSize();
    }

    /**
     * prepare room
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
}