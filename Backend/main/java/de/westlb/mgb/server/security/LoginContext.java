package de.westlb.mgb.server.security;

import java.io.Serializable;

/**
 * @author WSY4957
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LoginContext implements Serializable {

	
	/**
     * 
     */
    private static final long serialVersionUID = 5110225088367358947L;
    private String ntUserId;
	private String ntDomain;
	private String errorText;
	private String sessionKey;

	public LoginContext() {
	}

	/**
	 * Returns the ntUserId.
	 * @return String
	 */
	public String getNtUserId() {
		return ntUserId;
	}

	/**
	 * Sets the ntUserId.
	 * @param ntUserId The ntUserId to set
	 */
	public void setNtUserId(String ntUserId) {
		this.ntUserId = ntUserId;
	}

	/**
	 * Returns the errorText.
	 * @return String
	 */
	public String getErrorText() {
		return errorText;
	}

	/**
	 * Sets the errorText.
	 * @param errorText The errorText to set
	 */
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	@Override
    public String toString() {
		return ntUserId + ":" + ntDomain + "" + sessionKey;
	}

    /**
     * Returns the sessionKey.
     * @return String
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * Sets the sessionKey.
     * @param sessionKey The sessionKey to set
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * Returns the ntDomain.
     * @return String
     */
    public String getNtDomain() {
        return ntDomain;
    }

    /**
     * Sets the ntDomain.
     * @param ntDomain The ntDomain to set
     */
    public void setNtDomain(String ntDomain) {
        this.ntDomain = ntDomain;
    }

}
