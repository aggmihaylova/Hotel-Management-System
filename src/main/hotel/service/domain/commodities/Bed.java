package main.hotel.service.domain.commodities;

/**
 * Class bed has 3 private members and one inherited from the abstract class AbstractCommodity
 * <p>
 * methods - getters,setters and implementation of the base class method prepare()
 */


public class Bed extends AbstractCommodity {

    private double length;
    private double width;
    private int numberOfPersonas;

    /**
     * parameterized constructor
     *
     * @param inventoryNumber  - bed's inventory number
     * @param length           - bed's length
     * @param width            - bed's width
     * @param numberOfPersonas - bed's capacity
     */
    public Bed(int inventoryNumber, double length, double width, int numberOfPersonas) {
        super(inventoryNumber);
        this.length = length;
        this.width = width;
        this.numberOfPersonas = numberOfPersonas;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public int getNumberOfPersonas() {
        return numberOfPersonas;
    }


    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setNumberOfPersonna(int numberOfPersonas) {
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
