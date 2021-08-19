package de.westlb.mgb.model.impl;

import java.io.Serializable;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 * @modelguid {6799D54A-92B2-4CCD-9935-B46C2FE2FD64}
 */
public class StateIdImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 334013485674522168L;

	/**
	 * @modelguid {CF1B8B15-D0B3-40AE-9B55-6407EDEADFC9}
	 */
	public StateIdImpl() {
	}

	/** @modelguid {331B73D1-F48E-481A-9379-5009784575B0} */
	public StateIdImpl(MandantImpl mandant, String stateCode) {
		this.mandant = mandant;
		this.stateCode = stateCode;
	}
		
	/** @modelguid {D035C75D-AF96-46FF-9639-8712860B8C76} */
	private String stateCode;
	
	/** @modelguid {B54766B1-CE31-4806-B1E9-C5B96059CB04} */
	private MandantImpl mandant;

	/**
	 * Returns the mandant.
	 * @return String
	 * @modelguid {437F61AF-EE19-4842-A909-BCED55F432A0}
	 */
	public MandantImpl getMandant() {
		return mandant;
	}

	/**
	 * Returns the ntId.
	 * @return String
	 * @modelguid {7BEFC1BC-CB00-486E-AB80-762DBBFA242D}
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * Sets the mandant.
	 * @param mandant The mandant to set
	 * @modelguid {8676BD6A-6E46-4368-BB9C-32643BC9E970}
	 */
	public void setMandant(MandantImpl mandantCode) {
		this.mandant = mandantCode;
	}

	/**
	 * Sets the ntId.
	 * @param ntId The ntId to set
	 * @modelguid {1D983353-B51A-4DC0-B873-8A89A0CFDB77}
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/** @modelguid {CE4DF18A-CC32-4D7F-8DCA-29BB3D8B12A5} */
	@Override
    public boolean equals(Object anObject) {
		if (anObject == this) {
			return true;
		} 
        if (!(anObject instanceof StateIdImpl))
            return false;
		return toString().equals( ((StateIdImpl)anObject).toString() );
	}
	
	/** @modelguid {5D669545-C19D-48CF-A1AA-5784F55CF730} */
	@Override
    public int hashCode() {
		if (stateCode == null || mandant == null) {
			return 0;
		}
        return toString().hashCode();
	}

	/** @modelguid {2A446CF3-00D8-45BD-868A-BBBF6B1B34A3} */
	@Override
    public String toString() {
		if (stateCode == null || mandant == null) {
			return "";
		}
        return stateCode + " " + mandant.getCode();
	}

}
