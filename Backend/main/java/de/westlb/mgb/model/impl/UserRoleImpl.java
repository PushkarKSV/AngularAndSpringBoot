package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {145F7080-9232-4CBC-BC44-8E26C38D3565}
 */
public class UserRoleImpl extends EntityImpl {

    public static final String LOCATION_DELIMITER = ",";

	private static final long serialVersionUID = -1374287309253551030L;

	/** @modelguid {F2EA159B-A47D-40DD-8747-89AA0D99F866} */
	private RoleImpl type;

	private String location;
	/** @modelguid {94D223CC-87CF-41BB-9A49-36C9FE2D332D} */
	private EmployeeImpl employee;

	/** @modelguid {2DAB31AA-97D2-488D-B006-E233B407F842} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	public boolean isRole(String role) {
	    return role != null && type != null && role.equals(type.getCode());
	}
	
	/**
	 * Returns the employee.
	 * @return EmployeeImpl
	 * @modelguid {3290F2CA-A2BA-4CB3-B32E-E1DB54959B15}
	 */
	public EmployeeImpl getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 * @param employee The employee to set
	 * @modelguid {D954B902-814C-4662-A186-B836D3959EA9}
	 */
	public void setEmployee(EmployeeImpl employee) {
		this.employee = employee;
	}


	
    public String getLocation() {
        return location;
    }

    
    public void setLocation(String location) {
        this.location = location;
    }

    /**
	 * Returns the type.
	 * @return RoleImpl
	 */
	public RoleImpl getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(RoleImpl type) {
		this.type = type;
	}

}

