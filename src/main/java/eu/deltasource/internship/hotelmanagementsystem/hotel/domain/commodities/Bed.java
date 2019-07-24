package eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities;

/**
 * Bed is part of the set of commodities
 */
public class Bed extends AbstractCommodity {

    private BedType bedType;

    /**
     * This ia a constructor
     *
     * @param bedType bed type
     */
    public Bed(int inventoryNumber, BedType bedType) {
        super(inventoryNumber);
        this.bedType = bedType;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() {
        System.out.println("Bedsheets are being replaced");
    }
}