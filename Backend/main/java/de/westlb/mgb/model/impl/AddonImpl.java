package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {7F9E7AE7-0ABB-4684-B76A-0524652B7723}
 */
public class AddonImpl extends EntityImpl {
	
	/**
     * 
     */
    private static final long serialVersionUID = -8987466304597161094L;

    /** @modelguid {E4804963-36E5-4C0D-B0D9-439091186C1E} */
	private double priceTolerancePercent;

	/** @modelguid {32BEBEF3-90C4-4D10-8A94-22C49C0CD70C} */
	private int timeToleranceMinutes;
	
	/** @modelguid {A7F55FC7-57C4-402E-B9F1-1888FEC9197C} */
	private String condition;
	
	/** @modelguid {AB19F3A9-3523-47E1-98F6-BA9ACC9286D2} */
	private String comment;

	/** @modelguid {F806547E-4882-46E4-8B27-1CD4D9C3AFBB} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}
		
	@Override
    public String toString() {
		return comment+" (+/-"+priceTolerancePercent+"%, +/-"+timeToleranceMinutes+"Min)";
	}
	/**
	 * Returns the priceInPercent.
	 * @return double
	 * @modelguid {7DF8E69A-17A8-40BE-8D88-FB9CB425D577}
	 */
	public double getPriceTolerancePercent() {
		return priceTolerancePercent;
	}

	/**
	 * Returns the timeInMinutes.
	 * @return long
	 * @modelguid {A75659CE-7D79-48F5-BABC-24A5C6B94169}
	 */
	public int getTimeToleranceMinutes() {
		return timeToleranceMinutes;
	}

	/**
	 * Sets the priceInPercent.
	 * @param priceInPercent The priceInPercent to set
	 * @modelguid {A25C3907-FE03-46E7-8CB7-6F4F525355A0}
	 */
	public void setPriceTolerancePercent(double priceInPercent) {
		this.priceTolerancePercent = priceInPercent;
	}

	/**
	 * Sets the timeInMinutes.
	 * @param timeInMinutes The timeInMinutes to set
	 * @modelguid {1A302517-727F-4048-AFD7-9A043FF21F29}
	 */
	public void setTimeToleranceMinutes(int timeInMinutes) {
		this.timeToleranceMinutes = timeInMinutes;
	}

	/**
	 * Returns the comment.
	 * @return String
	 * @modelguid {61B32494-444B-4315-B8E3-33BF72F42CF4}
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Returns the condition.
	 * @return String
	 * @modelguid {D4337AFC-4DCD-476D-9B09-973D65B1FF2F}
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Sets the comment.
	 * @param comment The comment to set
	 * @modelguid {C31600CE-6B97-463F-872C-689D96E6ACDD}
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the condition.
	 * @param condition The condition to set
	 * @modelguid {DBFC7B7E-6609-4C73-A85F-D534E6A6060B}
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

}

