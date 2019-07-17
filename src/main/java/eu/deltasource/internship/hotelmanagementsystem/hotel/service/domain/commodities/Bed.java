package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Class bed has 3 private members and one inherited from the abstract class AbstractCommodity
 * <p>
 * methods - getters,setters and implementation of the base class method prepare()
 */


public class Bed extends AbstractCommodity {

    private BedSize numberOfPersonas;

    public Bed(int inventoryNumber, BedSize numberOfPersonas) {
        super(inventoryNumber);
        this.numberOfPersonas = numberOfPersonas;
    }

    public int getNumberOfPersonas() {
        return numberOfPersonas.getSize();
    }

    public void setNumberOfPersonas(BedSize numberOfPersonas) {
        this.numberOfPersonas = numberOfPersonas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() {
        System.out.println("Bedsheets are being replaced");
    }
}
