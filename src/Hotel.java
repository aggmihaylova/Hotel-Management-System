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

    }

    public ArrayList<Room> getRooms() {

        return rooms;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * get all free rooms
     *
     * @return ArrayList of fr
     * ee rooms
     */

    public ArrayList<Room> getAllFreeRooms() {

        ArrayList<Room> freeRooms = new ArrayList<>();

        int i;
        for (i = 0; i < rooms.size(); i++) {
            if (!rooms.get(i).isBooked()) {
                freeRooms.add(rooms.get(i));
            }
        }
        return freeRooms;

    }

    /**
     * search for the first free room and try to book it
     * the loop will continue until the first free room
     * after that the room is booked
     */


    public void bookFirstFreeRoom() {

        int i;
        for (i = 0; rooms.get(i).isBooked(); i++) ;
        rooms.get(i).setBooked(true);

    }

    /**
     * book free room
     * /
     *
     * @param num - number of the room which I want to book
     */

    public void bookRoomByNum(int num) throws Exception {

        char flg = 0;

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNum() == num) {
                if (!rooms.get(i).isBooked()) {
                    rooms.get(i).setBooked(true);
                    flg = 1;
                } else {
                    throw new Exception("The room has already been booked ");
                }
            }
        }
        if (flg == 0) {
            throw new IllegalArgumentException("Invalid room number");
        }
    }


    public void clearRooms() {
        for (int i = 0; i < rooms.size(); i++)
            rooms.get(i).setBooked(false);
    }

}
