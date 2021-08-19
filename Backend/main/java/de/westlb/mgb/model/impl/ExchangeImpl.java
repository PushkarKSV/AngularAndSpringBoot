package de.westlb.mgb.model.impl;

import java.util.Collection;
import java.util.Set;

/** @modelguid {06171FB3-BECB-4682-AE58-DFCE79BEFB2A} */
public class ExchangeImpl extends EntityImpl {
	
	/**
     * 
     */
    private static final long serialVersionUID = -2097896569972730572L;

    /** @modelguid {80A3826B-F2FA-454D-A9E3-905CBA751074} */
	private String bloombergId;

	/** @modelguid {A2E9DD61-25F3-4C26-8042-AF4B4EB496AC} */
	private String reutersId;

	/** @modelguid {EACB7982-FE33-45D8-98E1-2283D3E2E470} */
	private Set<ExchangeMappingEntryImpl> mappings;

	/** @modelguid {ED19A3AA-68F7-4AE9-B339-279107FB85BC} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}	

	/**
	 * Returns the bloombergId.
	 * @return String
	 * @modelguid {CD19E443-4E66-490D-8DCF-4DD52AA6D653}
	 */
	public String getBloombergId() {
		return bloombergId;
	}

	/**
	 * Returns tC
	 * @return java.util.Set
	 * @modelguid {D5DE6725-CA4C-4A10-87AA-8DEA2EFED40C}
	 */
	public Collection<ExchangeMappingEntryImpl> getMappings() {
		return mappings;
	}

	/**
	 * Returns the reutersId.
	 * @return String
	 * @modelguid {CB230548-6608-4858-8AEA-8C4BB4882CA5}
	 */
	public String getReutersId() {
		return reutersId;
	}

	/**
	 * Sets the bloombergId.
	 * @param bloombergId The bloombergId to set
	 * @modelguid {147DB2DC-CACE-4278-9C8A-1182A791ADC2}
	 */
	public void setBloombergId(String bloombergId) {
		this.bloombergId = bloombergId;
	}

	/**
	 * Sets the reutersId.
	 * @param reutersId The reutersId to set
	 * @modelguid {AC333A58-DFC7-4913-AC19-CF0EFC2D0BED}
	 */
	public void setReutersId(String reutersId) {
		this.reutersId = reutersId;
	}

	/**
	 * Sets the mappings.
	 * @param mappings The mappings to set
	 * @modelguid {9A01C15A-105F-424D-A76E-48DEF818A5D8}
	 */
	public void setMappings(Set<ExchangeMappingEntryImpl> mappings) {
		this.mappings = mappings;
	}
	
	/** 
	 * Print a description of the instrument.
	 * Used for dual control jobs list.
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return bloombergId != null ? bloombergId : super.toString();
	}

}

