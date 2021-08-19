package de.westlb.mgb.model.impl;

import de.westlb.mgb.model.definition.StateCodeDef;

/**
 * 
 * @modelguid {1B3FC21F-2DCA-4087-B213-E05558954280}
 */
public class AutomaticStateImpl extends StateImpl implements StateCodeDef {

    private static final long serialVersionUID = -5154993872497645464L;
    
    /** @modelguid {73601E0F-0471-4032-B4C0-90CC91234B9A} */
	private boolean isManualCheckRequired;
    private int samplePercentage;
	private ManualSampleStateImpl sampleState;
	
	/**
	 * Indicates if the trade has been checked. False if trade is
	 * a storno or has been filtered for some other reason.
	 */
	private boolean isMccChecked;
	
	/** @modelguid {6D8918C9-D97D-4766-9F36-03BC09C06AA6} */
	private String marketDataRequestType;

	@Override
    public String getStateCodeType() {
	    return AUTO;
	}

	/**
	 * Constructor for AutomaticStateImpl.
	 * @modelguid {05404FBF-46A9-4C8B-BF1D-39952CBA1DFD}
	 */
	@SuppressWarnings("unused")
    private AutomaticStateImpl() {
	}

	/** @modelguid {29C49958-F2FC-480A-93EE-EA4FE5F16A59} */
	public AutomaticStateImpl(StateIdImpl stateId) {
		super(stateId);
	}
	
	/**
	 * Returns the isManualCheckRequired.
	 * @return boolean
	 * @modelguid {785099C7-376F-4488-88D5-86039B307D59}
	 */
	public boolean isManualCheckRequired() {
		return isManualCheckRequired;
	}

	/** @modelguid {33E9FFD5-61F9-49CD-A39A-CF78C54EA1A0} */
	public boolean getIsManualCheckRequired() {
		return isManualCheckRequired;
	}

	/**
	 * Returns the marketDataRequestType.
	 * @modelguid {B8774404-ABA9-49D1-AB45-4E1D4988922F}
	 */
	public String getMarketDataRequestType() {
		return marketDataRequestType;
	}

	/**
	 * Sets the isManualCheckRequired.
	 * @param isManualCheckRequired The isManualCheckRequired to set
	 * @modelguid {AB8894FE-A329-4E3E-9D15-7DBC6F63758A}
	 */
	public void setIsManualCheckRequired(boolean isManualCheckRequired) {
		this.isManualCheckRequired = isManualCheckRequired;
	}

	/**
	 * Sets the marketDataRequestType.
	 * @param marketDataRequestType The marketDataRequestType to set
	 * @modelguid {FBA231B0-8EBA-4B4A-9B10-A584FB94B41C}
	 */
	public void setMarketDataRequestType(String marketDataRequestType) {
		this.marketDataRequestType = marketDataRequestType;
	}

    /**
     * Sets the isChecked flag. 
     */
    public boolean getIsMccChecked() {
        return isMccChecked;
    }

	/**
	 * Sets the isChecked flag.
	 */
    public void setIsMccChecked(boolean b) {
		isMccChecked = b;
    }

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
}

