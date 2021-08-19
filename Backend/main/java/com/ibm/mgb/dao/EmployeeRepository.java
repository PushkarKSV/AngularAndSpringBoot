 package com.ibm.mgb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.mgb.model.T01Employee;


@Repository
public interface EmployeeRepository  extends JpaRepository<T01Employee,Long>{

//	public Optional<List<T01Employee>> findByT01NtId(String username); 
	//public Optional<List<T01Employee>> findByT01NtId(String t01NtId);
}
