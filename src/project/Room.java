package project;

public class Room {

    private int roomNum;
    private boolean isBooked;

    public Room(int roomNum, boolean isBooked) {
        this.roomNum = roomNum;
        this.isBooked = isBooked;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public Room() {

    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getRoomNum() {
        return roomNum;
    }


}

