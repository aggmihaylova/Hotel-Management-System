package project;


import commodities.AbstractCommodity;
import commodities.Bed;
import commodities.Booking;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HotelServiceApplication {

    public static void main(String[] args) throws Exception {


        LocalDate localDateFrom = LocalDate.of(2019, 04, 30);
        LocalDate localDateTo = LocalDate.of(2019, 05, 6);

        LocalDate localDateFrom1 = LocalDate.of(2019, 05, 15);
        LocalDate localDateTo1 = LocalDate.of(2019, 05, 20);


        Set<Booking> bookings = bookings = new HashSet<>();

        Booking booking = new Booking(8403124532L, "Ivan Johnson");
        booking.saveDate(localDateFrom, localDateTo);


        Booking booking1 = new Booking(345L, "Peter X");
        booking1.saveDate(localDateFrom1, localDateTo1);


        bookings.add(booking);
        bookings.add(booking1);


        Manager manager = new Manager("John", "Miller");


        AbstractCommodity abstractCommodity = new Bed(324, 2.7, 3, 3);
        Set<AbstractCommodity> abstractCommodities = new HashSet<>();
        abstractCommodities.add(abstractCommodity);

        LocalDate maintenanceDates = LocalDate.of(2019, 03, 06);
        Set<LocalDate> maintenanceDatesSet = new HashSet<>();
        maintenanceDatesSet.add(maintenanceDates);


        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(3, abstractCommodities, maintenanceDatesSet, bookings, (short) 1));


        Hotel hotel = new Hotel("Rose", rooms);
        manager.setHotel(hotel);


        LocalDate fromInterval = LocalDate.of(2019, 5, 3);
        LocalDate toInterval = LocalDate.of(2019, 5, 16);


        Booking interval = new Booking(0L, "Mariya");
        interval.saveDate(fromInterval, toInterval);
        Booking freeInterval = new Booking();


        for (Room room : rooms) {

            System.out.println("Room with number " + room.getRoomNum() + " has " + room.getCountBeds() + " beds.");

            freeInterval = room.findAvailableDatesForIntervalAndSize(interval);

            if (freeInterval == null)
                System.out.println("The room hasn't been booked yet");
            else {
                System.out.println("The room is free from " + freeInterval.getFrom().getYear() + "-" + freeInterval.getFrom().getMonthValue() + "-" + freeInterval.getFrom().getDayOfMonth() +
                        " to " + freeInterval.getTo().getYear() + "-" + freeInterval.getTo().getMonthValue() + "-" + freeInterval.getTo().getDayOfMonth());
            }
        }

        Booking newBooking = new Booking(9714044552L, LocalDate.of(2019, 5, 7), LocalDate.of(2019, 5, 10), "John");
        int days = 3;

        int differenceInterval = newBooking.getTo().getDayOfMonth() - newBooking.getFrom().getDayOfMonth();

        if (newBooking.getFrom().compareTo(freeInterval.getFrom()) > 0
                && newBooking.getTo().compareTo(freeInterval.getTo()) < 0 &&
                differenceInterval >= days)
            if (manager.makeReservation(newBooking, 1, days))
                System.out.println("Successful booking!");

            else System.out.println("Unsuccessful booking");
    }
}



