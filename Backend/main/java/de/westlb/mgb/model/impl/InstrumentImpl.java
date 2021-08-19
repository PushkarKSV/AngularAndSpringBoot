package de.westlb.mgb.model.impl;

import java.util.Set;

/**
 * 
 * @modelguid {718204DC-BB70-4EED-A2B4-7A2E7DCC8C8F}
 */
public class InstrumentImpl extends EntityImpl {

	/**
     * 
     */
    private static final long serialVersionUID = -1537274835401117945L;

    /** @modelguid {21A37159-7EBE-4625-8FEF-EEE3893C4D0E} */
	private String instrument;

	private String instrumentName;

	private String bloombergRequestSources;
	
	private boolean enabled = true;
	
	private Set<DualControlJobImpl> dualControlJobs;

	/** @modelguid {7E1D8F53-DB14-44BD-A549-81592D037652} */
	public InstrumentImpl() {
	}

	/** @modelguid {1AB34D98-E825-4DC4-8C95-53F1A16F9982} */
	public InstrumentImpl(
		String instrument) {
		this.instrument = instrument;
	}

	/** @modelguid {E8FF286A-E6F2-4E99-8E73-0E34C464132E} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * Returns the isin.
	 * @return String
	 * @modelguid {45C8DCE0-B6A0-4736-A6CC-E29D84C10794}
	 */
	public String getInstrument() {
		return instrument;
	}

	/**
	 * Sets the instrument.
	 * @param instrument The instrument to set
	 * @modelguid {D7753C6C-9A89-42D1-A112-2A0AF81864F6}
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	/**
	 * Returns the instrumentName.
	 * @return String
	 */
	public String getInstrumentName() {
		return instrumentName;
	}

	/**
	 * Sets the instrumentName.
	 * @param instrumentName The instrumentName to set
	 */
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

    /** 
     * Print a description of the instrument.
     * Used for dual control jobs list.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	if (instrumentName != null) {
    		return instrumentName;
    	} else if (instrument != null) {
    		return instrument;
    	} else {
			return super.toString();
    	}
    }

    /**
     * @return
     */
    public Set<DualControlJobImpl> getDualControlJobs() {
        return dualControlJobs;
    }

    /**
     * @param set
     */
    public void setDualControlJobs(Set<DualControlJobImpl> set) {
        dualControlJobs = set;
    }

	public String getBloombergRequestSources() {
		return bloombergRequestSources;
	}
	public void setBloombergRequestSources(String bloombergRequestSources) {
		this.bloombergRequestSources = bloombergRequestSources;
	}

	public String[] getBloombergRequestSourceArray() {
	    if (bloombergRequestSources != null) {
	        return bloombergRequestSources.split(",");
	    }
        return null;
	}

	public void setBloombergRequestSourceArray(String[] bloombergRequestSourceArray) {
		if ( bloombergRequestSourceArray.length > 0) {
			this.bloombergRequestSources = bloombergRequestSourceArray[0]; 
		}
		for (int i = 1; i < bloombergRequestSourceArray.length; i++) {
			this.bloombergRequestSources = this.bloombergRequestSources + "," + bloombergRequestSourceArray[i]; 
		}
	}

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }
}
