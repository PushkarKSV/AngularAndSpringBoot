package com.ibm.mgb.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.mgb.dao.UserMaintenanceRepository;
import com.ibm.mgb.service.UserMaintenanceService;

@Service
public class UserMaintenanceServiceImpl implements UserMaintenanceService{
	
	@Autowired
	UserMaintenanceRepository userMaintenanceRepository;

	@Transactional
	public Optional<List<Object[]>> getUserDetailService(String username){
		return userMaintenanceRepository.getUserDetailDao(username);
	}
	
	@Transactional
	public Optional<List<Object[]>> getUserMaintenanceService(String mandant){
		return userMaintenanceRepository.getUserMaintenanceDao(mandant);
	}
	
	@Transactional
	public int validateUser(String loggedInUser) {
		return userMaintenanceRepository.validateUser(loggedInUser);
	}

	@Transactional
	public Optional<List<Object[]>> getResultandCommentservice(String mandant) {
		return userMaintenanceRepository.retrieveResultandCommentDao(mandant);
	}
}
