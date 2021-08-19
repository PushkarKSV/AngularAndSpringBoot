/*
 * Created on Jun 23, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.server.security;

/**
 * An exception that occures when the NTLM authentication
 * fails or the domain of the user is not declared as
 * trusted.
 *
 * @author WSY4148 
 */
public class AuthenticationException extends SecurityException {

	/**
     * 
     */
    private static final long serialVersionUID = 4176051167180236981L;

    /**
	 * 
	 */
	public AuthenticationException() {
		super();
	}

	/**
	 * @param message
	 */
	public AuthenticationException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

}
