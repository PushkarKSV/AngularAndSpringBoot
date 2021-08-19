/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.server.security;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import org.apache.log4j.Logger;



/**
 * General purpose utility methods related to the security component of the MGB application.
 *
 * @author WSY4148
 */
public class SecurityUtils {
	private static final String LOOKUP_NAME = "jdbc/UserDB";
	private static final String PARAM_SSO_PROPERTIES = "sso.properties";

	private static final Logger log = Logger.getLogger(SecurityUtils.class);
	@SuppressWarnings("unused")
    private static DataSource dataSource = null;
	private static boolean checkAuthorization = false;
	private static Collection<String> trustedDomains;	
	private static Properties ssoProperties = new Properties();

	
	static {
		try {
			System.out.println("##Inside SecurityUtils try block##");
			init(PARAM_SSO_PROPERTIES);
		} catch (Exception e) {
			System.out.println("##Inside SecurityUtils catch block##");
			log.error("Error initialing NTLM security utils", e);
		}
	}
	
	public static void init(String ssoPropertiesName) throws ServletException{
		log.debug("Param " + PARAM_SSO_PROPERTIES + " = " + ssoPropertiesName);
		ssoProperties = new Properties();
		
		System.out.println("##Inside SecurityUtils init ##");
		if (ssoPropertiesName == null) {
			String msg = "could not resolve init parameter " + PARAM_SSO_PROPERTIES;
			System.out.println("##msg##"+msg);
			log.fatal(msg);
			throw new ServletException(msg);
		}

		try {
			InputStream in = SecurityUtils.class.getResourceAsStream("/" + ssoPropertiesName);
			System.out.println("##InputStream##"+in);
			ssoProperties.load(in);
		} catch (IOException e) {
			System.out.println("###could not find sso.properties###");
			log.fatal("could not find sso.properties");
			throw new ServletException(e);
		}

		if ("yes".equals(ssoProperties.getProperty("checkAuthentication"))) {
			System.out.println("##checkAuthorization##"+checkAuthorization);
			checkAuthorization = true;
		}

		trustedDomains = new ArrayList<String>();
		String str = ssoProperties.getProperty("trustedDomains");
		if (str != null) {
			System.out.println("##trustedDomains##"+trustedDomains);
			trustedDomains = new ArrayList<String>(Arrays.asList(org.springframework.util.StringUtils.split(str, ",")));
		}

		if (checkAuthorization) {
			try {
				System.out.println("##ssoProperties.getProperty(\"dbDriverClass\")##"+ssoProperties.getProperty("dbDriverClass"));
				Class.forName(ssoProperties.getProperty("dbDriverClass"));
			} catch (ClassNotFoundException e) {
				log.fatal("could find driver", e);
				throw new ServletException(e);
			}
	
			try {
				Context init = new InitialContext();
				Context ctx = (Context) init.lookup("java:comp/env");
				dataSource = (DataSource) ctx.lookup(ssoProperties.getProperty("datasourceName", LOOKUP_NAME));
				System.out.println("##dataSource##"+dataSource);
			} catch (NamingException ne) {
				System.out.println("##\"could not find datasource##");
				log.fatal("could not find datasource");
				throw new ServletException(ne);
			}
		}		
	}
	
	public static LoginContext checkAuthenticationThrowsException(HttpServletRequest req, HttpServletResponse resp) throws AuthorizationException, AuthenticationException {
		LoginContext lc = getLoginContext(req);
		if (lc.getNtUserId() == null) {
			throw new AuthenticationException("Could not determine windows NT-ID");
		}

		if (lc.getNtDomain() == null || !trustedDomains.contains(lc.getNtDomain().toUpperCase())) {
			throw new AuthenticationException("Windows NT domain <" +lc.getNtDomain() + "> is not trusted!");
		}

		log.debug("check authorization");
		if (checkAuthorization && !checkAuthorization(lc)) {
			throw new AuthorizationException("Authorization check failed!");
		}

		return lc;
	}
	
	
    /**
     * DOCUMENT ME!
     *
     * @param req DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
	public static LoginContext getLoginContext(HttpServletRequest req) {
        LoginContext lc = (LoginContext) req.getSession().getAttribute("LoginContext");

        if (lc == null) {
            lc = new LoginContext();

            String userId = req.getRemoteUser();
            // nur zum Offline Testen
            // userId = "MDWESTLB\\wsy4148";

            // Split domain qualified userid like MDWESTLB\wsy4148
            // into domain name and userid
            if (userId != null) {
                int iDomain = userId.indexOf("\\");

                if (iDomain < 1) {
                    lc.setNtUserId(userId);
                } else {
                    lc.setNtDomain(userId.substring(0, iDomain));
                    lc.setNtUserId(userId.substring(iDomain + 1));
                }
            }

            lc.setErrorText("");
            lc.setSessionKey(req.getSession().getId());

            req.getSession().setAttribute("LoginContext", lc);
        }

        return lc;
    }
 
     public static LoginContext getLoginContext(HttpServletRequest req, String user) {

    	req.getSession().removeAttribute("LoginContext");
   	    LoginContext lc = new LoginContext();

   		lc.setNtDomain("DIRECT_LOGIN");
   		lc.setNtUserId(user);
   		lc.setErrorText("");
   		lc.setSessionKey(req.getSession().getId());

   		req.getSession().setAttribute("LoginContext", lc);
    	log.debug("direct logged on user "+user);
    	return lc;
    }

	/**
	 * Method checkAuthentication.
	 * @param string
	 * @param string1
	 * @return boolean
	 */
	private static boolean checkAuthorization(LoginContext lc) {
		Connection con = null;
		Statement stmt = null;

		String sql = ssoProperties.getProperty("checkAuthenticationSQL");
		String dbUrl = ssoProperties.getProperty("dbUrl");
		String userid = ssoProperties.getProperty("userid");
		String password = ssoProperties.getProperty("password");

		try {
			con = DriverManager.getConnection(dbUrl, userid, password);
			System.out.println("##con##"+con);
			stmt = con.createStatement();
			System.out.println("##stmt##"+stmt);
			@SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("##Result set##"+rs);
			// no error, so user is authorized to use the application
			return true;
		} catch (SQLException e) {
			System.out.println("error checking authorization");
			log.debug("error checking authorization", e);

			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException sqle) {
				log.warn(sqle);
			}
		}
	}
	
	public static final String getSsoProperty(String key) {
		return ssoProperties.getProperty(key);
	}

	   public static String extractUserFromDomainUser(String domainUser) {
	        String userNtId = domainUser;
	        int domainDelimPos = domainUser.indexOf("/"); 
	        if ( domainDelimPos > -1 && domainUser.length() > domainDelimPos ) {
	            userNtId = domainUser.substring(domainUser.indexOf("/")+1);
	        }
	        return userNtId.toLowerCase();
	    }

}