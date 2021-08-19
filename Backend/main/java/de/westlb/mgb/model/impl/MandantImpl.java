package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class MandantImpl extends EntityImpl  {

	private static final long serialVersionUID = -7093855309296301377L;

	private String code;

	private String name;

	private String client;

	private String locationOverride;

	private String productClass;
	
	private boolean autoCheck;

	private boolean enabled = true;

	@Override
    public Serializable getId() {
		return code;
	}

	@Override
    protected StringBuffer getProperties() {
		return new StringBuffer("code=" + code);
	}

	private Set<DualControlJobImpl> dualControlJobs;

	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the code.
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 * @param code The code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Returns the client.
	 * @return String
	 */
	public String getClient() {
		return client;
	}

	/**
	 * Sets the client
	 * @param client The client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}


	/**
	 * Returns the LocationOverride.
	 * @return String
	 */
	public String getLocationOverride() {
		return locationOverride;
	}

	/**
	 * Sets the LocationOverride.
	 * @param LocationOverride The LocationOverride to set
	 */
	public void setLocationOverride(String locationOverride) {
		this.locationOverride = locationOverride;
	}

	/**
	 * Returns the product class.
	 * @return String
	 */
	public String getProductClass() {
		return productClass;
	}

	/**
	 * Sets the product class.
	 * @param productClass The productClass to set
	 */
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	/**
	 * Returns the dualControlJobs.
	 * @return Set
	 */
	public Collection<DualControlJobImpl> getDualControlJobs() {
		return dualControlJobs;
	}

	/**
	 * Sets the dualControlJobs.
	 * @param dualControlJobs The dualControlJobs to set
	 */
	public void setDualControlJobs(Set<DualControlJobImpl> dualControlJobs) {
		this.dualControlJobs = dualControlJobs;
	}

	protected MandantImpl() {
	}

	public MandantImpl(String code) {
		this.code = code;
	}

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setAutoCheck(boolean autoCheck) {
        this.autoCheck = autoCheck;
    }

    public boolean getAutoCheck() {
        return autoCheck;
    }

}
