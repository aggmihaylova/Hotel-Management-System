package commodities;

public class Bed extends AbstractCommodity {

    private double length;
    private double width;
    private int numberOfPersonas;

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

    public void prepare() {
        System.out.println("Bedsheets are being replaced");
    }
}
