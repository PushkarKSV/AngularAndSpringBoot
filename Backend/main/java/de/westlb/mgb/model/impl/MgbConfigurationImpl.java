package de.westlb.mgb.model.impl;

import java.io.Serializable;

import de.westlb.mgb.model.definition.MgbConfigurationDef;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbConfigurationImpl extends EntityImpl implements MgbConfigurationDef {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2177441726022370299L;
	private MgbConfigurationIdImpl mgbConfigurationId;
	private String value;
	private boolean hidden;

	@SuppressWarnings("unused")
    private MgbConfigurationImpl() {
	}

    public MgbConfigurationImpl(MgbConfigurationIdImpl impl) {
        mgbConfigurationId = impl;
    }

	@Override
    public Serializable getId() {
		return getMgbConfigurationId();
	}

	/**
	 * Returns the value.
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * @param value The value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/** 
	 * Print a description of the instrument.
	 * Used for dual control jobs list.
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return getMgbConfigurationId() != null ? getMgbConfigurationId().getKey() : super.toString();
	}

    /**
     * @return
     */
    public MgbConfigurationIdImpl getMgbConfigurationId() {
        return mgbConfigurationId;
    }

    /**
     * @param impl
     */
    public void setMgbConfigurationId(MgbConfigurationIdImpl impl) {
        mgbConfigurationId = impl;
    }
    
	/**
	 * Returns the stateCode.
	 * @return String
	 */
	public String getKey() {
		return mgbConfigurationId.getKey();
	}

	@Override
    public MandantImpl getMandant() {
		return mgbConfigurationId.getMandant();
	}

}
