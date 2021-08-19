package com.ibm.mgb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ibm.mgb.model.Configuration;
import com.ibm.mgb.model.Employee;
import com.ibm.mgb.model.Instrument;
import com.ibm.mgb.model.T01Employee;

@Service
public interface AdministrationService {

	public Optional<List<Object[]>> getConfigurationService(String mandant);
	public Optional<List<Object[]>> getInstrumentService(String mandant);
	public List<Instrument> retrieveAllInstruments(String client);
//	public List<Instrument> getpricecheckCategory(String mandant);
	public Optional<List<Object[]>> getpricecheckCategory(String mandant);
	public List<Configuration> retrieveAllConfigurations(String mandantId);
	public List<Employee> retrieveAllEmployees(String mandantId);
	public List<Employee> retrieveAllEmployeeByFirstName(String mandantId, String firstName);
	public void updateEmployee(String mandantId,String UserId, String firstName, String lastName, String phone, String email,
			String nt_ID,String changedDate);
	public List<Employee> retrieveAllEmployeeDetails(String mandantId, String firstName);
	public List<Employee> retrievedeleteEmployee(String mandantId, String nt_ID);
	
	public T01Employee updateEmployee(String mandantId,String UserId, Employee employee);
	
			
	

	
	
}
