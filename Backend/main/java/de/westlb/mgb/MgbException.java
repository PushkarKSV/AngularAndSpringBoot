package de.westlb.mgb;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Exception with optional message parameters and root cause.
 * Message templates are taken from a resource.
 * An application may set the name of the bundle by setting the system
 * property <code>MgbException.SYSTEM_PROPERTY</code>
 * before the class <code>MgbException</code> is loaded.
 * The default bundle name is <code>Mgb.DEFAULT_RESOURCE</code>.
 * Messages may take parameters, indicated as <em>#1, #2, #3, ..., #9</em>.
 * Actual parameters are passed to the various constructors. Since message
 * resources are loaded using the resource bundle mechanism, this class
 * supports internationalized messages.
 * 
 * @author Manfred Boerner
 */
public class MgbException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3324349148440924391L;
	private final String key;
	private final Object[] params;
	protected final String resource;
	protected Throwable cause;

	public static final String E_NOT_AUTHORIZED = "NotAuthorized";
	
	/**
	 * Constructor, no message parameters, no root cause
	 */
	public MgbException(String key) {
		this(key, new Object[0], null);
	}

	/**
	 * Constructor, one message parameter, no root cause
	 */
	public MgbException(String key, Object param) {
		this(key, param, null);
	}

	/**
	 * Constructor, two message parameters, no root cause
	 */
	public MgbException(String key, Object param1, Object param2) {
		this(key, param1, param2, null);
	}

	/**
	 * Constructor, three message parameters, no root cause
	 */
	public MgbException(String key, Object param1, Object param2, Object param3) {
		this(key, param1, param2, param3, null);
	}

	/**
	 * Constructor, any number of message parameters, no root cause
	 */
	public MgbException(String key, Object[] params) {
		this(key, params, null);
	}

	/**
	 * Constructor, no message parameters, root cause
	 */
	public MgbException(String key, Throwable cause) {
		this(key, new Object[]{}, cause);
	}

	/**
	 * Constructor, one message parameter, root cause
	 */
	public MgbException(String key, Object param, Throwable cause) {
		this(key, new Object[]{param}, cause);
	}

	/**
	 * Constructor, two message parameters, root cause
	 */
	public MgbException(String key, Object param1, Object param2, Throwable cause) {
		this(key, new Object[]{param1, param2}, cause);
	}

	/**
	 * Constructor, three message parameters, root cause
	 */
	public MgbException(String key, Object param1, Object param2, Object param3, Throwable cause) {
		this(key, new Object[]{param1, param2, param3}, cause);
	}

	/**
	 * Constructor, any number of message parameters, root cause
	 */
	public MgbException(String key, Object[] params, Throwable cause) {
		//super(cause);
		this.cause = cause;
		
		this.key = key;
		this.params = params;
		this.resource = resource();
	}

	private String resource() {
//		String result = getClass().getName() + "Resource";
		String result = MgbException.class.getName()+ "Resource";
		System.out.println("###Inside MGB Excpetion resourse() @@@ result##"+result);
		if (System.getProperty(result) != null)
			result = System.getProperty(result);
		System.out.println("###Inside MGB Excpetion resourse()-- result##"+result);
		return result;
	}

	/**
	 * Answer message key
	 */
	public String getKey() {
		return key;
	}

	private String replace(String source, String search, String replace) {
		int end = source.indexOf(search);
		if (end < 0)
			return source;
		int start = 0;
		StringBuffer result = new StringBuffer();
		do {	
			result.append(source.substring(start, end));
			result.append(replace);
			start = end + search.length();
			end = source.indexOf(search, start);
		} while (end >= 0);
		result.append(source.substring(start));
		return result.toString();
	}

	private String message(ResourceBundle bundle) {
		String msg = null;
		try {
			msg = bundle.getString(key);
		} catch (MissingResourceException e) {
			msg = key;
			if (params != null && params.length > 0) {
				msg += "(";
				for (int i = 1; i < params.length; i++)
					msg += "'#" + i + "',";
				msg += "'#" + params.length + "')";
			}
			if (getCause() != null)
				msg += ": #e";
		}
		if (params != null) {
			for (int i = 0; i < params.length; i++)
				msg = replace(msg, "#" + (i+1), "" + params[i]);
		}
		if (getCause() != null) {
			if (getCause().getMessage() == null)
				msg = replace(msg, "#e", getCause().toString());
			else
				msg = replace(msg, "#e", getCause().getMessage());
		}
		return msg;
	}

	/**
	 * Answer message (default language)
	 */
	@Override
    public String getMessage() {
		try {
			System.out.println("Inside getMessage() ## "+ message(ResourceBundle.getBundle(resource)));
			return message(ResourceBundle.getBundle(resource));
		} catch (MissingResourceException e){
			e.printStackTrace();
			return super.getMessage();
		}
	}
		
	/**
	 * Answer message, use language according to specified locale
	 */
	public String getMessage(Locale locale) {
		try {
			return message(ResourceBundle.getBundle(resource, locale));
		} catch (MissingResourceException e){
			e.printStackTrace();
			return super.getMessage();
		}
	}

	/**
	 * Answer string representation.
	 */	
	@Override
    public String toString() {
		String s = "Error " + key + ": " + getMessage();
		if (getCause() != null)
			s += "(cause: " + getCause() +")";
		return s;
	}
	/**
	 * Returns the cause.
	 * @return Throwable
	 */
	@Override
    public Throwable getCause() {
		return cause;
	}

	/**
	 * Sets the cause.
	 * @param cause The cause to set
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}
