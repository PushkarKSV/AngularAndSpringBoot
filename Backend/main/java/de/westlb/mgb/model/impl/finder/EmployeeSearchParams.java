package de.westlb.mgb.model.impl.finder;

import de.westlb.mgb.model.impl.MandantImpl;

/**
 * 
 * @modelguid {A855CC2B-944B-4D56-BE7B-1F6F3B252510}
 */
public class EmployeeSearchParams extends SearchParams {

	/** @modelguid {AC22C5C2-04FF-4C0B-9B17-F4031DC94407} */
	private String firstName;

	/** @modelguid {14D3830C-C0EC-42B3-AA7C-34483B1A3C27} */
	private String lastName;

	/** @modelguid {3430559F-CBF5-44C8-94F3-A8FBA8F82B24} */
	private String traderCode;

	/** @modelguid {D5619190-A277-4088-AC98-A352D4E2AEC6} */
	private String ntId;

	private boolean hasRoles = true;

   private String role;

	/**
	 * Constructor for EmployeeSearchParams.
	 */
	public EmployeeSearchParams() {

	}

	/**
	 * Constructor for EmployeeSearchParams.
	 * @param mandant
	 */
	public EmployeeSearchParams(
		MandantImpl mandant,
		String firstName,
		String lastName,
		String ntId,
		String traderId) {
		super(mandant);
		this.firstName = firstName;
		this.lastName = lastName;
		this.ntId = ntId;
		this.traderCode = traderId;
	}

	/**
	 * Returns the firstName.
	 * @return String
	 * @modelguid {738E7532-4CE6-4E7B-BE4C-EB7CCD7E1E8E}
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the lastName.
	 * @return String
	 * @modelguid {5FE32220-1B55-4316-96EA-C972092AB67C}
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the ntId.
	 * @return String
	 * @modelguid {35C66111-036C-4400-8820-20919BC78DDA}
	 */
	public String getNtId() {
		return ntId;
	}

	/**
	 * Returns the traderId.
	 * @return String
	 * @modelguid {B5D5E978-BE88-48F2-83D9-279E730CF3E6}
	 */
	public String getTraderCode() {
		return traderCode;
	}

	/**
	 * Sets the firstName.
	 * @param firstName The firstName to set
	 * @modelguid {5667CA2B-2072-40B7-9DC3-F97364FC6B42}
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the lastName.
	 * @param lastName The lastName to set
	 * @modelguid {D2186DF2-AFD8-4F34-A0E9-5217DB5D02E0}
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the ntId.
	 * @param ntId The ntId to set
	 * @modelguid {A0E9EEBE-E377-439E-9C1C-6C5B96330263}
	 */
	public void setNtId(String ntId) {
		this.ntId = ntId;
	}

	/**
	 * Sets the traderId.
	 * @param traderId The traderId to set
	 * @modelguid {AAB8BD1F-4D33-4C75-A8F4-B8985DBCEB7F}
	 */
	public void setTraderCode(String traderId) {
		this.traderCode = traderId;
	}

	public boolean hasRoles() {
		return hasRoles;
	}
	public void setHasRoles(boolean hasRoles) {
		this.hasRoles = hasRoles;
	}

    
    public String getRole() {
        return role;
    }

    
    public void setRole(String role) {
        this.role = role;
    }
	
}
