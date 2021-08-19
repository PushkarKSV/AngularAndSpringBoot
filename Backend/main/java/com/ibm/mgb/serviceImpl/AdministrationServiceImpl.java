package com.ibm.mgb.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.mgb.dao.AdministrationRepository;
import com.ibm.mgb.model.Configuration;
import com.ibm.mgb.model.Employee;
import com.ibm.mgb.model.Employees;
import com.ibm.mgb.model.Instrument;
import com.ibm.mgb.model.T01Employee;
import com.ibm.mgb.service.AdministrationService;

@Service
public class AdministrationServiceImpl implements AdministrationService{
	
	@Autowired

	AdministrationRepository administrationRepository;

	@Transactional
	public Optional<List<Object[]>> getConfigurationService(String mandant) {
		return administrationRepository.getConfigurationDao(mandant);
	}
	
	@Transactional
	public Optional<List<Object[]>> getInstrumentService(String mandant){
		return administrationRepository.getInstrumentDao(mandant);
		
		
	
	}
	
	@Transactional
	public List<Instrument> retrieveAllInstruments(String mandant){
		String man=new String();
		man=mandant;
		System.out.println(man+"ddddddddddddddddddddddddddd");
		List<Instrument> instrumentList = new ArrayList<Instrument>();
		List<Object[]> instrumentListObject =administrationRepository.retrieveAllInstruments(mandant);
		System.out.println(administrationRepository.retrieveAllInstruments(mandant));
		for(Object[] obj :instrumentListObject) {
			Instrument instrument = new Instrument();
			instrument.setT05_instrument(String.valueOf(obj[1]));
			instrument.setT05_instrument_name(String.valueOf(obj[2]));
			instrument.setT11_name(String.valueOf(obj[13]));
			instrument.setT05_CHANGED_BY(String.valueOf(obj[6]));
			instrument.setT05_CHANGED_DATE(String.valueOf(obj[5]));
			
			
			instrumentList.add(instrument);
		}
		return instrumentList;
	}
	
	/*@Override
	public List<Employees> retrieveAllEmployee(String mandantId, String firstName) {
		
		String man=new String();
		man=mandantId;
		System.out.println(man+"ddddddddddddddddddddddddddd");
		List<Employees> EmployeesList = new ArrayList<Employees>();
		List<Object[]> EmployeesListObject =administrationRepository.retrieveAllEmployees(man,firstName);
		System.out.println(administrationRepository.retrieveAllEmployees(man,firstName));
		for(Object[] obj :EmployeesListObject) {
			Employees employee = new Employees();
			employee.setNT_ID(String.valueOf(obj[0]));
			employee.setFirstName(String.valueOf(obj[1]));
			employee.setLastName(String.valueOf(obj[2]));
			employee.setPhone(String.valueOf(obj[3]));
			employee.setEmail(String.valueOf(obj[4]));
			employee.setTrader_code(String.valueOf(obj[5]));
			employee.setName(String.valueOf(obj[6]));
			String firstName1=String.valueOf(obj[1]).concat(" ").concat(String.valueOf(obj[2]));
			
			employee.setTrader_name(firstName1);
			
			
			
			
			
			
			EmployeesList.add(employee);
		}
		return EmployeesList;
		
	}*/
	
	@Transactional
	public List<Employee> retrieveAllEmployees(String mandant){
		String man=new String();
		man=mandant;
		System.out.println("Inside retrieveAllEmployees() ---");
		List<Employee> employeesList = null;
		
		System.out.println("calling administrationRepository.retrieveAllEmployees(mandant) ---");
		List<Object[]> employeesListObject =administrationRepository.retrieveAllEmployees(mandant);
		if(employeesListObject != null) {
			employeesList = new ArrayList<Employee>();
			for(Object[] obj :employeesListObject) {
				Employee employee = new Employee();
				employee.setNT_ID(String.valueOf(obj[1]));
				employee.setFirstName(String.valueOf(obj[3]));
				employee.setLastName(String.valueOf(obj[4]));
				employee.setPhone(String.valueOf(obj[5]));
				employee.setEmail(String.valueOf(obj[6]));
				
				employeesList.add(employee);
			}
		}
		return employeesList;
	}
//	
//	
//	@Transactional
//	public List<Employees> retrieveAllEmployeeDetails(String mandant){
//		String man=new String();
//		man=mandant;
//		System.out.println(man+"ddddddddddddddddddddddddddd");
//		List<Employees> EmployeesList = new ArrayList<Employees>();
//		List<Object[]> EmployeesListObject =administrationRepository.retrieveAllEmployeeDetails(mandant);
//		System.out.println(administrationRepository.retrieveAllInstruments(mandant));
//		for(Object[] obj :EmployeesListObject) {
//			Employees employee = new Employees();
//			employee.setNT_ID(String.valueOf(obj[0]));
//			employee.setFirstName(String.valueOf(obj[1]));
//			employee.setLastName(String.valueOf(obj[2]));
//			employee.setPhone(String.valueOf(obj[3]));
//			employee.setEmail(String.valueOf(obj[4]));
//			
//			
//			
//			
//			
//			EmployeesList.add(employee);
//		}
//		return EmployeesList;
//	}
	
	
	@Transactional
	public void updateEmployee(String mandantId,String userId, String firstName, String lastName, String phone, String email,
			String nt_ID,String changedDate)
	{	
		System.out.println("mandantId "+mandantId);
		System.out.println("UserId "+userId);
		System.out.println("firstName "+firstName);
		System.out.println("lastName "+lastName);
		System.out.println("phone "+phone);
		System.out.println("email "+email);
		System.out.println("nt_ID "+nt_ID);
		System.out.println("changedDate "+changedDate);
		
		//administrationRepository.updateEmployee(mandantId,UserId,firstName,lastName,phone,email,nt_ID,changedDate);
		T01Employee employee = new T01Employee();
		employee.setT01Id(null);
		employee.setT01FirstName(firstName);
		employee.setT01LastName(lastName);
		employee.setT01Email(email);
		employee.setT01NtId(nt_ID);
		employee.setT01Phone(phone);
		employee.setT01ChangedBy(userId);
		
		administrationRepository.save(employee);
	}
	
	
	
	
	
	
	
