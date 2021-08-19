package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {AA41A005-7DA8-4A08-AFCD-195807C956F3}
 */
public class TraderImpl extends EntityImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5173074281794500877L;

	/** @modelguid {36B5C4CD-02F5-45CD-BF48-C01A1F7B6519} */
	private String traderCode;
	
	/** @modelguid {BEBAD3C3-620A-4320-8CC4-EC3FF191B529} */
	private SourceSystemImpl sourceSystem;

	/** @modelguid {3CF35B4B-9DD2-458A-9242-3CDE210FD326} */
	private EmployeeImpl employee;
	
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * Returns the id.
	 * @return String
	 * @modelguid {7F8D3D02-764F-4B5F-BEA5-379FBA1F2815}
	 */
	public String getTraderCode() {
		return traderCode;
	}

	/**
	 * Returns the source.
	 * @return SourceSystemImpl
	 * @modelguid {9E245A89-2FD9-48FB-A64E-2CB85E76FD5B}
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Sets the id.
	 * @param id The id to set
	 * @modelguid {46A8DF8C-C02B-420B-B625-09DF183A9B18}
	 */
	public void setTraderCode(String traderId) {
		this.traderCode = traderId;
	}

	/**
	 * Sets the source.
	 * @param source The source to set
	 * @modelguid {37970E2E-0070-4D52-8EF3-3AC61815A5C0}
	 */
	public void setSourceSystem(SourceSystemImpl source) {
		this.sourceSystem = source;
	}

	/**
	 * Returns the employee.
	 * @return EmployeeImpl
	 * @modelguid {3B6D76C0-F896-41D1-988F-C5770232D728}
	 */
	public EmployeeImpl getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee.
	 * @param employee The employee to set
	 * @modelguid {493100E1-69D9-4576-9804-636E71A17D61}
	 */
	public void setEmployee(EmployeeImpl employee) {
		this.employee = employee;
	}

}

