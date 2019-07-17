package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;


public enum BedSize {

    SINGLE(1), DOUBLE(2), TRIPLE(3), QUAD(4), KING(5);

    private int size;

    public int getSize() {
        return size;
    }


    BedSize(int size) {
        this.size = size;
    }
};


