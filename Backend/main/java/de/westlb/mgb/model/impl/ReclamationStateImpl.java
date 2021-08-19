package de.westlb.mgb.model.impl;

import de.westlb.mgb.model.definition.StateCodeDef;

/**
 * 
 * @modelguid {BB130327-8710-4E85-A851-038B830F027D}
 */
public class ReclamationStateImpl extends StateImpl implements StateCodeDef {
		
	private static final long serialVersionUID = -4344102185157645153L;

   @Override
public String getStateCodeType() {
       return RECLAMATION;
   }

	/**
	 * Constructor for ReclamationStateImpl.
	 * @modelguid {31DA315C-A58B-4FF1-B993-F5532A32BD72}
	 */
	@SuppressWarnings("unused")
    private ReclamationStateImpl() {
	}

	/** @modelguid {27BA8649-0477-43B3-AFDB-F2B0BF562F3C} */
	public ReclamationStateImpl(StateIdImpl stateId) {
		super(stateId);
	}

}

