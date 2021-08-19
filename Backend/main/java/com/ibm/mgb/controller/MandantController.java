package com.ibm.mgb.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.mgb.exception.MandantNotFoundException;
import com.ibm.mgb.model.Configuration;
import com.ibm.mgb.model.Instrument;
import com.ibm.mgb.model.LocalReport;
//import com.ibm.mgb.service.AdministrationService;
import com.ibm.mgb.service.MandantService;
import com.ibm.mgb.util.MGBUtils;
import com.ibm.mgb.util.MgbContants;

import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.finder.EmployeeSearchParams;
import de.westlb.mgb.persistence.PersistenceException;

@CrossOrigin(origins=MgbContants.server_path)
@RestController
public class MandantController extends MgbController{
	
	private static final Logger logger = Logger.getLogger(MandantController.class);
	private static String userName1 = "IBM0330";
	
	@Autowired
	private MandantService mandantService;
	
	
	
	@GetMapping("/Users/{userId}/Mandants")
	public ResponseEntity<Optional<List<MandantImpl>>> retrieveMandant(@PathVariable String userId) {
		String userName = userId;
		System.out.println("###Username : "+ userName);
		EmployeeSearchParams param = new EmployeeSearchParams();
		System.out.println("##EmployeeSearchParams :"+param);
        param.setNtId(userId.toLowerCase());
        List<MandantImpl> mandantList = null;
        
		try {
			mandantList = mandantService.retrieveAllMandantService(param);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		int i = 0;
		List names = new ArrayList ();
		MandantImpl mandant;
        while (i < mandantList.size()) {
        	mandant = (MandantImpl)mandantList.get(i);
        	names.add( mandant.getName());
            i++;
        }
        return new ResponseEntity(names, HttpStatus.OK);
	}
	
	/*@GetMapping("/Users/{userId}/Mandants")
	public ResponseEntity<Optional<List<T01Employee>>> retrieveMandant(@PathVariable String userId) {
		String userName = userId;
		Optional<List<T01Employee>> mandantList = mandantService.retrieveAllMandantService(userName);
		if(!mandantList.isPresent())
			throw new UserNotFoundException("Invalid User ID! " + userName);
		return new ResponseEntity<Optional<List<T01Employee>>>(mandantList, HttpStatus.OK);
	}*/
	
	@GetMapping("/Users/{userId}/GetEAAMandants")
	public ResponseEntity<Optional<List<Object[]>>> retrieveEAAMandants(@PathVariable String userId) {
		logger.info("Retrieving EAA Mandants");
		String userName = userId;
		Optional<List<Object[]>> eaaMandants = mandantService.getEAAMandantService(userName);
		if(!eaaMandants.isPresent())
			return null;
//			throw new UserNotFoundException("EAA Mandants not found for the user: " + userName + " or Invalid User!!");
		else
			return new ResponseEntity<Optional<List<Object[]>>>(eaaMandants, HttpStatus.OK);
	}
	
	@GetMapping("/Users/{userId}/GetCBBMandants")
	public ResponseEntity<Optional<List<Object[]>>> retrieveCBBMandants(@PathVariable String userId) {
		logger.info("Retrieving CBB Mandants");
		String userName = userId;
		Optional<List<Object[]>> cbbMandants = mandantService.getCBBMandantService(userName);
		if(!cbbMandants.isPresent())
			return null;
//			throw new UserNotFoundException("CBB Mandants not found for the user: " + userName + " or Invalid User!!");
		return new ResponseEntity<Optional<List<Object[]>>>(cbbMandants, HttpStatus.OK);
	}
	@GetMapping("/ReportLocations")
	public List<String> retrieveReportLocations() {
			
			return mandantService.retrieveReportLocations();
	}
	
	
	  @PostMapping("/localreport")
	    void addUser(@RequestBody LocalReport user) {
		  System.out.println(user);
		  
		  LocalReport obj=new  LocalReport();
		  obj.setMarketdata(user.getMarketdata());
		  obj.setDatefilter(user.getDatefilter());
		  obj.setDatefrom(user.getDatefrom());
		  obj.setDateto(user.getDateto());
		  obj.setReportLocation(user.getReportLocation());
		  obj.setReporttype(user.getReporttype());
		  
	       System.out.println(obj.getMarketdata());
	      System.out.println(obj.getDatefilter());
	      System.out.println(obj.getReportLocation());
	      System.out.println(obj.getReporttype());
	      System.out.println(obj.getDatefrom());
	      System.out.println(obj.getDateto());;
	     
	      
	      
	    }
	  @PostMapping("/Instrument/{mandant}/{UserId}")
		public void updateInstrument(@PathVariable String mandant,@PathVariable String UserId,@RequestBody List<Instrument> priceCheck)
		{
		  String mandantName = mandant;			
		  String mandantId = MGBUtils.getMandant(mandantName);
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss SSS ");
		   LocalDateTime changedDate = LocalDateTime.now();
		   System.out.println(dtf.format(changedDate)+"HELLo");
//		   String changedDate1=dtf.format(changedDate);
//		  SimpleDateFormat formatter = new SimpleDateFormat(" dd MMM yyyy");
//		  Date date = new Date();
//			String changedDate = formatter.format(date);
//			System.out.println("Date Format with E, dd MMM yyyy HH:mm:ss z : "+ changedDate);
			System.out.println(priceCheck);
//			Instrument instrument=new Instrument();
			 String UserId1=UserId;
			 for (Instrument instrument : priceCheck) {
				
		            System.out.println(instrument.getT05_instrument());
		            System.out.println(instrument.getT05_instrument_name());
		            System.out.println(instrument.getT11_name());	
		       
		       
		        
	     mandantService.updatepriceCheck(mandantId,instrument.getT05_instrument(),instrument.getT05_instrument_name(),instrument.getT11_name(),UserId1,dtf.format(changedDate));
		//mandantService.updatepriceCheck1(mandantId,instrument.getT05_instrument(),instrument.getT05_instrument_name(),UserId,changedDate);
		          
		        }			   
			
			System.out.print(UserId);
		
			System.out.println(mandantId);
			
			
			
//			obj.setT05_instrument(priceCheck.getT05_instrument());
//			obj.setT05_instrument_name(priceCheck.getT05_instrument_name());
//			obj.setT11_name(priceCheck.getT11_name());
//			System.out.println(obj.getT05_instrument());
//			System.out.println(obj.getT05_instrument_name());
//			System.out.println(obj.getT11_name());
			
			
//			instrument=obj.getT05_instrument();
//			instrument_name=obj.getT05_instrument_name();
//			t11_name=obj.getT11_name();
			
			
				
//			 mandantService.updatepriceCheck(mandantId,obj.getT05_instrument(),obj.getT05_instrument_name(),obj.getT11_name()); 
	 //AdministrationService.updatepriceCheck(mandantId,instrument,instrument_name,t11_name);
			
			
			
		}
	  @PostMapping("/Configuration/{mandant}/{UserId}")
			public void updateConfiguration(@PathVariable String mandant,@PathVariable String UserId,@RequestBody List<Configuration> rowdata)
			{
			  String mandantName = mandant;			
			  String mandantId = MGBUtils.getMandant(mandantName);
			  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:S ");
			   LocalDateTime changedDate = LocalDateTime.now();
			   System.out.println(dtf.format(changedDate)+"HELLo");
//			  SimpleDateFormat formatter = new SimpleDateFormat(" dd MMM yyyy HH:mm:ss");
//			  Date date = new Date();
//				String changedDate = formatter.format(date);
				System.out.println("Date Format with E, dd MMM yyyy HH:mm:ss  : "+ dtf.format(changedDate));
				System.out.println(rowdata);
//				Instrument instrument=new Instrument();
				System.out.println(changedDate);
				 String UserId1=UserId;
				 for (Configuration configuration:rowdata) {
					
			            System.out.println(configuration.getKey());
			            System.out.println(configuration.getValue());
			           // System.out.println(configuration.);	
			       
			       
			mandantService.updateConfigurationValue(mandantId,configuration.getKey(),configuration.getValue(),UserId1,dtf.format(changedDate));
			//mandantService.updatepriceCheck1(mandantId,instrument.getT05_instrument(),instrument.getT05_instrument_name(),UserId,changedDate);
			          
			        }}
	
	@GetMapping("/Users/{userId}/GetPAGMandants")
	public ResponseEntity<Optional<List<Object[]>>> retrievePagMandants(@PathVariable String userId) {
		logger.info("Retrieving PAG Mandants");
		String userName = userId;
		Optional<List<Object[]>> pagMandants = mandantService.getPAGMandantService(userName);
		System.out.println(pagMandants+"-------");
		if(!pagMandants.isPresent())
			return null;
//			throw new UserNotFoundException("PAG Mandants not found for the user: " + userName + " or Invalid User!!");
		else
		return new ResponseEntity<Optional<List<Object[]>>>(pagMandants, HttpStatus.OK);
	}
		
	@GetMapping("/Mandants/{mandant}/OpenJobs")
	public ResponseEntity<Optional<List<Object[]>>> retrieveOpenJob(@PathVariable String mandant) {
		String mandantName=mandant;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		Optional<List<Object[]>> openJobList =  mandantService.getOpenJobService(mandantId);
		if(!openJobList.isPresent()) 
			return null;
//			throw new MandantNotFoundException("No open Jobs found for the mandant: " + mandantName);
		return new ResponseEntity<Optional<List<Object[]>>>(openJobList, HttpStatus.OK);
	}

	@GetMapping("/Mandants/{mandant}/OpenTrades")
	public ResponseEntity<Optional<List<Object[]>>> retrieveOpenTrade(@PathVariable String mandant){
		String mandantName=mandant;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		Optional<List<Object[]>> openTradeList =  mandantService.getOpenTradeService(mandantId);
		if(!openTradeList.isPresent())
			return null;
//			throw new MandantNotFoundException("No open trades found for the mandant: " + mandantName);
		return new ResponseEntity<Optional<List<Object[]>>>(openTradeList, HttpStatus.OK);
	}

	@GetMapping("/Mandants/{mandant}/AllTrades")
	public ResponseEntity<Optional<List<Object[]>>> retrieveSearchTrades(@PathVariable String mandant) {
		String mandantName = mandant;
		String mandantId = MGBUtils.getMandant(mandantName);
		System.out.println(mandant+mandantName+mandantId);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		Optional<List<Object[]>> searchTrades =  mandantService.getSearchTradeService(mandantId);
		if(!searchTrades.isPresent())
			return null;
//			throw new MandantNotFoundException("No trades found for the mandant: " + mandantName);
		return new ResponseEntity<Optional<List<Object[]>>>(searchTrades, HttpStatus.OK);
	}
	
	@GetMapping("/Mandants/{mandant}/Job")
	public ResponseEntity<Optional<List<Object[]>>> retrieveJobByMandant(@PathVariable String mandant) {
		String mandantName = mandant;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		Optional<List<Object[]>> jobList = mandantService.getJobByMandantService(mandantId);	
		if(!jobList.isPresent())
			return null;
//			throw new MandantNotFoundException("No Jobs found for the mandant: " + mandantName);
		return new ResponseEntity<Optional<List<Object[]>>>(jobList, HttpStatus.OK);
	}
	
	@GetMapping("/Mandants/{mandant}/Jobs")	
	public List<Object[]> getSelectedDateJobs(@PathVariable String mandant,@RequestParam("FromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate FromDate,@RequestParam("ToDate")  @DateTimeFormat(pattern = "yyyy-MM-dd")   LocalDate ToDate )
	{
		 System.out.println(FromDate);
		  System.out.println(ToDate);
		  System.out.println(mandant);
		System.out.println("JobList"+mandant);
		String Client=new String();
		String man=mandant;
		//		 DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		//		 LocalDateTime  From_Date=LocalDateTime.parse(FromDate, formatter);
		System.out.println(FromDate);
		System.out.println(ToDate);
		System.out.println("-----------------------"+Client+"====================");
		List<Object[]> obj =  mandantService.getSelectedDateJobsService(man,FromDate,ToDate);
		
		return obj;
		//return mandantService.retrieveAllInstruments();
	}
	
@GetMapping("/Mandants/{mandant}/Trade")
	
	public List<Object[]> getSelectedDateTrades(@PathVariable String mandant,@RequestParam("FromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate FromDate,@RequestParam("ToDate")  @DateTimeFormat(pattern = "yyyy-MM-dd")   LocalDate ToDate )
	{
		String mandantName = mandant;
		
		String mandantId = MGBUtils.getMandant(mandantName);
		 System.out.println(FromDate);
		  System.out.println(ToDate);
		  System.out.println(mandantId);
		//		 DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		//		 LocalDateTime  From_Date=LocalDateTime.parse(FromDate, formatter);
		System.out.println(FromDate);
		System.out.println(ToDate);

		List<Object[]> obj =  mandantService.getSelectedDateTradesService(mandantId,FromDate,ToDate);
		
		return obj;
		//return mandantService.retrieveAllInstruments();
	}
	
	@GetMapping("/Mandants/{mandant}/Job/{jobId}/TradeList")
	public ResponseEntity<Optional<List<Object[]>>> retreiveTradeByJobId(@PathVariable String mandant, @PathVariable String jobId) {
		String mandantName = mandant;
		String jobValue = jobId;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		Optional<List<Object[]>> tradeList = mandantService.getTradeByJobIdService(mandantId, jobValue);
		if(!tradeList.isPresent())
			return null;
//			throw new MandantNotFoundException("No Trades found for the mandant: " + mandantName + " and job: " + jobValue + " or Invalid details!!");
		return new ResponseEntity<Optional<List<Object[]>>>(tradeList, HttpStatus.OK);
	}
	
//	@GetMapping("/Mandants/{mandant}/Trade/{tradeId}/Overview")
//	public ResponseEntity<List<Object[]>> retrieveTradeOverviewNOJobID(@PathVariable String mandant, @PathVariable String tradeId){
//		String mandantName = mandant;
//		String tradeValue = tradeId;
//			String mandantId = MGBUtils.getMandant(mandantName);
//			System.out.println("hello------");
//			if(mandantId.isEmpty())
//				throw new MandantNotFoundException("Invalid Mandants!!");
//			List<Object[]> overview = new ArrayList<Object[]>();
//			List<Object[]> trade = new ArrayList<Object[]>();	
//			trade = mandantService.getOverviewTradeNOJobIdService(mandantId, tradeValue);
////			if(trade.isEmpty())
////				throw new MandantNotFoundException("No overview trade details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
//			List<Object[]> marketData = new ArrayList<Object[]>();
////			marketData = mandantService.getOverviewMarketDataService(mandantId, jobValue, tradeValue);
////			if(marketData.isEmpty())
////				throw new MandantNotFoundException("No overview market data details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
//			List<Object[]> checkResult = new ArrayList<Object[]>();
////			checkResult = mandantService.getOverviewCheckResultService(mandantId, jobValue, tradeValue);
//			System.out.println(checkResult+"---");
////			if(checkResult.isEmpty())
////				throw new MandantNotFoundException("No overview check result details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
//		
//			overview.addAll(trade);
//			overview.addAll(marketData);
//			overview.addAll(checkResult);
//			
//			//if(overview.isEmpty())
//				//throw new MandantNotFoundException("No Trades found for the mandant: " + mandantName + "and job: " + jobId1);
//			return new ResponseEntity<List<Object[]>>(overview, HttpStatus.OK);
//			
//			
//		
//	}
	
	@GetMapping("/Mandants/{mandant}/Job/{jobId}/Trade/{tradeId}/Overview")
	public ResponseEntity<List<Object[]>> retrieveTradeOverview(@PathVariable String mandant, @PathVariable String jobId, @PathVariable String tradeId){
		String mandantName = mandant;
		String jobValue = jobId;
		String tradeValue = tradeId;
		if(mandantName.equals("Bond EAA") || mandantName.equals("Bonds CBB") || mandantName.equals("Bond PAG")) {
			String mandantId = MGBUtils.getMandant(mandantName);
			System.out.println("hello------");
			if(mandantId.isEmpty())
				throw new MandantNotFoundException("Invalid Mandants!!");
			List<Object[]> overview = new ArrayList<Object[]>();
			List<Object[]> trade = new ArrayList<Object[]>();	
			trade = mandantService.getOverviewTradeService(mandantId, jobValue, tradeValue);
//			if(trade.isEmpty())
//				throw new MandantNotFoundException("No overview trade details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> marketData = new ArrayList<Object[]>();
			marketData = mandantService.getOverviewMarketDataService(mandantId, jobValue, tradeValue);
//			if(marketData.isEmpty())
//				throw new MandantNotFoundException("No overview market data details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> checkResult = new ArrayList<Object[]>();
			checkResult = mandantService.getOverviewCheckResultService(mandantId, jobValue, tradeValue);
			System.out.println(checkResult+"---");
//			if(checkResult.isEmpty())
//				throw new MandantNotFoundException("No overview check result details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
		
			overview.addAll(trade);
			overview.addAll(marketData);
			overview.addAll(checkResult);
			
			//if(overview.isEmpty())
				//throw new MandantNotFoundException("No Trades found for the mandant: " + mandantName + "and job: " + jobId1);
			return new ResponseEntity<List<Object[]>>(overview, HttpStatus.OK);
			
			
		}
		
		else if(mandantName.equals("Derivate EAA") || mandantName.equals("Derivative CBB") || mandantName.equals("Derivative PAG")) {
			System.out.println("into derivative loop----------------------------------------------------------------");
			String mandantId = MGBUtils.getMandant(mandantName);
			if(mandantId.isEmpty())
				throw new MandantNotFoundException("Invalid Mandants!!");
			List<Object[]> overview = new ArrayList<Object[]>();
			List<Object[]> trade = new ArrayList<Object[]>();	
			trade = mandantService.getDerivativeOverviewTradeService(mandantId, jobValue, tradeValue);
//			if(trade.isEmpty())
//				throw new MandantNotFoundException("No overview trade details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> marketData = new ArrayList<Object[]>();		
			marketData = mandantService.getDerivativeOverviewMarketDataService(mandantId, jobValue, tradeValue);
//			if(marketData.isEmpty())
//				throw new MandantNotFoundException("No overview market data details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> checkResult = new ArrayList<Object[]>();	
			checkResult = mandantService.getDerivativeOverviewCheckResultService(mandantId, jobValue, tradeValue);
//			if(checkResult.isEmpty())
//			{
//				checkResult.add(null);
//				throw new MandantNotFoundException("No overview check result details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
//			}
				System.out.println(trade+"--+--"+checkResult);
//			List<Object[]> assetsData =new ArrayList<Object[]>();
//			assetsData=mandantService.getOverviewAssetsDataService(mandantId, jobValue, tradeValue);
			overview.addAll(trade);
			overview.addAll(marketData);
			overview.addAll(checkResult);
//			overview.addAll(assetsData);
			
			return new ResponseEntity<List<Object[]>>(overview, HttpStatus.OK);
			}
		
		if(mandantName.equals("MoneyMarket EAA") || mandantName.equals("MoneyMarket CBB") || mandantName.equals("MoneyMarket PAG")) {
			String mandantId = MGBUtils.getMandant(mandantName);
			List<Object[]> overview = new ArrayList<Object[]>();
			List<Object[]> trade = new ArrayList<Object[]>();	
			trade = mandantService.getOverviewTradeService(mandantId, jobValue, tradeValue);
//			if(trade.isEmpty())
//				throw new MandantNotFoundException("No overview trade details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> marketData = new ArrayList<Object[]>();
			marketData = mandantService.getOverviewMarketDataService(mandantId, jobValue, tradeValue);
//			if(marketData.isEmpty())
//				throw new MandantNotFoundException("No overview market data details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> checkResult = new ArrayList<Object[]>();
			checkResult = mandantService.getOverviewCheckResultService(mandantId, jobValue, tradeValue);
//			if(checkResult.isEmpty())
//				throw new MandantNotFoundException("No overview check result details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
		
			overview.addAll(trade);
			overview.addAll(marketData);
			overview.addAll(checkResult);
			
			//if(overview.isEmpty())
				//throw new MandantNotFoundException("No Trades found for the mandant: " + mandantName + "and job: " + jobId1);
			return new ResponseEntity<List<Object[]>>(overview, HttpStatus.OK);
		}
		if(mandantName.equals("Repo EAA") || mandantName.equals("Repo CBB") || mandantName.equals("Repo PAG")) {
			String mandantId = MGBUtils.getMandant(mandantName);
			if(mandantId.isEmpty())
				throw new MandantNotFoundException("Invalid Mandants!!");
			List<Object[]> overview = new ArrayList<Object[]>();
			List<Object[]> trade = new ArrayList<Object[]>();	
			trade = mandantService.getOverviewTradeService(mandantId, jobValue, tradeValue);
//			if(trade.isEmpty())
//				throw new MandantNotFoundException("No overview trade details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> marketData = new ArrayList<Object[]>();
			marketData = mandantService.getOverviewMarketDataService(mandantId, jobValue, tradeValue);
//			if(marketData.isEmpty())
//				throw new MandantNotFoundException("No overview market data details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
			List<Object[]> checkResult = new ArrayList<Object[]>();
			checkResult = mandantService.getOverviewCheckResultService(mandantId, jobValue, tradeValue);
//			if(checkResult.isEmpty())
//				throw new MandantNotFoundException("No overview check result details found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
		
			overview.addAll(trade);
			overview.addAll(marketData);
			overview.addAll(checkResult);
			
			//if(overview.isEmpty())
				//throw new MandantNotFoundException("No Trades found for the mandant: " + mandantName + "and job: " + jobId1);
			return new ResponseEntity<List<Object[]>>(overview, HttpStatus.OK);
			}
		else {
			return null;
			}
	}
	
	@GetMapping("/Mandants/{mandant}/Job/{jobId}/Trade/{tradeId}/AssetsData")
	public ResponseEntity<Optional<List<Object[]>>> retrieveTradeOverviewAssetsData(@PathVariable String mandant,@PathVariable String jobId,@PathVariable String tradeId){
		String mandantName = mandant;
		String jobValue = jobId;
		String tradeValue = tradeId;
		String mandantId = MGBUtils.getMandant(mandantName);
		Optional<List<Object[]>> assetsData = mandantService.getOverviewAssetsDataService(mandantId, jobValue, tradeValue);
		if(!assetsData.isPresent())
			return null;
//			throw new MandantNotFoundException("No assets data found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
	return new ResponseEntity<Optional<List<Object[]>>>(assetsData, HttpStatus.OK);
	}
	
//	@GetMapping("/Mandants/{mandant}/Trade/{tradeId}/AssetsData")
//	public ResponseEntity<Optional<List<Object[]>>> retrieveTradeOverviewAssetsData(@PathVariable String mandant,@PathVariable String tradeId){
//		String mandantName = mandant;
//		String tradeValue = tradeId;
//		String mandantId = MGBUtils.getMandant(mandantName);
//		Optional<List<Object[]>> assetsData = mandantService.getOverviewAssetsDataService1(mandantId, tradeValue);
//		if(!assetsData.isPresent())
//			throw new MandantNotFoundException("No assets data found for the mandant: " + mandantName +  " and trade: " + tradeValue + " or Invalid details!!");
//	return new ResponseEntity<Optional<List<Object[]>>>(assetsData, HttpStatus.OK);
//	}
	
	@GetMapping("/Mandants/{mandant}/Job/{jobId}/Trade/{tradeId}/History")
	public ResponseEntity<Optional<List<Object[]>>> retrieveTradeHistory(@PathVariable String mandant, @PathVariable String jobId, @PathVariable String tradeId) {
		String mandantName = mandant;
		String jobValue = jobId;
		String tradeValue = tradeId;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		Optional<List<Object[]>> historyList = mandantService.getTradeHistoryService(mandantId, jobValue, tradeValue);
		if(!historyList.isPresent())
			return null;
//			throw new MandantNotFoundException("No history found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
		return new ResponseEntity<Optional<List<Object[]>>>(historyList, HttpStatus.OK);
	}
	
//	@GetMapping("/Mandants/{mandant}/Trade/{tradeId}/History")
//	public ResponseEntity<Optional<List<Object[]>>> retrieveTradeHistoryNOJobId(@PathVariable String mandant,  @PathVariable String tradeId) {
//		String mandantName = mandant;
//		String tradeValue = tradeId;
//		String mandantId = MGBUtils.getMandant(mandantName);
//		if(mandantId.isEmpty())
//			throw new MandantNotFoundException("Invalid Mandants!!");
//		Optional<List<Object[]>> historyList = mandantService.getTradeHistoryNOJobIdService(mandantId, tradeValue);
//		if(!historyList.isPresent())
//			throw new MandantNotFoundException("No history found for the mandant1: " + mandantName + " and trade1: " + tradeValue + " or Invalid details!!");
//		return new ResponseEntity<Optional<List<Object[]>>>(historyList, HttpStatus.OK);
//	}
	
	@GetMapping("/Mandants/{mandant}/Job/{jobId}/Trade/{tradeId}/StornoGroup")
	public ResponseEntity<Optional<List<Object[]>>> retrieveDerivativeStornoGroup(@PathVariable String mandant,@PathVariable String jobId,@PathVariable String tradeId){
		String mandantName = mandant;
		String jobValue = jobId;
		String tradeValue = tradeId;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		Optional<List<Object[]>> stornoGroup = mandantService.getDerivativeStornoGroupService(mandantId, jobValue, tradeValue);
		if(!stornoGroup.isPresent())
			return null;
//			throw new MandantNotFoundException("No storno group found for the mandant: " + mandantName + " and job: " + jobValue + " and trade: " + tradeValue + " or Invalid details!!");
		return new ResponseEntity<Optional<List<Object[]>>>(stornoGroup, HttpStatus.OK);
	}
	
//	@GetMapping("/Mandants/{mandant}/Trade/{tradeId}/StornoGroup")
//	public ResponseEntity<Optional<List<Object[]>>> retrieveDerivativeStornoGroupNOJobId(@PathVariable String mandant,@PathVariable String tradeId){
//		String mandantName = mandant;
//		String tradeValue = tradeId;
//		String mandantId = MGBUtils.getMandant(mandantName);
//		if(mandantId.isEmpty())
//			throw new MandantNotFoundException("Invalid Mandants!!");
//		Optional<List<Object[]>> stornoGroup = mandantService.getDerivativeStornoGroupNOJobIdService(mandantId,tradeValue);
//		if(!stornoGroup.isPresent())
//			throw new MandantNotFoundException("No storno group found for the mandant: " + mandantName + " and trade: " + tradeValue + " or Invalid details!!");
//		return new ResponseEntity<Optional<List<Object[]>>>(stornoGroup, HttpStatus.OK);
//	}
	
//	@GetMapping("/Mandants/{mandant}/Job/{jobId}/{SelectedTrades}/{ResultAndComment}")
//	public String commentTrade(@PathVariable String mandant, @PathVariable String jobId, 
//						@PathVariable ArrayList<String>[] SelectedTrades,
//						@PathVariable String[] ResultAndComment) {
//		//select * from t10_state_Code where fk_t10_t09_state_mandant = 'BND' and t10_state_type = 'manual' and t10_enabled = 'Y'
//		String result = "";
//		for(int i=0;i<SelectedTrades.length;i++) { 
//				System.out.println("---Trades--"+SelectedTrades[i]);
//		}
//		result = ResultAndComment[1];
//		System.out.println(mandant+"-----"+"---"+jobId+"--"+ResultAndComment[0]+"--"+ResultAndComment[1]);
//		return "Comments Updated";
//	}
	
	
	@GetMapping("/Mandants/{mandant}/{jobId}/{SelectedTrades}/Result")
	public void commentandResult(@PathVariable String mandant, @PathVariable String[] jobId, 
						@PathVariable String[] SelectedTrades,@RequestParam("ResultandComment") String[] ResultAndComment) {
		boolean x=false;
		String mandantName=mandant;
		String result = "";
		String comment = "";		
		String mandantId = MGBUtils.getMandant(mandantName);
		result = ResultAndComment[1];
		comment = ResultAndComment[0];
		System.out.println(jobId.length);
			if(jobId.length==1) {
				for(int i=0;i<SelectedTrades.length;i++) { 
					String Trade="";
					Trade=(String)SelectedTrades[i];
					mandantService.updateCommentsService(userName1,mandantId,jobId[i],SelectedTrades[i],result,comment);
					System.out.println(mandantId+"-----T"+"---"+jobId[i]+"--"+result+"--"+comment+"---"+SelectedTrades[i]);
				}
			}
			else
			{
				for(int i=0;i<SelectedTrades.length;i++) { 
					String Trade="";
					Trade=(String)SelectedTrades[i];
					System.out.println(mandantId+"-----"+"---"+jobId[i]+"--"+result+"--"+comment+"---"+SelectedTrades[i]);
					mandantService.updateCommentsService(userName1,mandantId,jobId[i],SelectedTrades[i],result,comment);
				}
			}				
		
		}
	
	
	
	public Object[] comment;
	@PostMapping("Mandants/updateComment/Overview")
    void addUser(@RequestBody Object[] updateOverview) {
		comment=updateOverview;
		System.out.println(updateOverview[0].toString());
		String jobId=updateOverview[0].toString();
		String tradeId=updateOverview[1].toString();
		String Result=updateOverview[2].toString();
		String Comment=updateOverview[3].toString();
		String mandantId=MGBUtils.getMandant(updateOverview[4].toString());
		String userID=updateOverview[5].toString();
		System.out.println(jobId+tradeId+Result+Comment+mandantId+"****"+userID+userID.substring(1, userID.length() - 1));
		mandantService.updateCommentsService(userID.substring(1, userID.length() - 1),mandantId,jobId,tradeId,Result,Comment);
	}
public Object[][] comments;
	@PostMapping("Mandants/updateComment")
    void addUser(@RequestBody Object[][] update) throws IOException{
		comments=update;
		System.out.println(update[0][0].toString()+"--"+update[1][0].toString()+"--"+update[2][0].toString()
				+"--"+update[3][0].toString()+"--"+update[4][0].toString()+"--"+update[5][0].toString());
		String Result=update[2][0].toString();
		String Comment=update[3][0].toString();
		String mandantId = MGBUtils.getMandant(update[4][0].toString());
		String LoggedInUser = update[5][0].toString();
		List tradeId=new ArrayList();
		List jobId=new ArrayList();
		for(int i=0;i<update[0].length;i++)
		{
//			System.out.println(update[0][i].toString()+"--"+update[1][i].toString());
			tradeId.add(update[0][i].toString());
			jobId.add(update[1][i].toString());
		}
		
		System.out.println(tradeId.toString()+"--"+jobId.toString()+"=="+Result+"-="+Comment+"=-"+mandantId);
		if(jobId.size()==1) {
//			System.out.println(uploadedFile+"---"+uploadedFile.getBytes()+"---"+uploadedFile.getOriginalFilename());
			for(int i=0;i<jobId.size();i++) { 
				System.out.println(jobId.get(i).toString()+"-updateComment--"+tradeId.get(i).toString());
				mandantService.updateCommentsService(LoggedInUser,mandantId,jobId.get(0).toString(),tradeId.get(0).toString(),Result,Comment);
			}
		}
		else
		{
			for(int i=0;i<jobId.size();i++) { 
				System.out.println(jobId.get(i).toString()+"==*="+tradeId.get(i).toString());
				mandantService.updateCommentsService(userName1,mandantId,jobId.get(i).toString(),tradeId.get(i).toString(),Result,Comment);
			}
		}
	}
	
	@PostMapping("Mandants/FileUpload")
    void uploadFile(@RequestParam("File") MultipartFile file) throws IOException {
		String fileName="";
		MultipartFile file1=file;
		fileName=file1.getOriginalFilename();
		mandantService.insertFileForCommentsService(file1.getBytes(),fileName);
		
		String mandantId = MGBUtils.getMandant(comments[4][0].toString());
		List tradeId=new ArrayList();
		List jobId=new ArrayList();
		for(int i=0;i<comments[0].length;i++)
		{
//			System.out.println(comments[0][i].toString()+"-uploadFile-"+comments[1][i].toString());
			tradeId.add(comments[0][i].toString());
			jobId.add(comments[1][i].toString());
		}		
		System.out.println(file+"---" +file.getName()+"---" +file.getOriginalFilename()+"---" + file.getBytes()+"---" + file.getContentType()+"---" + file.getBytes().length);
		System.out.println("File Updated in database");
			int updatedFileID=mandantService.getUpdatedFileIDService();
			System.out.println(updatedFileID+"==updated Fie ID");
		if(jobId.size()==1) {
			for(int i=0;i<jobId.size();i++) { 
				System.out.println(jobId.get(0).toString()+"---"+tradeId.get(0).toString());
				mandantService.updateFileForCommentsService(updatedFileID,mandantId,jobId.get(0).toString(),tradeId.get(0).toString());
			} 
		}
		else
		{ 
			for(int i=0;i<jobId.size();i++) { 
				System.out.println(jobId.get(i).toString()+"==="+tradeId.get(i).toString());
				mandantService.updateFileForCommentsService(updatedFileID,mandantId,jobId.get(i).toString(),tradeId.get(i).toString());

			}
		}
	}
	@PostMapping("Mandants/FileUploadOverview")
    void uploadFileOverview(@RequestParam("File") MultipartFile file) throws IOException {
		String fileName="";
		MultipartFile file1=file;
		fileName=file1.getOriginalFilename();
		mandantService.insertFileForCommentsService(file1.getBytes(),fileName);
			int updatedFileID=mandantService.getUpdatedFileIDService();
			System.out.println(updatedFileID+"==updated Fie ID");
			String jobId=comment[0].toString();
			String tradeId=comment[1].toString();
			String Result=comment[2].toString();
			String Comment=comment[3].toString();
			String mandantId=MGBUtils.getMandant(comment[4].toString());
			String userID=comment[5].toString();
			System.out.println(jobId+tradeId+Result+Comment+mandantId+"++++"+userID+userID.substring(1, userID.length() - 1));
//			mandantService.updateCommentsService(userID.substring(1, userID.length() - 1),mandantId,jobId,tradeId,Result,Comment);
			mandantService.updateFileForCommentsService(updatedFileID,mandantId,jobId,tradeId);
		
//		BufferedReader br;
//		List<String> result = new ArrayList<>();
//		try {
//
//		     String line;
//		     InputStream is = file.getInputStream();
//		     br = new BufferedReader(new InputStreamReader(is));
//		     while ((line = br.readLine()) != null) {
//		          result.add(line);
//		     }
//
//		  } catch (IOException e) {
//		    System.err.println(e.getMessage());       
//		  }
//		mandantService.updateFileForCommentsService(file1.getBytes(),fileName);
//		System.out.println(result);
	}
	
	@GetMapping("/Mandants/{mandant}/{jobid}/{tradeid}/getFile")
	public byte[] retrieveEAAMandants1(@PathVariable String mandant,@PathVariable String jobid,@PathVariable String tradeid,
			HttpServletRequest request, HttpServletResponse response)throws IOException {
		String mandantName = mandant;
		String jobValue = jobid;
		String tradeValue = tradeid;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		System.out.println(mandantId+"--"+jobValue+"=="+tradeValue);
//		String fileId=mandantService.getFile(mandantId,jobValue,tradeValue); 
//		System.out.println(fileId);
		byte[] f1=mandantService.getFile(mandantId,jobValue,tradeValue);
//		File f=new File(f1.getName());
		System.out.println(f1);
		for(int i=0; i< f1.length ; i++) {
//			System.out.println("filedata");
//	         System.out.print((char)f1[i] +" ");
	      }
//		OutputStream os = response.getOutputStream();
        
		return f1;
	}
	
	@GetMapping("/Mandants/{mandant}/{jobid}/{tradeid}/getFileStatus")
	public boolean retrieveFileStatus(@PathVariable String mandant,@PathVariable String jobid,@PathVariable String tradeid,
			HttpServletRequest request, HttpServletResponse response)throws IOException {
		String mandantName = mandant;
		String jobValue = jobid;
		String tradeValue = tradeid;
		String mandantId = MGBUtils.getMandant(mandantName);
		if(mandantId.isEmpty())
			throw new MandantNotFoundException("Invalid Mandants!!");
		System.out.println(mandantId+"--"+jobValue+"=="+tradeValue);
//		String fileId=mandantService.getFile(mandantId,jobValue,tradeValue); 
//		System.out.println(fileId);
		String f1=mandantService.getFileStatus(mandantId,jobValue,tradeValue);
		boolean file=false;
		if(f1 != null)
		{
			file=true;        
		return file;
		}
		else
			return false;
	}
		@GetMapping("/Mandants/{mandant}/{jobid}/{tradeid}/getFileName")
		public ResponseEntity<Optional<List<Object[]>>> getFileName(@PathVariable String mandant,@PathVariable String jobid,@PathVariable String tradeid,HttpServletRequest request, HttpServletResponse response)throws IOException {
//			byte[] f1=mandantService.getFile1(); 
			String mandantName = mandant;
			String jobValue = jobid;
			String tradeValue = tradeid;
			String mandantId = MGBUtils.getMandant(mandantName);
			if(mandantId.isEmpty())
				throw new MandantNotFoundException("Invalid Mandants!!");
			System.out.println(mandantId+"--"+jobValue+"=="+tradeValue);
//			String fileName=mandantService.getFileName(mandantId,jobValue,tradeValue); 
//			System.out.println(fileName);
			
				Optional<List<Object[]>> mandantList = mandantService.getFileName(mandantId,jobValue,tradeValue);
				if(!mandantList.isPresent())
					return null;
//					throw new UserNotFoundException("Invalid User ID! " + userName);
				return new ResponseEntity<Optional<List<Object[]>>>(mandantList, HttpStatus.OK);
//			return fileName;
//			if(fileName!=null)
//				return true;
//			else
//				return false;
		
//		String mimeType = URLConnection.guessContentTypeFromName(f1.getName());
//		if (mimeType == null) {
//			//unknown mimetype so set the mimetype to application/octet-stream
//			mimeType = "application/octet-stream";
//		}
//		response.setContentType(mimeType);
//		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + f.getName() + "\""));
////		 response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + f1.getName() + "\""));
//		response.setContentLength((int) ((File) f1).length());
//
//		InputStream inputStream = new BufferedInputStream(new FileInputStream(f)); 
//
//		FileCopyUtils.copy(inputStream, response.getOutputStream());

	}

}


