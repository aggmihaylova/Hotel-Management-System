package project;

import commodities.AbstractCommodity;
import commodities.Booking;


import java.time.LocalDate;
import java.util.*;

public class Room {

    private int roomNum;
    private short countBeds;
    Set<AbstractCommodity> commodities;
    Set<LocalDate> maintenanceDates;
    Set<Booking> bookings;

    public Room(int roomNum, Set<AbstractCommodity> commodities, Set<LocalDate> maintenanceDates, Set<Booking> bookings, short countBeds) {
        this.roomNum = roomNum;
        this.commodities = commodities;
        this.maintenanceDates = maintenanceDates;
        this.bookings = bookings;
        this.countBeds = countBeds;
    }

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
     * @param date - the date on which the room is available
     */
    public void prepareRoom(LocalDate date) {

        for (int i = 0; i < commodities.size(); i++) ;
        maintenanceDates.add(date);
    }

    /**
     * Create new booking
     *
     * @param newBooking - new booking
     */

    public void createBooking(Booking newBooking) {
        prepareRoom(newBooking.getFrom());
        bookings.add(newBooking);
    }

    /**
     * Remove booking
     *
     * @param removeBooking the booking which should be removed
     * @throws Exception - if the there is not such booking
     */

    public void removeBooking(Booking removeBooking) throws Exception {
        if (!checkForAvailability(removeBooking)) {
            bookings.remove(removeBooking);
        }
        throw new Exception("Such booking does not exist!");

    }

    /**
     * check if there is a booking in the set of bookings
     *
     * @param newBooking the new booking a customer wants to make
     * @return true if the booking can be made
     */


    public boolean checkForAvailability(Booking newBooking) {

        for (Booking book : bookings) {
            if (!newBooking.equals(book))
                return true;
        }

        return false;
    }

    /**
     * under construction
     *
     * @param interval
     * @return
     */

    public Booking findAvailableDatesForIntervalAndSize(Booking interval) {

        List<Booking> bookedDates = new ArrayList<>(bookings);
        Collections.sort(bookedDates);


        for (int i = 0; i < bookedDates.size() - 1; i++) {
            if (interval.getFrom().compareTo(bookedDates.get(i).getTo()) >= 0) {
                return (new Booking(0L, bookedDates.get(i).getTo(), bookedDates.get(i + 1).getFrom(), null));
            } else
                return (new Booking(0L, bookedDates.get(i).getTo(), bookedDates.get(i + 1).getFrom(), null));

        }
        return null;
    }
}










































