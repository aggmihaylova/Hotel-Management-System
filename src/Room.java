public class Room {

    private int roomNum;
    private boolean bookedRoom;


    public boolean isBooked() {
        return (bookedRoom == true) ? true : false;
    }

    public Room(int roomNum, boolean bookedRoom) {
        this.roomNum = roomNum;
        this.bookedRoom = bookedRoom;
    }


    public Room() {
        this.roomNum = 0;
        this.bookedRoom = false;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setBooked(boolean booked) {
        this.bookedRoom = booked;
    }

    public int getRoomNum() {
        return roomNum;
    }


    public boolean bookRoom() {
        if (this.bookedRoom == true)
            return false;
        else
            return true;
    }
}

