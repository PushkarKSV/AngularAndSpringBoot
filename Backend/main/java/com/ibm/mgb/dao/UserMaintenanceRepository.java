package com.ibm.mgb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.ibm.mgb.model.T01Employee;

//@Repository
@EnableJpaRepositories(basePackages = "com.ibm.mgb")
public interface UserMaintenanceRepository extends JpaRepository<T01Employee,Integer> {

	@Query(value="select distinct t01.T01_NT_ID, t17.FK_T17_T29_ROLE, t01.t01_firstname, t01.t01_lastname, t01.t01_phone, t01.t01_email,\r\n" + 
			"(select max(t01_employee.t01_creation_date) from t01_employee) as T01_CREATION_DATE, t17.T17_location\r\n" + 
			"from T01_EMPLOYEE t01,T17_USER_ROLE t17\r\n" + 
			"where T01_ID= FK_T17_T01_EMPLOYEE AND UPPER(T01_NT_ID) =UPPER(:username)",nativeQuery = true)
	public Optional<List<Object[]>> getUserDetailDao(@Param("username") String username);
	
	@Query(value="select t1.t01_firstname, t1.t01_lastname, t2.fk_t17_t09_mandant,t1.t01_phone,t1.t01_email,t1.t01_nt_id,listagg(t2.fk_t17_t29_role,',') within group (order by fk_t17_t29_role),t2.t17_location \r\n" + 
			"from t01_employee t1, t17_user_role t2\r\n" + 
			"where t1.t01_id=t2.fk_t17_t01_employee and t2.fk_t17_t09_mandant = :mandant\r\n" + 
			"group by t1.t01_firstname, t1.t01_lastname, t1.t01_nt_id,t1.t01_phone,t1.t01_email,\r\n" + 
			"t2.fk_t17_t09_mandant,t2.t17_location ",nativeQuery = true)
	public Optional<List<Object[]>> getUserMaintenanceDao(@Param("mandant") String mandant);
	
//	@Query(value="select  COUNT(T01_NT_ID) from T01_EMPLOYEE,T17_USER_ROLE where T01_ID= FK_T17_T01_EMPLOYEE AND T01_NT_ID =:loggedInUser",nativeQuery = true)
	@Query(value="select  COUNT(T01_NT_ID) from T01_EMPLOYEE,T17_USER_ROLE where T01_ID= FK_T17_T01_EMPLOYEE AND UPPER(T01_NT_ID) =UPPER(:loggedInUser)",nativeQuery = true)
	public int validateUser(@Param("loggedInUser") String loggedInUser);
	
	
	@Query(value="select T10_LONG_DESCRIPTION from t10_state_Code where fk_t10_t09_state_mandant =:mandant and t10_state_type = 'manual' and t10_enabled = 'Y'",nativeQuery = true)
	public Optional<List<Object[]>> retrieveResultandCommentDao(@Param("mandant") String mandant);
}
