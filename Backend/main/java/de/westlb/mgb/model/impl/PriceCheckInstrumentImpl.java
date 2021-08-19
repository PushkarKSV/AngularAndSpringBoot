/*
 * Created on Jul 9, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PriceCheckInstrumentImpl extends InstrumentImpl {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8894369784224779718L;

	/** @modelguid {1AB34D98-E825-4DC4-8C95-53F1A16F9982} */
	public PriceCheckInstrumentImpl(
		String instrument,
		PriceCheckCategoryImpl priceCheckCategory) {
		super(instrument);
		this.priceCheckCategory = priceCheckCategory;
	}

	private PriceCheckCategoryImpl priceCheckCategory;

	/**
	 * Returns the priceCheckCategory.
	 * @return PriceCheckCategoryImpl
	 * @modelguid {D8477B17-CE9B-4C64-AD09-928E55BADE85}
	 */
	public PriceCheckCategoryImpl getPriceCheckCategory() {
		return priceCheckCategory;
	}

	/**
	 * Sets the priceCheckCategory.
	 * @param priceCheckCategory The priceCheckCategory to set
	 * @modelguid {86F5DE03-8BC2-4078-823D-15146B7FA7BB}
	 */
	public void setPriceCheckCategory(PriceCheckCategoryImpl priceCheckCategory) {
		this.priceCheckCategory = priceCheckCategory;
	}

	/**
	 * 
	 */
	public PriceCheckInstrumentImpl() {
		super();
	}
	/**
	 * @param instrument
	 */
	public PriceCheckInstrumentImpl(String instrument) {
		super(instrument);
	}
}
