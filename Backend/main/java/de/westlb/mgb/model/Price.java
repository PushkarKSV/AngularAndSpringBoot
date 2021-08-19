package de.westlb.mgb.model;

import java.util.Calendar;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Price {

	public String getSource() ;


	/**
	 * Returns the priceDate.
	 * @return Date
	 */
	public Calendar getPriceDate();

	/**
	 * Sets the priceDate.
	 * @param priceDate The priceDate to set
	 */
	public void setPriceDate(Calendar priceDate);

}
