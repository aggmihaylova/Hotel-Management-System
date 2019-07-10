package project;

public class Manager {

    private String firstName;
    private String lastName;
    private Hotel hotel;


    public Manager(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hotel = new Hotel();

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    /**
     * book room by number
     *
     * @param num - number of the room which I want to book
     */

    public void bookRoomNum(int num) {

        try {
            hotel.bookRoomByNum(num);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            bookFreeRoom(); // handled
        }
    }


    /**
     * call bookFirstFreeRoom()
     */

    public void bookFreeRoom() {

        hotel.bookFirstFreeRoom();
    }

    public void clearFreeRooms() {

        hotel.clearRooms();
    }

}

