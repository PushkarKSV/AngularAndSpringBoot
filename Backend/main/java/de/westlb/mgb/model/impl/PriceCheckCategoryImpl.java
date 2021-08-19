package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {34F592BB-7C37-4706-8BFB-0E651A42B4DC}
 */
public class PriceCheckCategoryImpl extends EntityImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3920864041199943810L;
	public final static String DEFAULT_PRICE_CHECK_CATEGORY = "default";
	/** @modelguid {FC63EB3F-5A87-4327-9DB1-2A8821A1022B} */
	private String name;
	/** @modelguid {7803127B-AFF5-4B44-925D-8466D4D68EB3} */
	private double priceTolerancePercent;
	/** @modelguid {42E53280-8591-4BF6-ABD4-B434797736E9} */
	private double priceToleranceAbsolute;
	/** @modelguid {383F1C7B-0D4C-44E9-9B0E-AA9CE40B6580} */
	private int timeToleranceMinutes;
    private int samplePercentage;
	private ManualSampleStateImpl sampleState;
	private boolean enabled;

	
	public int getSamplePercentage() {
		return samplePercentage;
	}
	public void setSamplePercentage(int samplePercentage) {
		this.samplePercentage = samplePercentage;
	}
	public ManualSampleStateImpl getSampleState() {
		return sampleState;
	}
	public void setSampleState(ManualSampleStateImpl sampleState) {
		this.sampleState = sampleState;
	}
	/** @modelguid {1D799C56-5538-4D9B-9FFD-59EB1021D3DC} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * Returns the name.
	 * @return String
	 * @modelguid {3D03F888-7ED6-4BBB-A024-F2C5A5F7E70C}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the priceToleranceMinusPercent.
	 * @return double
	 * @modelguid {3CCBDC76-8977-441B-B7BF-8AA164E1BA49}
	 */
	public double getPriceToleranceAbsolute() {
		return priceToleranceAbsolute;
	}

	/**
	 * Returns the priceTolerancePlusPercent.
	 * @return double
	 * @modelguid {98FD9A90-2052-4CDE-B79A-69A3276D091F}
	 */
	public double getPriceTolerancePercent() {
		return priceTolerancePercent;
	}

	/**
	 * Returns the timeToleranceMinutes.
	 * @return long
	 * @modelguid {A3DA8A33-797C-4E45-A981-13C68DAE4587}
	 */
	public int getTimeToleranceMinutes() {
		return timeToleranceMinutes;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 * @modelguid {E7F14945-7296-4572-A0E1-59776F59189A}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the priceToleranceMinusPercent.
	 * @param priceToleranceMinusPercent The priceToleranceMinusPercent to set
	 * @modelguid {334EEC28-3F2B-4436-8797-108A24BD09C9}
	 */
	public void setPriceToleranceAbsolute(double priceToleranceMinusPercent) {
		this.priceToleranceAbsolute = priceToleranceMinusPercent;
	}

	/**
	 * Sets the priceTolerancePlusPercent.
	 * @param priceTolerancePlusPercent The priceTolerancePlusPercent to set
	 * @modelguid {1659E9F7-2B98-4787-AA72-C900DFD2C6C8}
	 */
	public void setPriceTolerancePercent(double priceTolerancePlusPercent) {
		this.priceTolerancePercent = priceTolerancePlusPercent;
	}

	/**
	 * Sets the timeToleranceMinutes.
	 * @param timeToleranceMinutes The timeToleranceMinutes to set
	 * @modelguid {9D89AFE4-FEC4-4F90-8F4E-0387CBE0D51D}
	 */
	public void setTimeToleranceMinutes(int timeToleranceMinutes) {
		this.timeToleranceMinutes = timeToleranceMinutes;
	}

	/** 
	 * Print a description of the instrument.
	 * Used for dual control jobs list.
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return name != null ? name : super.toString();
	}
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean getEnabled() {
        return enabled;
    }
}

