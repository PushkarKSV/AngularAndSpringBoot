package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {216EB04F-2D7A-4AF9-B766-9BA81E0CBC88}
 */
public class ExchangeMappingEntryPrioImpl extends EntityImpl {
	/**
     * 
     */
    private static final long serialVersionUID = 7227050483113823934L;

    /** @modelguid {BE47FC2C-CADA-49A1-B7DA-7DC0EA27E3D8} */
	private boolean sourceSystemNotNull;

	/** @modelguid {2F68D685-BC8A-4B7A-B3A3-F66E8E56756B} */
	private boolean isinNotNull;
	
	/** @modelguid {44551AF8-2381-4CF0-BC72-520F0720A6E0} */
	private boolean isinCountryPrefixNotNull;

	/** @modelguid {6B853242-C416-4A42-B983-D48580B7A297} */
	private boolean sourceSystemExchangeIdNotNull;	
	
	/** @modelguid {1F7F7EF4-02D5-4E90-AF66-FF416ED0A320} */
	private boolean currencyNotNull;
	
	/** @modelguid {B41B1D5E-8D59-418B-83A4-659510603176} */
	private int prio;

	private String prioName;

	@Override
    public String toString() {
	    return getClass().getName() + " [" + getPrioName() + "="+getPrio()+"]";
	}

	/**
	 * Returns the currencyNotNull.
	 * @return boolean
	 * @modelguid {14DE8C5E-10A7-4A54-92A6-25AD06CAD8EA}
	 */
	public boolean isCurrencyNotNull() {
		return currencyNotNull;
	}

	/**
	 * Returns the isinNotNull.
	 * @return boolean
	 * @modelguid {D81C18C2-4A22-4B2E-BF6C-66025C67BEE4}
	 */
	public boolean isIsinNotNull() {
		return isinNotNull;
	}

	/**
	 * Returns the prio.
	 * @return int
	 * @modelguid {90B869F7-D112-4CAE-B94B-E957ECA4BD7D}
	 */
	public int getPrio() {
		return prio;
	}

	/**
	 * Returns the sourceSystemExchangeIdNotNull.
	 * @return boolean
	 * @modelguid {E37C747C-4EAE-45E7-BB7E-8EAE69D17E48}
	 */
	public boolean isSourceSystemExchangeIdNotNull() {
		return sourceSystemExchangeIdNotNull;
	}

	/**
	 * Returns the sourceSystemNotNull.
	 * @return boolean
	 * @modelguid {3BBE524E-16F0-4F78-BEF6-75BB9565EA26}
	 */
	public boolean isSourceSystemNotNull() {
		return sourceSystemNotNull;
	}

	/**
	 * Sets the currencyNotNull.
	 * @param currencyNotNull The currencyNotNull to set
	 * @modelguid {4BC05424-105D-4B2F-87C1-638715CB68ED}
	 */
	public void setCurrencyNotNull(boolean currencyNotNull) {
		this.currencyNotNull = currencyNotNull;
	}

	/**
	 * Sets the isinNotNull.
	 * @param isinNotNull The isinNotNull to set
	 * @modelguid {FDC09936-0116-4A74-8A79-E3A424116013}
	 */
	public void setIsinNotNull(boolean isinNotNull) {
		this.isinNotNull = isinNotNull;
	}

	/**
	 * Sets the prio.
	 * @param prio The prio to set
	 * @modelguid {6F67CAA5-6137-4AE6-8A36-65F4D4DFBCDC}
	 */
	public void setPrio(int prio) {
		this.prio = prio;
	}

	/**
	 * Sets the sourceSystemExchangeIdNotNull.
	 * @param sourceSystemExchangeIdNotNull The sourceSystemExchangeIdNotNull to set
	 * @modelguid {90341666-6D8C-4988-8207-3401A5937E18}
	 */
	public void setSourceSystemExchangeIdNotNull(boolean sourceSystemExchangeIdNotNull) {
		this.sourceSystemExchangeIdNotNull = sourceSystemExchangeIdNotNull;
	}

	/**
	 * Sets the sourceSystemNotNull.
	 * @param sourceSystemNotNull The sourceSystemNotNull to set
	 * @modelguid {77A3FE35-DF32-423B-B5E5-825C835CA958}
	 */
	public void setSourceSystemNotNull(boolean sourceSystemNotNull) {
		this.sourceSystemNotNull = sourceSystemNotNull;
	}

	/**
	 * Returns the isinCountryPreFixNotNull.
	 * @return boolean
	 * @modelguid {54692E0A-6B3A-4F00-8DDF-1BA03C2E8FA6}
	 */
	public boolean isIsinCountryPrefixNotNull() {
		return isinCountryPrefixNotNull;
	}

	/**
	 * Sets the isinCountryPreFixNotNull.
	 * @param isinCountryPreFixNotNull The isinCountryPreFixNotNull to set
	 * @modelguid {FF0D8C9D-29E8-433E-9932-879171807ABE}
	 */
	public void setIsinCountryPrefixNotNull(boolean isinCountryPreFixNotNull) {
		this.isinCountryPrefixNotNull = isinCountryPreFixNotNull;
	}

	/**
	 * Returns the prioName.
	 * @return String
	 */
	public String getPrioName() {
		return prioName;
	}

	/**
	 * Sets the prioName.
	 * @param prioName The prioName to set
	 */
	public void setPrioName(String prioName) {
		this.prioName = prioName;
	}

}

