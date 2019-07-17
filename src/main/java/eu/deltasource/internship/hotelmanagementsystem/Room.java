package eu.deltasource.internship.hotelmanagementsystem;


/**
 * Class Room has 5 private members
 * <p>
 * methods - setters, getters, constructors and other methods
 */

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;


import java.time.LocalDate;
import java.util.*;

public class Room {

    private int roomNum;
    private short countBeds;
    Set<AbstractCommodity> commodities;
    Set<LocalDate> maintenanceDates;
    Set<Booking> bookings;

    /**
     * Parametrized constructor
     *
     * @param roomNum          - room's number
     * @param commodities      - set of commodities
     * @param maintenanceDates - set of
     * @param bookings         - set of bookings
     * @param countBeds        - total number of beds
     */

    public Room(int roomNum, Set<AbstractCommodity> commodities,
                Set<LocalDate> maintenanceDates, Set<Booking> bookings, short countBeds) {
        this.roomNum = roomNum;
        this.commodities = commodities;
        this.maintenanceDates = maintenanceDates;
        this.bookings = bookings;
        this.countBeds = countBeds;
    }

    /**
     * Default constructor
     */


    public Room() {

    }

    public int getRoomNum() {
        return roomNum;
    }

    public short getCountBeds() {
        return countBeds;
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

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setCountBeds(short countBeds) {
        this.countBeds = countBeds;
    }

    public void setCommodities(Set<AbstractCommodity> commodities) {
        this.commodities = commodities;
    }

    public void setMaintenanceDates(Set<LocalDate> maintenanceDates) {
        this.maintenanceDates = maintenanceDates;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * prepare room
     *
     * @param date - the date on which the room is ready to be booked
     */
    public void prepareRoom(LocalDate date) {

        for (int i = 0; i < maintenanceDates.size(); i++) ;
        maintenanceDates.add(date);
    }

    /**
     * @param newBooking new reservation
     * @return the number of the room that has been booked
     */

    public int createBooking(Booking newBooking) {
        bookings.add(newBooking);
        return roomNum;
    }

    /**
     * Remove booking
     *
     * @param removeBooking the booking which should be removed
     * @throws Exception - if the there is no such booking
     */


    public boolean removeBooking(Booking removeBooking) throws Exception {
        if (!checkForAvailability(removeBooking)) {
            bookings.remove(removeBooking);
            prepareRoom(removeBooking.getFrom());
            return true;

        }
        throw new Exception("Such booking does not exist!");

    }

    /**
     * check if booking exists in the set of bookings
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
























