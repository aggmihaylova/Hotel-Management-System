import java.util.ArrayList;

public class HotelServiceApplication {

    public static void main(String[] args) {

        Manager manager = new Manager("John", "Miller");
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(12, false));
        rooms.add(new Room(32, false));
        Hotel hotel = new Hotel("Rose", rooms);
        manager.setFlower(hotel); // ?? xxx
        manager.getFreeRoom();
        manager.clearFreeRooms();


    }
}
