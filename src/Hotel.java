import java.util.ArrayList;

public class Hotel {

    private String name;
    private ArrayList<Room> rooms;

    public Hotel(String name, ArrayList<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public Hotel() {
        this.name = null;
        this.rooms = null;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }



    public void getFFreeRoom() {

        int i;
        for (i = 0; i < rooms.size(); i++)
            if (!rooms.get(i).isBooked())
                rooms.get(i).setBooked(true);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }


    public ArrayList<Room> getAllFreeRooms() {

        ArrayList<Room> freeRoms = new ArrayList<>();

        int i;
        for (i = 0; i < rooms.size(); i++)
            if (!rooms.get(i).isBooked())
                freeRoms.add(rooms.get(i));

        return freeRoms;

    }


    public boolean bookRoombyNum(int num) {


        for (int i = 0; i < rooms.size(); i++)
            if (rooms.get(i).getRoomNum() == num) {
                if (rooms.get(i).bookRoom())
                    rooms.get(i).setBooked(true);
                return true;
            }
        return false;

    }


    public void clearRooms() {
        for (int i = 0; i < rooms.size(); i++)
            rooms.get(i).setBooked(false);
    }

}
