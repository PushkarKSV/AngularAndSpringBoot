package de.westlb.mgb.model;


/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface IntervalPrice {

	public double getPriceMax();

	/**
	 * Returns the lowPrice.
	 * @return BigDecimal
	 */
	public double getPriceMin();

	/**
	 * Sets the highPrice.
	 * @param highPrice The highPrice to set
	 */
	public void setPriceMax(double priceMax);

	/**
	 * Sets the lowPrice.
	 * @param lowPrice The lowPrice to set
	 */
	public void setPriceMin(double priceMin);

}