	@Transactional
	public List<Configuration> retrieveAllConfigurations(String mandant){
		String man=new String();
		man=mandant;
		System.out.println(man+"ddddddddddddddddddddddddddd");
		List<Configuration> ConfigurationList = new ArrayList<Configuration>();
		List<Object[]> ConfigurationListObject =administrationRepository.retrieveAllConfigurations(mandant);
//		System.out.println(administrationRepository.retrieveAllConfigurations(mandant));
		System.out.println(ConfigurationListObject+"Hi");
		for(Object[] obj :ConfigurationListObject) {
			
			
			Configuration configuration = new Configuration();
			if(obj[0].toString()!=null)
			configuration.setKey(String.valueOf(obj[0]));
			
			configuration.getKey();
//			if(obj[1].toString()!="null")
//			System.out.print(String.valueOf(obj[1]));
			System.out.println(String.valueOf(obj[1]));
			if(String.valueOf(obj[1])!=(null))
				configuration.setValue(String.valueOf(obj[1]));
			configuration.getValue();
//			configuration.setFK_T42_T09_MANDANT();
//			configuration.setT42_CHANGED_DATE(obj[3].toString());
//			if(obj[4].toString()!="null")
			configuration.setChangedBy(String.valueOf(obj[4]));
//			
//			System.out.println(configuration.getValue());
//			System.out.print(configuration.getFK_T42_T09_MANDANT());
			
			
			ConfigurationList.add(configuration);
		}
		return ConfigurationList;
	}
	
	
	public List<Employee> retrievedeleteEmployee(String mandantId, String nt_ID) {
		return administrationRepository.retrievedeleteEmployee(mandantId,nt_ID);
	}
	
	
	
	
//	@Transactional
//	public  void updatepriceCheck(String mandantId, String instrument, String instrument_name,
//			String t11_name)
//	{
//		System.out.println(mandantId);
//		System.out.println(instrument);
//		
//	 administrationRepository.updatepriceCheck(mandantId,instrument,instrument_name,t11_name);
//	}
//	@Transactional
//	public List<Instrument> getpricecheckCategory(String mandant) {
//		List<Instrument> instrumentList = new ArrayList<Instrument>();
//		List<Object[]> instrumentListObject= administrationRepository.getpricecheckCategory(mandant);
//		for(Object[] obj :instrumentListObject) {
//			Instrument instrument = new Instrument();
//			
//			instrument.setT05_instrument_name(obj[1].toString());
//			
//			instrumentList.add(instrument);
//		}
//		return instrumentList;
//	
//	}
	
	@Transactional
	public Optional<List<Object[]>> getpricecheckCategory(String mandant) {
		
		return administrationRepository.getpricecheckCategory(mandant);
		
	
	
	}

	@Override
	public List<Employee> retrieveAllEmployeeDetails(String mandantId, String firstName) {
		String man=new String();
		man=mandantId;
		System.out.println(man+"ddddddddddddddddddddddddddd");
		List<Employee> EmployeesList = new ArrayList<Employee>();
		List<Object[]> EmployeesListObject =administrationRepository.retrieveAllEmployeeDetails(mandantId,firstName);
		System.out.println(administrationRepository.retrieveAllEmployeeDetails(mandantId,firstName));
		for(Object[] obj :EmployeesListObject) {
			Employee employee = new Employee();
			employee.setNT_ID(String.valueOf(obj[0]));
			employee.setFirstName(String.valueOf(obj[1]));
			employee.setLastName(String.valueOf(obj[2]));
			employee.setPhone(String.valueOf(obj[3]));
			employee.setEmail(String.valueOf(obj[4]));
			employee.setRole(String.valueOf(obj[5]));
			
			
			
			
			
			EmployeesList.add(employee);
		}
		return EmployeesList;

	}
	
	
	
}
