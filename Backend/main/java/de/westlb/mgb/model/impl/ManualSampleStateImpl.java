/*
 * Created on Sep 11, 2006
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ManualSampleStateImpl extends AbstractManualState {

	private static final long serialVersionUID = -9207633763500588490L;

   @Override
public String getStateCodeType() {
       return SAMPLE;
   }

	@SuppressWarnings("unused")
    private ManualSampleStateImpl() {
	}

	public ManualSampleStateImpl(StateIdImpl stateId) {
		super(stateId);
	}

	@Override
    public boolean isReclamationRequired() {
		return false;
	}

	@Override
    public ReclamationStateImpl getReclamationState() {
		return null;
	}

}
