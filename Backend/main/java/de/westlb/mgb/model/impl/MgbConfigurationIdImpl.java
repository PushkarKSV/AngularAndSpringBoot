/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


/*
 * Created on Mar 26, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.io.Serializable;


/**
 * DOCUMENT ME!
 *
 * @author WSY4148 
 */
public class MgbConfigurationIdImpl  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7977196911080299272L;
	private String key;
    private MandantImpl mandant;

    @SuppressWarnings("unused")
    private MgbConfigurationIdImpl() {
    }

    /**
     * Creates a new MgbConfigurationIdImpl object.
     *
     * @param mandant DOCUMENT ME!
     * @param key DOCUMENT ME!
     */
    public MgbConfigurationIdImpl(MandantImpl mandant, String key) {
        this.mandant = mandant;
        this.key = key;
    }

	@Override
    public int hashCode() {
		if (key == null || mandant == null) {
			return 0;
		}
        return toString().hashCode();
	}
	
    /**
     * Method used by OSCache libary to build filename for
     * the cached object.
     * 
     * @return String
     */
    @Override
    public String toString() {
        if ((key == null) || (mandant == null)) {
            return "";
        }
        return key + " " + mandant.getCode();
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public MandantImpl getMandant() {
        return mandant;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setKey(String string) {
        key = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @param impl
     */
    public void setMandant(MandantImpl impl) {
        mandant = impl;
    }

    /* (Kein Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} 
        if (!(obj instanceof MgbConfigurationIdImpl))
            return false;
		return toString().equals( ((MgbConfigurationIdImpl)obj).toString() );
    }

}