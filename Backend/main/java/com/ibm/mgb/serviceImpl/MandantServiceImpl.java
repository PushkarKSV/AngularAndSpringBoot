package com.ibm.mgb.serviceImpl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.mgb.dao.EmployeeRepository;
import com.ibm.mgb.service.MandantService;

import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.finder.AbstractFinder;
import de.westlb.mgb.model.impl.finder.EmployeeSearchParams;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;

@Service
public class MandantServiceImpl extends AbstractFinder implements MandantService {
	private transient Session session = null;
	  private MandantImpl mandant = null;
	  private EmployeeImpl user = null;
	  private boolean isBusy = false;
	
	public MandantServiceImpl(Session sess) {
		super(sess);
	}

	protected MandantServiceImpl() {
		super(null);
	}
	
	/*
	 * @Autowired MandantRepository mandantRepository;
	 */
	
	 private void throwRemoteException(Exception exception) throws RemoteException {
		 System.out.println("##Inside throwRemoteException ###"+exception.getMessage());
		 logger.error(exception.getMessage(),exception);
	        throw new RemoteException(exception.getMessage());
	    }
	
	public void openSession() throws PersistenceException {
		try {
        if (mandant != null && user != null && user.getNtId() != null) {
        	System.out.println("# Inside if() open session##");
            session = StoreSingleton.getUniqueInstance().openSession(mandant, user.getNtId());
        } else {
        	System.out.println("# Inside else() open session##");
            session = StoreSingleton.getUniqueInstance().openSession();
        }
        isBusy = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    public void closeSession() throws PersistenceException {
        if (session != null) {
            session.close();
        }
        isBusy = false;
    }

    private Session getSession() throws RemoteException {
    	if (session == null || !session.isOpen()) {
    		try {
    			closeSession();
    			openSession();
    		} catch (PersistenceException pe) {
    			System.out.println("Inside getssion() ##"+ pe);
	           throwRemoteException(pe);
    	 	}
    	}
    	return session;
    }

	/*
	 * @Autowired EmployeeRepository employeeRepository;
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MandantImpl> retrieveAllMandantService(EmployeeSearchParams params) throws PersistenceException, RemoteException{
		MandantServiceImpl mandants = new MandantServiceImpl(getSession());
		System.out.println("##Inside retrieveAllMandantService##");
		System.out.println("##Mandants :"+mandants);
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
	        ArrayList<String> clauseTokens = new ArrayList<String>();
	        System.out.println("#Testing##--------123456");
	        String table = "select distinct mand from "+EmployeeImpl.class.getName()+" emp join emp.mandant mand";
	        if (params != null) {
	            if (params.getRole() != null) {
	                clauseTokens.add(":role in elements(emp.roles)");
	                columnValues.put("role", params.getRole());
	            }
	            if (isNotNullOrZero(params.getFirstName())) {
	                clauseTokens.add("upper(emp.firstName) like upper(:firstName)");
	                columnValues.put("firstName", params.getFirstName());
	            }
	            if (isNotNullOrZero(params.getLastName())) {
	                clauseTokens.add("upper(emp.lastName) like upper(:lastName)");
	                columnValues.put("lastName", params.getLastName());
	            }
	            if (isNotNullOrZero(params.getNtId())) {
	                clauseTokens.add("upper(emp.ntId) = upper(:ntId)");
	                columnValues.put("ntId", params.getNtId());
	            }
	            if (isNotNullOrZero(params.getTraderCode())) {
	                clauseTokens.add("upper(tid.traderCode) like upper(:traderCode) and emp = tid.employee");
	                columnValues.put("traderCode", params.getTraderCode());
	                table = "select distinct mand from EmployeeImpl emp join emp.mandant man, TraderImpl tid";
	            }
	            if (params.hasRoles()) {
	                clauseTokens.add("size(emp.roles) > 0");                
	            }
	            if (params.isOnlyEnabled()) {
	                clauseTokens.add("mand.enabled = 'Y'");
	            }
	        }
	        System.out.println("#Testing#");
	        List<MandantImpl> res = buildQuery(table, columnValues, clauseTokens, " order by mand.name").getResultList();
	        System.out.println("result size## "+ res.size());
	        
	        List<Optional<MandantImpl>>  result =  res.stream().map(Optional::ofNullable).collect(Collectors.toList());
	        return res;
		
		// return mandantRepository.retrieveAllMandantDao(username);
	}
	
	protected boolean isNotNullOrZero(Object o) {
		if (o != null) {
			if (o instanceof String) {
				return ((String)o).length() > 0;
			} else if (o instanceof Number) {
				return ((Number)o).intValue() != 0;				
			} else {
				 // logger.warn("no additional null-check for class "+o.getClass().getName());
				return true;
			}
		}
        return false;
	}
//	@Transactional
//	public List<String> retrieveReportLocations() {
//		return mandantRepository.retrieveReportLocations();
//	}

	@Override
	public Optional<List<Object[]>> getEAAMandantService(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getCBBMandantService(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getPAGMandantService(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getOpenJobService(String mandant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getOpenTradeService(String mandant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getSearchTradeService(String mandant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getJobByMandantService(String mandant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getTradeByJobIdService(String mandant, String jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getSelectedDateJobsService(String mandant, LocalDate FromDate, LocalDate ToDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getSelectedDateTradesService(String mandant, LocalDate FromDate, LocalDate ToDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getOverviewTradeService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getOverviewMarketDataService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getOverviewCheckResultService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getDerivativeOverviewTradeService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getDerivativeOverviewMarketDataService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getDerivativeOverviewCheckResultService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getOverviewAssetsDataService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getTradeHistoryService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getDerivativeStornoGroupService(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatepriceCheck(String mandantId, String t05_instrument, String t05_instrument_name, String t11_name,
			String UserId, String changedDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatepriceCheck1(String mandantId, String t05_instrument, String t05_instrument_name, String UserId,
			String changedDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCommentsService(String userName, String mandant, String JobId, String Trade, String Result,
			String Comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertFileForCommentsService(byte[] file, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUpdatedFileIDService() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateFileForCommentsService(int FileId, String mandant, String JobId, String TradeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> retrieveReportLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getFile(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileStatus(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getFile1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<Object[]>> getFileName(String mandant, String jobId, String tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateConfigurationValue(String mandantId, String t42_key, String t42_value, String userId1,
			String changedDate) {
		// TODO Auto-generated method stub
		
	}
	
	
	/*@Transactional
	public Optional<List<T01Employee>> retrieveAllMandantService(String username) {
		return employeeRepository.findByT01NtId(username);
	}*/
	
	
	
//	@Transactional
//	public Optional<List<Object[]>> getEAAMandantService(String username){
//		return mandantRepository.getEAAMandantDao(username);
//	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getCBBMandantService(String username){
//		return mandantRepository.getCBBMandantDao(username);
//	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getPAGMandantService(String username){
//		return mandantRepository.getPAGMandantDao(username);
//	}
//		
//	@Transactional
//	public Optional<List<Object[]>> getOpenJobService(String mandant){
//		return mandantRepository.getOpenJobDao(mandant);
//	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getOpenTradeService(String mandant){
//		return mandantRepository.getOpenTradeDao(mandant);
//	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getSearchTradeService(String mandant) {
//		return mandantRepository.getSearchTradeDao(mandant);
//	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getJobByMandantService(String mandant) {
//		return mandantRepository.getJobByMandantDao(mandant);
//	}
//	
//	@Transactional
//	public  Optional<List<Object[]>> getTradeByJobIdService(String mandant, String jobId) {
//		return mandantRepository.getTradeByJobIdDao(mandant, jobId);
//	}
//	
//	@Transactional
//	public List<Object[]> getSelectedDateJobsService(String mandant, LocalDate FromDate, LocalDate Todate) {
//		return mandantRepository.getSelectedDateJobsDao(mandant,FromDate,Todate);
//	}
//	
//	@Transactional
//	public List<Object[]> getSelectedDateTradesService(String mandant, LocalDate FromDate, LocalDate Todate) {
//		return mandantRepository.getSelectedDateTradesDao(mandant,FromDate,Todate);
//	}

//	@Transactional
//	public List<Object[]> getOverviewTradeNOJobIdService(String mandant,String tradeId) {
//		return mandantRepository.getOverviewTradeNOJobIdDao(mandant,tradeId);
//	}
	
//	@Transactional
//	public List<Object[]> getOverviewTradeService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getOverviewTradeDao(mandant, jobId, tradeId);
//	}
//	
//	@Transactional
//	public List<Object[]> getOverviewMarketDataService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getOverviewMarketDataDao(mandant, jobId, tradeId);
//	}
//	
//	@Transactional
//	public List<Object[]> getOverviewCheckResultService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getOverviewCheckResultDao(mandant, jobId, tradeId);
//	}
//	
//	@Transactional
//	public List<Object[]> getDerivativeOverviewTradeService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getDerivativeOverviewTradeDao(tradeId);
//	}
//	
//	@Transactional
//	public List<Object[]> getDerivativeOverviewMarketDataService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getDerivativeOverviewMarketDataDao(mandant,jobId,tradeId);
//	}
//	
//	@Transactional
//	public List<Object[]> getDerivativeOverviewCheckResultService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getDerivativeOverviewCheckResultDao(mandant, jobId, tradeId);
//	}
//	
//	public Optional<List<Object[]>> getOverviewAssetsDataService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getOverviewAssetsDataDao(mandant, jobId, tradeId);
//	}
//	
////	public Optional<List<Object[]>> getOverviewAssetsDataService1(String mandant, String tradeId) {
////		
////		return mandantRepository.getOverviewAssetsDataDao1(tradeId);
////	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getTradeHistoryService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getTradeHistoryDao(mandant, jobId, tradeId);
//	}
//	
////	@Transactional
////	public Optional<List<Object[]>> getTradeHistoryNOJobIdService(String mandant,  String tradeId) {
////		return mandantRepository.getTradeHistoryNOJobIdDao(mandant,  tradeId);
////	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getDerivativeStornoGroupService(String mandant, String jobId, String tradeId) {
//		return mandantRepository.getDerivativeStornoGroupDao(mandant, jobId, tradeId);
//	}
//
////	@Transactional
////	public void updateCommentsService(String userName,String mandant,String JobId,String Trade, String Result, String Comment) {
////		System.out.println(userName+mandant+JobId+Trade+Result+Comment+"))))))))))))------");
////		mandantRepository.updateCommentDao(userName,mandant,JobId,Trade, Result, Comment);			
////		}
//	
//		
//	
////	@Transactional
////	public Optional<List<Object[]>> getDerivativeStornoGroupNOJobIdService(String mandant,String tradeId) {
////		return mandantRepository.getDerivativeStornoGroupNOJobIdDao(mandant,tradeId);
////	}
//	@Transactional
//	public  void updatepriceCheck(String mandantId, String instrument, String instrument_name,
//			String t11_name,String UserId,String changedDate)
//	{
//		System.out.println(mandantId);
//		System.out.println(mandantId);
//		System.out.println(instrument);
//		
//		mandantRepository.updatepriceCheck(mandantId,instrument,instrument_name,t11_name,UserId,changedDate);
//	}
//	@Transactional
//	public void updateConfigurationValue(String mandantId, String t42_key, String t42_value, String userId1,
//			String changedDate)
//	{
//		System.out.println(mandantId);
//		System.out.println(mandantId);
//		
//		
//		mandantRepository.updateConfigurationValue(mandantId,t42_key, t42_value, userId1,
//				 changedDate);
//	}
//	
//	@Transactional
//	public  void updatepriceCheck1(String mandantId, String instrument, String instrument_name
//			,String UserId,String changedDate)
//	{
//		System.out.println(mandantId);
//		System.out.println(mandantId);
//		System.out.println(instrument);
//		
//		mandantRepository.updatepriceCheck1(mandantId,instrument,instrument_name,UserId,changedDate);
//	}
//	
//	@Transactional
//	public void updateCommentsService(String userName,String mandant,String JobId,String Trade, String Result, String Comment) {
//		System.out.println(userName+mandant+JobId+Trade+Result+Comment+"))))))))))))------");
//		mandantRepository.updateCommentDao(userName,mandant,JobId,Trade,Result,Comment);			
//		}
//	
//	@Transactional
//	public void updateFileForCommentsService(int FileId,String mandant,String JobId,String Trade) {
//		System.out.println(FileId+"-*-*-"+mandant+"-*-*-"+JobId+"-*-*-"+Trade);
//		mandantRepository.updateFileForCommentsDao(FileId,mandant,JobId,Trade);			
//		}
//	 
//	@Transactional
//	public void insertFileForCommentsService(byte[] file,String fileName) 
//	{
//		System.out.println(file.toString()+"--Fileimpl-"+fileName);
//		for(int i=0; i< file.length ; i++) {
//	         System.out.print((char)file[i] +" ");
//	      }
//		mandantRepository.insertFileForCommentsDao(file,fileName);
//	}
//	
//	@Transactional
//	public int getUpdatedFileIDService() 
//	{
//		return mandantRepository.getUpdatedFileIDDao();
//	}
//	
//	@Transactional
//	public byte[] getFile(String mandant,String jobId,String tradeId) 
//	{
//		String mandantName = mandant;
//		String jobValue = jobId;
//		String tradeValue = tradeId;
//		System.out.println(mandantName+"-*-"+jobValue+"=**="+tradeValue);
//		return mandantRepository.getFile(mandantName,jobValue,tradeValue);
//	}
//	
//	@Transactional
//	public String getFileStatus(String mandant,String jobId,String tradeId) 
//	{
//		String mandantName = mandant;
//		String jobValue = jobId;
//		String tradeValue = tradeId;
//		System.out.println(mandantName+"-*-"+jobValue+"=**="+tradeValue);
//		return mandantRepository.getFileStatus(mandantName,jobValue,tradeValue);
//	}
//	
//	@Transactional
//	public byte[] getFile1() 
//	{
//		return mandantRepository.getFile1();
//	}
//	
//	@Transactional
//	public Optional<List<Object[]>> getFileName(String mandant,String jobId,String tradeId) 
//	{
//		System.out.println(mandant+"-=-="+jobId+tradeId);
//		return mandantRepository.getFileName(mandant,jobId,tradeId);
//	}
}
