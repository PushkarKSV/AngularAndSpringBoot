/*
 * Created on Sep 14, 2006
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
public abstract class AbstractManualState extends StateImpl {

    private static final long serialVersionUID = 7946549765932580105L;

    protected AbstractManualState() {
	}

	protected AbstractManualState(StateIdImpl stateId) {
		super(stateId);
	}

	public abstract boolean isReclamationRequired();

	public abstract ReclamationStateImpl getReclamationState();

}
