package de.westlb.mgb.model.impl;

import java.text.Format; 
import java.util.Calendar;

import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.Price;
import de.westlb.mgb.model.definition.MarketDataSourceDef;
import de.westlb.mgb.model.impl.visitor.PriceVisitor;

/**
 * 
 * @modelguid {217E1EE4-5E50-40CD-9B07-7D256625BF25}
 */
public abstract class PriceImpl extends EntityImpl implements Price, MarketDataSourceDef  {

    private static final long serialVersionUID = -3759751794355830654L;

    /** @modelguid {52542AEA-EEAD-414C-98A5-D9C5B1AC083E} */
	private String source = MarketDataSourceDef.BLOOMBERG;

	protected final Format dateTimeFormat = new DateFormat(DateFormat.DATETIME_FORMAT_LONG);

	/** @modelguid {0FEC48AC-15B9-40D6-B03C-343E6EE51916} */
	protected Calendar priceDate = null;

   public void accept(PriceVisitor visitor) {};

	/**
	 * Returns the source.
	 * @return String
	 * @modelguid {2C452A22-6126-4F3D-A260-377221E744F4}
	 */
	@Override
    public String getSource() {
		return source;
	}

	/**
	 * Sets the source.
	 * @param source The source to set
	 * @modelguid {ACA39FE4-39B0-4868-BD15-9D09AA6F3A84}
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Returns the priceDate.
	 * @return Date
	 * @modelguid {92AE77E1-82CB-4792-8835-E556FCB2B86A}
	 */
	@Override
    public Calendar getPriceDate() {
		return priceDate;
	}

	/**
	 * Sets the priceDate.
	 * @param priceDate The priceDate to set
	 * @modelguid {D4132B79-23BB-4D21-B977-1F8368E0E3C1}
	 */
	@Override
    public void setPriceDate(Calendar priceDate) {
		this.priceDate = priceDate;
	}


	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 * @modelguid {90087107-EFBE-4FBF-ABA3-BADDBB6273AC}
	 */
	@Override
	public int compareTo(EntityImpl aPrice) {
		return this.priceDate.getTime().compareTo(((PriceImpl)aPrice).priceDate.getTime());
	}
}

