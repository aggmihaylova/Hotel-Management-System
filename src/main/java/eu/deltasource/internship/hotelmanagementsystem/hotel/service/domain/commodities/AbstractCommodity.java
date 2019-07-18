package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * This is an abstract class
 * which has one private member - inventoryNumber
 * getters, setters, abstract method prepare() and overridden equals() and hashcode()
 */

abstract public class AbstractCommodity {

    private static int inventoryNum;
    protected int inventoryNumber;

    /**
     * Default constructor
     * assigns value to the inventory number
     */

    public AbstractCommodity() {
        inventoryNumber = ++inventoryNum;
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
