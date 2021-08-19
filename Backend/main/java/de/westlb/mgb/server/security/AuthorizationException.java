/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.server.security;

/**
 * An exception that occures when the authorization check
 * parametrized in security modul fails.
 *
 * @author WSY4148 
 */
public class AuthorizationException extends SecurityException {
	/**
     * 
     */
    private static final long serialVersionUID = -5852660160074883655L;

    /**
	 * 
	 */
	public AuthorizationException() {
		super();
	}

	/**
	 * @param message
	 */
	public AuthorizationException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

}