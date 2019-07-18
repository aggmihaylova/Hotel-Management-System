package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Class bed has 1 private member and one inherited from the abstract class AbstractCommodity
 * methods - getters,setters,constructor and implemented method prepare()
 */


public class Bed extends AbstractCommodity {

    private BedSize numberOfPersonas;


    /**
     * Parametrized constructor
     *
     * @param numberOfPersonas bed capacity
     */

    public Bed(BedSize numberOfPersonas) {
        super();
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
