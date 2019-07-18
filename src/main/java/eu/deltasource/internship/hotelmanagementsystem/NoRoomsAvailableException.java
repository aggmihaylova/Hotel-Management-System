package eu.deltasource.internship.hotelmanagementsystem;

public class NoRoomsAvailableException extends  RuntimeException{

    NoRoomsAvailableException(String exceptionMessage)  {
        super(exceptionMessage);
    }
}
