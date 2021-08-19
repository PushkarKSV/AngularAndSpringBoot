package com.ibm.mgb.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mgb.exception.MandantNotFoundException;
import com.ibm.mgb.exception.UserNotFoundException;
import com.ibm.mgb.service.UserMaintenanceService;
import com.ibm.mgb.util.MGBUtils;
import com.ibm.mgb.util.MgbContants;
import com.ibm.mgb.util.SecurityUtil;

@CrossOrigin(origins=MgbContants.server_path)
@RestController
public class UserMaintenanceController extends MgbController{
	
	private static final Logger logger = Logger.getLogger(UserMaintenanceController.class);
//	private static String userName1 = "IBM0330";
	
	@Autowired
	private UserMaintenanceService userMaintenanceService;
	
	@GetMapping("/UserId/UserProfile")
//	public  ArrayList retrieveUserProfileUserID()
	public  ArrayList<String> retrieveUserProfileUserID(final Principal user,
			HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
	String UserID="IBM0368";
		List obj =new ArrayList();
		obj.add(UserID.toUpperCase());		
		System.out.println(obj);
	return (ArrayList) obj;
//		int validateUserCount = 0;
//		String loggedInUser = SecurityUtil.getUserObject( request, response);
//		
//		if(!loggedInUser.isEmpty()) {
////			validateUserCount = userMaintenanceService.validateUser(loggedInUser.toUpperCase());
//		validateUserCount = userMaintenanceService.validateUser(loggedInUser);
////			System.out.println("validateUserCount :"+validateUserCount);
//			
//		}
//		if(validateUserCount>0) {
//			ArrayList<String> loggedInUserObj = new ArrayList<String>();
//			loggedInUserObj.add(loggedInUser.toUpperCase());
//			
//			return loggedInUserObj;
//		}else {
//			return null;
//		}
	}
		
	
	@GetMapping("/Users/{userId}/UserDetails")
	public ResponseEntity<Optional<List<Object[]>>> retrieveUserDetails(@PathVariable String userId) {
		String userName = userId;
		Optional<List<Object[]>> userDetails =  userMaintenanceService.getUserDetailService(userName);
		logger.info("Test");
		System.out.println(userDetails+"---");
		if(!userDetails.isPresent())
			throw new UserNotFoundException("User Details not found for the user: " + userName + " or Invalid User!!");
		return new ResponseEntity<Optional<List<Object[]>>>(userDetails, HttpStatus.OK);
	}
	
	@GetMapping("/Mandants/{mandant}/UserMaintenance")
	public ResponseEntity<Optional<List<Object[]>>> getUserMaintenance(@PathVariable String mandant) {
		logger.info("UserMaintenance Page: " + mandant);
		String mandantName = mandant;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid mandant name!");
		Optional<List<Object[]>> userlist =  userMaintenanceService.getUserMaintenanceService(mandantId);
		if(!userlist.isPresent())
			throw new MandantNotFoundException("No user available for the mandant: " + mandantName + " or Invalid Mandant!!");
		return new ResponseEntity<Optional<List<Object[]>>>(userlist, HttpStatus.OK);
	}
	
	@GetMapping("/Mandants/{mandant}/ResultandComment")
	public ResponseEntity<Optional<List<Object[]>>> retrieveResultandComment(@PathVariable String mandant) {
		String mandantName = mandant;
		String mandantId = MGBUtils.getMandant(mandantName);
		Optional<List<Object[]>> ResultandComment =  userMaintenanceService.getResultandCommentservice(mandantId);
		logger.info("Test");
		if(!ResultandComment.isPresent())
			throw new UserNotFoundException("User Details not found for the user: " + ResultandComment + " or Invalid User!!");
		return new ResponseEntity<Optional<List<Object[]>>>(ResultandComment, HttpStatus.OK);
	}

}
