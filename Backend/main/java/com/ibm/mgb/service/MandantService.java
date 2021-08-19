package com.ibm.mgb.service;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.finder.EmployeeSearchParams;
import de.westlb.mgb.persistence.PersistenceException;

@Service
public interface MandantService {

	public List<MandantImpl> retrieveAllMandantService(EmployeeSearchParams param) throws PersistenceException, RemoteException;
	//public Optional<List<T01Employee>> retrieveAllMandantService(String username);
	public Optional<List<Object[]>> getEAAMandantService(String username);
	public Optional<List<Object[]>> getCBBMandantService(String username);
	public Optional<List<Object[]>> getPAGMandantService(String username);
	public Optional<List<Object[]>> getOpenJobService(String mandant);
	public Optional<List<Object[]>> getOpenTradeService(String mandant);
	public Optional<List<Object[]>> getSearchTradeService(String mandant);
	public Optional<List<Object[]>> getJobByMandantService(String mandant); 
	public Optional<List<Object[]>> getTradeByJobIdService(String mandant, String jobId);
	public List<Object[]> getSelectedDateJobsService(String mandant, LocalDate FromDate, LocalDate ToDate);
	public List<Object[]> getSelectedDateTradesService(String mandant, LocalDate FromDate, LocalDate ToDate);
//	public List<Object[]> getOverviewTradeNOJobIdService(String mandant,String tradeId);
	public List<Object[]> getOverviewTradeService(String mandant, String jobId,String tradeId);
	public List<Object[]> getOverviewMarketDataService(String mandant, String jobId,String tradeId);
	public List<Object[]> getOverviewCheckResultService(String mandant,String jobId, String tradeId);
	public List<Object[]> getDerivativeOverviewTradeService(String mandant, String jobId,String tradeId);
	public List<Object[]> getDerivativeOverviewMarketDataService(String mandant, String jobId,String tradeId);
	public List<Object[]> getDerivativeOverviewCheckResultService(String mandant,String jobId, String tradeId);
	public Optional<List<Object[]>> getOverviewAssetsDataService(String mandant, String jobId, String tradeId);
//	public Optional<List<Object[]>> getOverviewAssetsDataService1(String mandant,  String tradeId);
	public Optional<List<Object[]>> getTradeHistoryService(String mandant, String jobId, String tradeId);
//	public Optional<List<Object[]>> getTradeHistoryNOJobIdService(String mandant,String tradeId);
	public Optional<List<Object[]>> getDerivativeStornoGroupService(String mandant, String jobId, String tradeId);	
//	public Optional<List<Object[]>> getDerivativeStornoGroupNOJobIdService(String mandant,String tradeId);	
//	public void updateCommentsService(String userName,String mandant,String JobId,String Trade,String Result,String Comment);
	
//	public List<String> retrieveReportLocations();
	public void updatepriceCheck(String mandantId, String t05_instrument, String t05_instrument_name, String t11_name,String UserId,String changedDate);
	public void updatepriceCheck1(String mandantId, String t05_instrument, String t05_instrument_name, String UserId,String changedDate);
	public void updateCommentsService(String userName,String mandant,String JobId,String Trade,String Result,String Comment);
	public void insertFileForCommentsService(byte[] file,String fileName);
	public int getUpdatedFileIDService();
	public void updateFileForCommentsService(int FileId,String mandant,String JobId,String TradeId);
	public List<String> retrieveReportLocations();
	public byte[] getFile(String mandant,String jobId,String tradeId);
	public String getFileStatus(String mandant,String jobId,String tradeId);
	public byte[] getFile1();
	public Optional<List<Object[]>> getFileName(String mandant,String jobId,String tradeId);
	public void updateConfigurationValue(String mandantId, String t42_key, String t42_value, String userId1,
			String changedDate);

}
