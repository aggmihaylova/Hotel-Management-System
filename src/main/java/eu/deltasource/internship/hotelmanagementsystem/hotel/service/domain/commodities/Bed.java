package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Bed is part of the set of commodities
 */
public class Bed extends AbstractCommodity {

    private BedType bedType;

    /**
     * @param bedType bed type
     */
    public Bed(BedType bedType) {
        super();
        this.bedType = bedType;
    }

    public BedType getBedType() {
        return bedType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepare() {
        System.out.println("Bedsheets are being replaced");
    }
}