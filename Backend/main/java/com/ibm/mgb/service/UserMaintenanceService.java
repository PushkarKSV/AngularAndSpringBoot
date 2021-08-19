package com.ibm.mgb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface UserMaintenanceService {

	public Optional<List<Object[]>> getUserDetailService(String username);
	public Optional<List<Object[]>> getUserMaintenanceService(String mandant);
	public int validateUser(String loggedInUser);
	public Optional<List<Object[]>> getResultandCommentservice(String mandant);
}
