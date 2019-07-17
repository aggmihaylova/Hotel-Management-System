package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * This is an abstract class
 * which has one private member - inventoryNumber
 * <p>
 * getters, setters and abstract method prepare()
 */

abstract public class AbstractCommodity {

    protected int inventoryNumber;


    public AbstractCommodity(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Booking))
            return false;

        if (this == obj)
            return true;

        AbstractCommodity abstractCommodity = (AbstractCommodity) obj;

        return this.inventoryNumber == abstractCommodity.inventoryNumber;

    }

    @Override
    public int hashCode() {
        int hash = 3;

        hash *= inventoryNumber;

        return hash;

    }

    /**
     * The room is being prepared !
     */

    abstract public void prepare();


}
