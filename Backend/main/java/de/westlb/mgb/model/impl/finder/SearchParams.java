package de.westlb.mgb.model.impl.finder;

import de.westlb.mgb.model.impl.MandantImpl;

/**
 * 
 * @modelguid {52EAB355-10E0-42E0-BED6-27F61AF71E8A}
 */
public class SearchParams {
	
	/** @modelguid {9D88BF0B-1DC0-45EA-AC68-C04DD90BE704} */
	private MandantImpl mandant;

	private int maxResults = 0;

	private String className;
	
	private String fetchClause;
	
	private boolean onlyEnabled = false;
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setClassName(Class<?> aClass) {
		this.className = aClass.getName();
	}

	public String getFetchClause() {
		return fetchClause;
	}

	public void setFetchClause(String fetchClause) {
		this.fetchClause = fetchClause;
	}

	/**
	 * Constructor for SearchParams.
	 * @modelguid {465E270D-3138-4965-B2B2-9A4022D90DDB}
	 */
	public SearchParams() {
	}

	/**
	 * Constructor for SearchParams.
	 * @param mandant
	 * @modelguid {219642E6-C1CD-498C-9FE3-51A80D19B82C}
	 */
	public SearchParams(MandantImpl mandant) {
		this.mandant = mandant;
	}

	/**
	 * Returns the mandantenId.
	 * @return long
	 * @modelguid {6F1BB50B-2717-4C2F-8BA8-A177F33893CD}
	 */
	public MandantImpl getMandant() {
		return mandant;
	}

	/**
	 * Sets the mandantenId.
	 * @param mandantenId The mandantenId to set
	 * @modelguid {4521D8FF-8846-4033-B2E5-C440C0C16711}
	 */
	public void setMandant(MandantImpl mandant) {
		this.mandant = mandant;
	}

	/**
	 * @return Returns the maxResult.
	 */
	public int getMaxResults() {
		return maxResults;
	}
	/**
	 * @param maxResult The maxResult to set.
	 */
	public void setMaxResults(int maxResult) {
		this.maxResults = maxResult;
	}

    public void setOnlyEnabled(boolean onlyEnabled) {
        this.onlyEnabled = onlyEnabled;
    }

    public boolean isOnlyEnabled() {
        return onlyEnabled;
    }
}

