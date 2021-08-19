package de.westlb.mgb.model.impl;

import java.io.Serializable;

import de.westlb.mgb.model.definition.StateCodeTypeDef;

/** @modelguid {AF864A58-AF0E-4106-817B-17F684A02AA4} */
public abstract class StateImpl extends EntityImpl implements StateCodeTypeDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5812877504447609267L;

	public abstract String getStateCodeType();
	
	// The field state code is mapped readonly. It contains
	// the same information as stateId.stateCode, but needed
	// for optimization of hibernate queries.
	private String stateCode;
	
	/** @modelguid {4A50537B-FBA1-44B4-9313-7CDB234C0E59} */
	private StateIdImpl stateId;

	/** @modelguid {D9C0185F-2BC6-467E-8019-325EAA7ECA6D} */
	private String shortDescription;
	
	/** @modelguid {C06AECCC-8AB3-41F5-8902-02651F490766} */
	private String longDescription;

	private String comment;

	/** @modelguid {92ABAEFF-C178-4DBE-A9CC-10F3A36B3AB0} */
	private boolean finalState;

	private boolean enabled = true;

	/** @modelguid {4CCE2425-9691-48CF-9342-5496315BAF16} */
	@Override
    protected StringBuffer getProperties() {
		if ( stateId != null ) {
			return new StringBuffer("stateCode=" + stateId.getStateCode() + ", mandantCode="+ stateId.getMandant().getCode());
		}
        return new StringBuffer("stateId=null");			
	}

	/** @modelguid {042F135A-B791-4595-A097-3BDC1F842545} */
	@Override
    public Serializable getId() {
		return stateId;
	}
	
	/** @modelguid {F1653D36-19F6-4689-9B22-AF2FA5114912} */
	protected StateImpl() {
	}

	/** @modelguid {0752E552-3F59-4839-B05E-785B19F3CEC6} */
	public StateImpl(StateIdImpl stateId) {
		this.stateId = stateId;
		if (stateId != null) {
			this.stateCode = stateId.getStateCode();
		}
	}

	/**
	 * Returns the longDescription.
	 * @return String
	 * @modelguid {4F3EF946-6DD3-45CC-8124-F6820CD1AF35}
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * Returns the shortDescription.
	 * @return String
	 * @modelguid {DAA4CA6B-AB00-4853-A520-FF2736066B38}
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Sets the isFinalState.
	 * @param isFinalState The isFinalState to set
	 * @modelguid {51BF38EF-A3A3-4B15-8A63-8F47A53BABF5}
	 */
	public void setFinalState(boolean aFinalState) {
		finalState = aFinalState;
	}

	/**
	 * Sets the longDescription.
	 * @param longDescription The longDescription to set
	 * @modelguid {02C1735B-F20C-4920-9EC3-577346EF0FDE}
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * Sets the shortDescription.
	 * @param shortDescription The shortDescription to set
	 * @modelguid {5EDA7A59-19FB-4A0D-BDF6-4EC6F242DAE0}
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Returns the stateId.
	 * @return StateIdImpl
	 * @modelguid {900061D6-86F2-466D-97CC-B4CC48E0329D}
	 */
	public StateIdImpl getStateId() {
		return stateId;
	}


	/**
	 * Sets the stateId.
	 * @param stateId The stateId to set
	 * @modelguid {C6CE5EF8-6460-466F-8388-CFAEAB1CA5EC}
	 */
	public void setStateId(StateIdImpl stateId) {
		this.stateId = stateId;
		if (stateId != null) {
			this.stateCode = stateId.getStateCode();
		}
	}

	/**
	 * Returns the finalState.
	 * @return boolean
	 * @modelguid {1B75CE98-A19C-4F5A-B344-FD048A636144}
	 */
	public boolean getFinalState() {
		return finalState;
	}


	/**
	 * Returns the stateCode.
	 * @return String
	 * @modelguid {5D139A5E-9DAA-4B3C-8672-A7382B97E413}
	 */
	public String getStateCode() {
		return stateCode;
	}

	/** @modelguid {26E0CBF2-8645-48FC-AC21-2F9629E41779} */
	@Override
    public MandantImpl getMandant() {
		return stateId.getMandant();
	}


	/**
	 * @param string
	 */
	@SuppressWarnings("unused")
    private void setStateCode(String string) {
		stateCode = string;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}

