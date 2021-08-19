package de.westlb.mgb.model.impl;

import java.util.Calendar; 

import de.westlb.mgb.model.IntervalPrice;
import de.westlb.mgb.model.impl.visitor.PriceVisitor;

/**
 * 
 * @modelguid {1352E486-6319-4A1A-9E6D-8FFBE98E572B}
 */
public class IntervalPriceImpl extends PriceImpl implements IntervalPrice {

	/**
     * 
     */
    private static final long serialVersionUID = 3935655877062406685L;
    /** @modelguid {9E0D58A7-2427-445B-9E91-81596AF087A3} */
	private double priceMin = 0.0d;
	/** @modelguid {1048B267-2EDA-40CE-99DF-93DA428390F0} */
	private double priceMax = 0.0d;

	/** @modelguid {817F44DD-A158-4605-8725-83F61C475C42} */
	public IntervalPriceImpl() {
	}

	@Override
    public void accept(PriceVisitor visitor) {
	    visitor.visit(this);
	}
	
	/** @modelguid {723E258F-8D38-4BDD-9A3A-0F95E90CE2FB} */
	public IntervalPriceImpl(Calendar priceDate, double priceMin, double priceMax) {
		if (priceMin > priceMax) {
			this.priceMin = priceMax;
			this.priceMax = priceMin;
		} else {
			this.priceMin = priceMin;
			this.priceMax = priceMax;
		}
		this.priceDate = priceDate;
	}


	/**
	 * Returns the priceMax.
	 * @return BigDecimal
	 * @modelguid {30586AD8-0A31-4053-A9FC-5149A65D5C9C}
	 */
	@Override
    public double getPriceMax() {
		return priceMax;
	}

	/**
	 * Returns the priceMin.
	 * @return BigDecimal
	 * @modelguid {7129E4A6-7ADE-48BE-8F1F-A6AF29894AC9}
	 */
	@Override
    public double getPriceMin() {
		return priceMin;
	}


	/**
	 * Sets the priceMax.
	 * @param priceMax The priceMax to set
	 * @modelguid {A89F2165-704F-47E0-9424-1CF72A60F354}
	 */
	@Override
    public void setPriceMax(double priceMax) {
		this.priceMax = priceMax;
	}

	/**
	 * Sets the priceMin.
	 * @param priceMin The priceMin to set
	 * @modelguid {4A971566-48C4-477A-AA18-92C3AB97C910}
	 */
	@Override
    public void setPriceMin(double priceMin) {
		this.priceMin = priceMin;
	}

	/** @modelguid {0BADA87E-3193-4F90-A01E-5C6BC5DB3693} */
	@Override
    public String toString() {
		if (priceDate != null) {
			return dateTimeFormat.format(priceDate.getTime()) + " " + priceMin + " " + priceMax;
		}
        return "";
	}

}
