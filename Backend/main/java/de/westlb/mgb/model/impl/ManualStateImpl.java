package de.westlb.mgb.model.impl;

import de.westlb.mgb.model.definition.StateCodeDef;

/**
 * 
 * @modelguid {930494B1-5657-4899-A103-2764E4843594}
 */
public class ManualStateImpl extends AbstractManualState implements StateCodeDef {

	private static final long serialVersionUID = 6171115423566310236L;

	@Override
    public String getStateCodeType() {
	   return MANUAL;
	}

	/**
	 * Constructor for ManualStateImpl.
	 * @modelguid {57A49228-9B4C-4E92-AD4E-21DE6D641216}
	 */
	@SuppressWarnings("unused")
    private ManualStateImpl() {
	}

	/** @modelguid {A82DFDB2-242B-472D-A591-BA910D4F367D} */
	public ManualStateImpl(StateIdImpl stateId) {
		super(stateId);
	}

	/** @modelguid {515F0006-063E-4EC8-96E5-E118DA0D868E} */
	private boolean isReclamationRequired;

	/** @modelguid {8B122FB7-422A-4838-B309-9ED1ACB25509} */
	private ReclamationStateImpl reclamationState;

	/**
	 * Returns the isReclamationRequired.
	 * @return boolean
	 * @modelguid {9844FBB3-1776-4648-868A-82F1EC387C4D}
	 */
	@Override
    public boolean isReclamationRequired() {
		return isReclamationRequired;
	}

	/** @modelguid {E9B70017-5D1C-4428-B78C-6F9B85590A71} */
	@SuppressWarnings("unused")
    private boolean getIsReclamationRequired() {
		return isReclamationRequired;
	}

	/**
	 * Sets the isReclamationRequired.
	 * @param isReclamationRequired The isReclamationRequired to set
	 * @modelguid {E6F006A2-6F0A-471C-AC50-7DF5BDADAB58}
	 */
	public void setIsReclamationRequired(boolean isReclamationRequired) {
		this.isReclamationRequired = isReclamationRequired;
	}

	/**
	 * Returns the reclamationState.
	 * @return ReclamationStateImpl
	 * @modelguid {5D5EBA12-7F7C-43B6-8402-79C8E2BBF165}
	 */
	@Override
    public ReclamationStateImpl getReclamationState() {
		return reclamationState;
	}

	/**
	 * Sets the reclamationState.
	 * @param reclamationState The reclamationState to set
	 * @modelguid {3798E72E-94B0-4E7E-93CF-D434B743AC72}
	 */
	public void setReclamationState(ReclamationStateImpl reclamationState) {
		this.reclamationState = reclamationState;
	}

}

