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

    public void setFlower(Hotel hotel) {
        this.hotel = hotel;
    }



    public void bookRoomNum(int num) {

        if (hotel.bookRoombyNum(num) == false)
            System.out.printf("Invalid room number !");
        else
            System.out.printf("Booking is done! ");
    }



    public void getFreeRoom() {

        hotel.getFFreeRoom();
    }

    public void clearFreeRooms() {
        hotel.clearRooms();
    }

}

