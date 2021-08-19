package com.ibm.mgb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mgb.model.Employee;
import com.ibm.mgb.model.Employees;
import com.ibm.mgb.model.T01Employee;
import com.ibm.mgb.service.AdministrationService;
import com.ibm.mgb.util.MGBUtils;
import com.ibm.mgb.util.MgbContants;

@CrossOrigin(origins=MgbContants.server_path)
@RestController
public  class AdministrationController {
	
	private static final Logger logger = Logger.getLogger(AdministrationController.class);
	private static String userName1 = "IBM0570";
	
	@Autowired
	private AdministrationService administrationService;
	
	
	@GetMapping("/Mandants/{client}/Employees")
	public List<Employees> retrieveEmployees(@PathVariable String client)
	{
		System.out.println("Inside AdministrationController:retrieveEmployees() ---"+ client);
		String mandantName =client;
		String mandantId = MGBUtils.getMandant(mandantName);
		System.out.println("MandantId : "+mandantId);
		List<Employees> employeeList =  administrationService.retrieveAllEmployees(mandantId);
		if(employeeList==null) {
			System.out.print("No Employees found for the mandate :"+mandantName);
		}
		else {
			System.out.print("Total Number of employees : "+ employeeList.toString());
		}
		return employeeList;
	}
	
	@GetMapping("/Mandants/{client}/{FirstName}")
	public List<Employees> retrieveEmployee(@PathVariable String client,@PathVariable String FirstName)
	{

		System.out.println("-----------------------"+client+"====================");
		String mandantName =client;
		String mandantId = MGBUtils.getMandant(mandantName);
		List<Employees> obj =  administrationService.retrieveAllEmployee(mandantId,FirstName);
		if(obj==null) 
			System.out.print("client-"+client);
		return obj;
		//return mandantService.retrieveAllInstruments();
	}
	
	@GetMapping("/Mandants/{client}/{FirstName}/EmployeeDetails")
	public List<Employees> retrieveEmployeeDetails(@PathVariable String client,@PathVariable String FirstName)
	{

		System.out.println("-----------------------"+client+"====================");
		String mandantName =client;
		String mandantId = MGBUtils.getMandant(mandantName);
		List<Employees> obj =  administrationService.retrieveAllEmployeeDetails(mandantId,FirstName);
		if(obj==null) 
			System.out.print("client-"+client);
		return obj;
		//return mandantService.retrieveAllInstruments();
	}
	
	  @PostMapping("/Mandants/{client}/{nt_ID}/Delete")
	public List<Employees> deleteEmployee(@PathVariable String client,@PathVariable String nt_ID)
	{

		System.out.println("-----------------------"+client+"====================");
		String mandantName =client;
		String mandantId = MGBUtils.getMandant(mandantName);
		List<Employees> obj =  administrationService.retrievedeleteEmployee(mandantId,nt_ID);
		if(obj==null) 
			System.out.print("client-"+client);
		return obj;
		//return mandantService.retrieveAllInstruments();
	}
	
	  @PostMapping("/{mandant}/{userId}")
		public T01Employee updateEmployee(@PathVariable String mandant,@PathVariable String userId,@RequestBody Employee value)
		{
		  String mandantName = mandant;			
		  String mandantId = MGBUtils.getMandant(mandantName);
		  Employee emp=new Employee();
		  SimpleDateFormat formatter = new SimpleDateFormat(" dd MMM yyyy ");
		  Date date = new Date();
		  String changedDate = formatter.format(date);
		  System.out.println(changedDate);
		 emp.setEmail(value.getEmail());
		 emp.setFirstName(value.getFirstName());
		 emp.setLastName(value.getLastName());
		 
		 emp.setName(value.getName());
		 emp.setPhone(value.getPhone());
		
		 emp.setPhone(value.getPhone());
		 emp.setNT_ID(value.getnt_ID());
		 emp.setPhone(value.getPhone());
		 System.out.println(emp.getEmail());
		 System.out.println(emp.getFirstName());
		 System.out.println(emp.getLastName());
		 System.out.println(emp.getPhone());
		 System.out.println(userId);
		
		 System.out.println(emp.getnt_ID());
		 
		 administrationService.updateEmployee(mandantId,userId,emp);
	
		}	        
}
