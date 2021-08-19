package com.ibm.mgb.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityUtil {

	public static String getUserObject( HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String loggedInUser = "";
		String auth = request.getHeader("Authorization");
		System.out.println("test :" +auth);
	    //No NTLM authentification
	    if (auth == null) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setHeader("WWW-Authenticate", "NTLM");
//	        return null;
	    }else
	    //check what client sent
	    if (auth.startsWith("NTLM")) {
	        byte[] msg = new sun.misc.BASE64Decoder().decodeBuffer(auth.substring(5));
	        int off = 0, length, offset;

	        if (msg[8] == 1) {
	            off = 18;
	            byte z = 0;
	            byte[] msg1 = {(byte) 'N', (byte) 'T', (byte) 'L', (byte) 'M', (byte) 'S',
	                (byte) 'S', (byte) 'P', z,
	                (byte) 2, z, z, z, z, z, z, z,
	                (byte) 40, z, z, z, (byte) 1, (byte) 130, z, z,
	                z, (byte) 2, (byte) 2, (byte) 2, z, z, z, z,
	                z, z, z, z, z, z, z, z};

	            // send ntlm type2 msg
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.setHeader("WWW-Authenticate", "NTLM " + new sun.misc.BASE64Encoder().encodeBuffer(msg1).trim());
//	            return null;
	        } //receive ntlm type 3 msg
	        else if (msg[8] == 3) {
	            off = 30;
	            //username
	            length = msg[off + 9] * 256 + msg[off + 8];
	            offset = msg[off + 11] * 256 + msg[off + 10];
	           loggedInUser = new String(msg, offset, length);
	           loggedInUser = loggedInUser.replaceAll("\\W", "");   
	        } 
	    }
		return loggedInUser;
	}
}
