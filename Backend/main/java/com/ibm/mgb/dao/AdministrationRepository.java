package com.ibm.mgb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.ibm.mgb.model.Employee;
import com.ibm.mgb.model.T01Employee;

//@Repository
@EnableJpaRepositories(basePackages = "com.ibm.mgb")
public interface AdministrationRepository extends JpaRepository<T01Employee,Integer>{

	@Query(value="select t42.t42_key, t42.t42_value, t42.fk_t42_t09_mandant from t42_mgb_configuration t42, t09_mandant t09 \r\n" + 
			"			where t42.fk_t42_t09_mandant=t09.T09_ID \r\n" + 
			"			and not regexp_like(T42_KEY,'^MAIL(*)|^LOOK(*)') and t09.t09_id=:mandant",nativeQuery = true)	
	public Optional<List<Object[]>> getConfigurationDao(@Param("mandant") String mandant);
	
	@Query(value="select t05.t05_instrument, t05.t05_instrument_name, t11.t11_tolerance_percent\r\n" + 
			"from t05_instrument t05 , t11_price_check_category t11,t09_mandant t09 \r\n" + 
			"where t05.T05_enabled = 'Y' and t05.fk_t05_t11_price_check = t11_id\r\n" + 
			"and t11.t11_enabled = 'Y'and t09.t09_ID = t05.fk_t05_t09_mandant \r\n" + 
			"and t05.fk_t05_t09_mandant = t11.FK_T11_T09_MANDANT and t09.t09_id=:mandant",nativeQuery = true)
	public Optional<List<Object[]>> getInstrumentDao(@Param("mandant") String mandant);
	
	@Query(value="  select * from t05_instrument t05 , t11_price_check_category t11,t09_mandant t09\r\n" + 
			"			where t05.T05_enabled = 'Y' and t05.fk_t05_t11_price_check = t11_id\r\n" + 
			"			and t11.t11_enabled = 'Y'and t09.t09_ID = t05.fk_t05_t09_mandant \r\n" + 
			"			and t05.fk_t05_t09_mandant = t11.FK_T11_T09_MANDANT and t09.t09_id=:client ORDER BY t05.t05_instrument   ",nativeQuery = true)
	public List<Object[]> retrieveAllInstruments(@Param("client") String client);

	
	
//	@Query(value=" select T11_NAME from t11_price_check_category where FK_T11_T09_MANDANT=:mandant",nativeQuery = true)
//	public List<Object[]> getpricecheckCategory(@Param("mandant") String mandant);

	@Query(value=" select  T11_NAME from t11_price_check_category where FK_T11_T09_MANDANT=:mandant order by T11_NAME  ",nativeQuery = true)
	public Optional<List<Object[]>> getpricecheckCategory(@Param("mandant") String mandant);

	
	@Query(value="select * from t42_mgb_configuration t42 \r\n" + 
			"						where\r\n" + 
			"						 not regexp_like(T42_KEY,'^MAIL(*)|^LOOK(*)') and t42.fk_t42_t09_mandant=:mandant ORDER BY t42.T42_KEY",nativeQuery = true)
	
	public List<Object[]> retrieveAllConfigurations(@Param("mandant") String mandant);
	
	
	
//	@Query(value="Update t11_price_check_category set t11_NAME=:t11_name \r\n" + 
//			"   \r\n" + 
//			"			            where  t11_id=( select distinct fk_t05_t11_price_check from t05_instrument t05,t11_price_check_category t11,t09_mandant t09  where t05.fk_t05_t09_mandant=:mandantId \r\n" + 
//			"			            and t05.T05_enabled = 'Y'\r\n" + 
//			"			            and t05.fk_t05_t11_price_check = t11_id\r\n" + 
//			"			            and t11.t11_enabled = 'Y'\r\n" + 
//			"			            \r\n" + 
//			"			           and t05.fk_t05_t09_mandant = t11.FK_T11_T09_MANDANT and t05.t05_instrument=:instrument and t05.t05_instrument_name=:instrument_name)",nativeQuery = true)
//	public   void updatepriceCheck(String mandantId, String instrument, String instrument_name,
//			String t11_name);
	
	@Query(value="select  * from t01_Employee,T17_USER_ROLE  where t01_ID=FK_T17_T01_EMPLOYEE and  FK_T01_T02_MANDANT=:mandant ",nativeQuery = true)
	public List<Object[]> retrieveAllEmployees(@Param("mandant") String mandant);
	

	@Query(value="select T01_NT_ID,T01_FIRSTNAME,T01_LASTNAME,T01_PHONE,T01_EMAIL,T18_TRADER_CODE,T07_NAME from t01_Employee,t18_trader,T17_USER_ROLE,\r\n" + 
			"t07_SOURCE_SYSTEM where t01_ID=FK_T17_T01_EMPLOYEE and  FK_T01_T02_MANDANT=:mandant and t01_ID=FK_T18_T01_EMPLOYEE and \r\n" + 
			"FK_T18_T07_SOURCE_SYSTEM=T07_ID and T01_FIRSTNAME=:firstName ",nativeQuery = true)
	public List<Object[]> retrieveAllEmployeeForGivenFirstName(@Param("mandant") String mandant,@Param("firstName") String firstName);
	
	
	
	@Modifying
	@Query(value="update t01_Employee set T01_FIRSTNAME=:firstName,T01_LASTNAME=:lastName,T01_PHONE=:phone,t01_Email=:email,T01_CHANGED_DATE=TO_DATE(:changedDate,'DD-MM-YY'),T01_CHANGED_BY=:UserId \r\n" + 
			"where T01_NT_ID=:nt_ID and FK_T01_T02_MANDANT=:mandantId ",nativeQuery = true)
	public void updateEmployee(String mandantId, String UserId, String firstName, String lastName, String phone,
			String email, String nt_ID, String changedDate);

	
	@Query(value="select T01_NT_ID,T01_FIRSTNAME,T01_LASTNAME,T01_PHONE,T01_EMAIL,FK_T17_T29_ROLE from t01_Employee,T17_USER_ROLE where t01_ID=FK_T17_T01_EMPLOYEE and FK_T01_T02_MANDANT=FK_T17_T09_MANDANT and FK_T01_T02_MANDANT=:mandant and \r\n" + 
			"			 T01_FIRSTNAME=:firstName order by FK_T17_T29_ROLE  ",nativeQuery = true)
	public List<Object[]> retrieveAllEmployeeDetails(@Param("mandant") String mandant,@Param("firstName") String firstName);

	
	@Query(value="delete from T17_USER_ROLE where t17_ID=(select t01_ID from t01_Employee where T01_NT_ID=:nt_ID and FK_T01_T02_MANDANT=:mandantId)\r\n" + 
			"   ",nativeQuery = true)
	public List<Employee> retrievedeleteEmployee(String mandantId, String nt_ID);
	
	

	
	
/*	@Modifying
	@Query(value="update t01_Employee set T01_FIRSTNAME=:firstName,T01_LASTNAME=:lastName,T01_PHONE=:phone,t01_Email=:email,T01_CHANGED_DATE=TO_DATE(:Date1,'DD-MM-YY'),T01_CHANGED_BY=:Userid \r\n" + 
			"where T01_NT_ID=:nt_ID and FK_T01_T02_MANDANT=:mandantId ",nativeQuery = true)
	public void updateEmployee( String mandantId, String Userid, String firstName,String lastName,String phone, String email,
			 String nt_ID,String Date1 );*/
	

	
	
	
	

}
