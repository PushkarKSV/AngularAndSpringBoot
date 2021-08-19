package de.westlb.mgb.model.impl;

import java.util.Calendar; 

import de.westlb.mgb.model.HistoricPrice;
import de.westlb.mgb.model.impl.visitor.PriceVisitor;

/**
 * 
 * @modelguid {3235B77E-FA2B-4E8D-A96B-F030ED8AEBE8}
 */
public class HistoricPriceImpl extends PriceImpl implements HistoricPrice {

	/**
     * 
     */
    private static final long serialVersionUID = -7701917919615073289L;
    /** @modelguid {B44EF690-42A4-4123-93B6-809473EBDC99} */
	private double price = 0.0d;

	/** @modelguid {7D33A51A-4FB9-4268-93A5-93872E5F7FC7} */
	public HistoricPriceImpl() {
	}

	@Override
    public void accept(PriceVisitor visitor) {
	    visitor.visit(this);
	}

	/** @modelguid {9C218A29-CC2E-4162-B465-3307795E105C} */
	public HistoricPriceImpl(Calendar priceDate, double price) {
		this.price = price;
		this.priceDate = priceDate;
	}

	/**
	 * Returns the price.
	 * @return BigDecimal
	 * @modelguid {DB29E68D-6588-4A02-8317-C3D976F70D83}
	 */
	@Override
    public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 * @modelguid {570A14F3-98FE-47B2-B97F-D2CA83DC7AE3}
	 */
	@Override
    public void setPrice(double price) {
		this.price = price;
	}


	/** @modelguid {4F2A6506-3DC3-4FCB-BBD5-867E21827FA5} */
	@Override
    public String toString() {
		if (priceDate != null) {
			return dateTimeFormat.format(priceDate.getTime()) + " " + price;
		}
        return "";
	}

}

