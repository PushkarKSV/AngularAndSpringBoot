package com.ibm.mgb.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.ibm.mgb.model.T01Employee;

//@Repository
@EnableJpaRepositories(basePackages = "com.ibm.mgb")
public interface MandantRepository extends JpaRepository<T01Employee,Integer>{
	
	@Query(value="select Distinct t09.t09_id,t09.t09_name,t09.t09_client from t01_employee t01, t17_user_role t17, t09_mandant t09 \r\n" + 
			"where t01.t01_id=t17.fk_t17_t01_employee  \r\n" + 
			"and t09.t09_id=t17.fk_t17_t09_mandant  \r\n" + 
			"and  UPPER(t01.t01_nt_id) =UPPER(:username) order by t09.t09_id",nativeQuery = true)
	public Optional<List<Object[]>> retrieveAllMandantDao(@Param("username") String username);
	
	@Query(value="select distinct T120_REPORT_ID from T120_REPORT_CONFIGURATION",nativeQuery = true)
	public List<String> retrieveReportLocations();
	
	@Query(value="select Distinct t09.t09_id,t09.t09_name,t09.t09_client,t01.t01_nt_id,\r\n" + 
			"(select count(*) from t08_job where T08_status not in ( 'CLOSED') and FK_t08_T09_Mandant = t09.t09_id) as Jobcount,\r\n" + 
			"(select count(*) from t02_trade T where exists (select * from t08_job J where T08_status not in ( 'CLOSED') and T.FK_t02_t08_job = J.t08_id and J.FK_t08_T09_Mandant = t09.t09_id)) as tradecount \r\n" + 
			"from t01_employee t01, t17_user_role t17, t09_mandant t09 \r\n" + 
			"where t01.t01_id=t17.fk_t17_t01_employee and t09.t09_id=t17.fk_t17_t09_mandant and t09.t09_id in('BND','DVD','MMK','REP')\r\n" + 
			"and UPPER(t01.t01_nt_id) =UPPER(:username) and t09.t09_client='EAA' order by t09.t09_id",nativeQuery = true)
	public Optional<List<Object[]>> getEAAMandantDao(@Param("username") String username);
	
	@Query(value="select Distinct t09.t09_id,t09.t09_name,t09.t09_client,t01.t01_nt_id,\r\n" + 
			"(select count(*) from t08_job where T08_status not in ( 'CLOSED') and FK_t08_T09_Mandant = t09.t09_id) as Jobcount,\r\n" + 
			"(select count(*) from t02_trade T where exists (select * from t08_job J where T08_status not in ( 'CLOSED') and T.FK_t02_t08_job = J.t08_id and J.FK_t08_T09_Mandant = t09.t09_id)) as tradecount \r\n" + 
			"from t01_employee t01, t17_user_role t17, t09_mandant t09 \r\n" + 
			"where t01.t01_id=t17.fk_t17_t01_employee and t09.t09_id=t17.fk_t17_t09_mandant and t09.t09_id in('BNC','DVC','MMC','REC')\r\n" + 
			"and UPPER(t01.t01_nt_id) =UPPER(:username) and t09.t09_client='CBB' order by t09.t09_id",nativeQuery = true)
	public Optional<List<Object[]>> getCBBMandantDao(@Param("username") String username);
	
	@Query(value="select Distinct t09.t09_id,t09.t09_name,t09.t09_client,t01.t01_nt_id,\r\n" + 
			"(select count(*) from t08_job where T08_status not in ( 'CLOSED') and FK_t08_T09_Mandant = t09.t09_id) as Jobcount,\r\n" + 
			"(select count(*) from t02_trade T where exists (select * from t08_job J where T08_status not in ( 'CLOSED') and T.FK_t02_t08_job = J.t08_id and J.FK_t08_T09_Mandant = t09.t09_id)) as tradecount \r\n" + 
			"from t01_employee t01, t17_user_role t17, t09_mandant t09 \r\n" + 
			"where t01.t01_id=t17.fk_t17_t01_employee and t09.t09_id=t17.fk_t17_t09_mandant and t09.t09_id in('BNG','DVG','MMG','REG')\r\n" + 
			"and UPPER(t01.t01_nt_id) =UPPER(:username) and t09.t09_client='PAG' order by t09.t09_id",nativeQuery = true)
	public Optional<List<Object[]>> getPAGMandantDao(@Param("username") String username);
		
	@Query(value="select distinct t08.T08_ID,TO_CHAR(t08.T08_CREATION_DATE, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.COB, 'dd.mm.yyyy'),TO_CHAR(t08.T08_START_BUSINESS_TIME, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.T08_STOP_BUSINESS_TIME, 'dd.mm.yyyy HH24:mm:SS'),t08.T08_STATUS,t08.FK_T08_T07_SOURCE_SYSTEM,TO_CHAR(t08.T08_START_LOAD_TIME, 'dd.mm.yyyy HH24:mm:SS'),\r\n" + 
			"TO_CHAR(t08.T08_STOP_LOAD_TIME, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.T08_START_CONVERT_TIME, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.T08_STOP_CONVERT_TIME, 'dd.mm.yyyy HH24:mm:SS'),t08.T08_TOTAL_RECORDS,t08.T08_LOAD_ERRORS,t08.T08_CONVERT_ERRORS,t08.T08_ARCHIVED \r\n" +
			"from t08_job t08 where T08_status not in ( 'CLOSED') and FK_t08_T09_Mandant in (:mandant) ORDER BY t08.T08_ID DESC",nativeQuery = true)
	public Optional<List<Object[]>> getOpenJobDao(@Param("mandant") String mandant);
	
	@Query(value = "select distinct(t02.T02_SRC_TRADE_ID),t02.fk_t02_t08_job,t02.T02_SRC_INSTRUMENT,t02.T02_CURRENCY,t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID, \r\n" + 
			"t02.T02_VOLUME,t02.T02_PRICE,t02.T02_SRC_TRADER_NAME,TO_CHAR(t02.T02_TRADE_DATE, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t02.T02_AMENDED_DATE, 'dd.mm.yyyy HH24:mm:SS') \r\n" + 
			"from T08_JOB t08, t04_trade_historie t04,  t02_trade t02, t44_book t44\r\n" + 
			"			where t04.fk_t04_t02_trade = t02.t02_id and t02.fk_t02_t08_job = t08.t08_id and t02.fk_t02_t44_book = t44.t44_book_id  \r\n" + 
			"			and t08.T08_status not in ( 'CLOSED')and t08.fk_t08_t09_mandant =:mandant  ORDER BY t02.fk_t02_t08_job DESC,T02.T02_Src_Trade_Id",nativeQuery = true)
	public Optional<List<Object[]>> getOpenTradeDao(@Param("mandant") String mandant);
	
	@Query(value="Select Distinct(T02.T02_Src_Trade_Id),t02.fk_t02_t08_job,T02.T02_Src_Instrument,T02.T02_Currency,T02.T02_Book_Id,T02.T02_Counterparty_Id,\r\n" + 
			"T02.T02_Volume,T02.T02_Price,T02.T02_Src_Trader_Name,To_Char(T02.T02_Trade_Date, 'dd.mm.yyyy HH24:mm:SS') As T02_Trade_Date,To_Char(T02.T02_Amended_Date, 'dd.mm.yyyy HH24:mm:SS') \r\n" + 
			"As T02_Amended_Date	From T02_Trade T02,T08_Job\r\n" + 
			"			Where T02.Fk_T02_T08_Job = T08_Id  And Fk_T02_T09_Mandant=:mandant  and t02.T02_TRADE_DATE>= sysdate-400 ORDER BY t02.fk_t02_t08_job DESC,T02.T02_Src_Trade_Id ",nativeQuery = true)
	public Optional<List<Object[]>> getSearchTradeDao(@Param("mandant") String mandant);
	
	@Query(value="SELECT t08.T08_ID,TO_CHAR(t08.T08_CREATION_DATE, 'dd.mm.yyyy HH24:mm:SS') as T08_CREATION_DATE,TO_CHAR(t08.COB, 'dd.mm.yyyy') as COB,\r\n" + 
			"TO_CHAR(t08.T08_START_BUSINESS_TIME, 'dd.mm.yyyy HH24:mm:SS') as T08_START_BUSINESS_TIME,TO_CHAR(t08.T08_STOP_BUSINESS_TIME, 'dd.mm.yyyy HH24:mm:SS') as T08_STOP_BUSINESS_TIME,\r\n" + 
			"t08.T08_STATUS,t08.FK_T08_T07_SOURCE_SYSTEM,TO_CHAR(t08.T08_START_LOAD_TIME, 'dd.mm.yyyy HH24:mm:SS') as T08_START_LOAD_TIME,\r\n" + 
			"TO_CHAR(t08.T08_STOP_LOAD_TIME, 'dd.mm.yyyy HH24:mm:SS') as T08_STOP_LOAD_TIME,TO_CHAR(t08.T08_START_CONVERT_TIME, 'dd.mm.yyyy HH24:mm:SS') as T08_START_CONVERT_TIME,\r\n" + 
			"TO_CHAR(t08.T08_STOP_CONVERT_TIME, 'dd.mm.yyyy HH24:mm:SS') as T08_STOP_CONVERT_TIME,t08.T08_TOTAL_RECORDS,t08.T08_LOAD_ERRORS,t08.T08_CONVERT_ERRORS,t08.T08_ARCHIVED \r\n" + 
			"FROM T08_JOB t08, t09_mandant t09   \r\n" + 
			"where t08.FK_T08_T09_MANDANT=t09.t09_id  and t09.t09_ID=:mandant ORDER BY t08.T08_ID DESC",nativeQuery = true)
	public Optional<List<Object[]>> getJobByMandantDao(@Param("mandant") String mandant);
	
	@Query(value="select t02.T02_SRC_TRADE_ID,t02.fk_t02_t08_job,t02.T02_SRC_INSTRUMENT,t02.T02_CURRENCY,t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,\r\n" + 
			"t02.T02_VOLUME,t02.T02_PRICE,t02.T02_SRC_TRADER_NAME,TO_CHAR(t02.T02_TRADE_DATE, 'dd.mm.yyyy HH24:mm:SS')as T02_TRADE_DATE,TO_CHAR(t02.T02_AMENDED_DATE, 'dd.mm.yyyy HH24:mm:SS')as T02_AMENDED_DATE \r\n" + 
			"from t02_Trade t02,t09_mandant t09 \r\n" + 
			"where t09.t09_id= t02.fk_t02_t09_mandant\r\n" + 
			"and t09.t09_id=:mandant and t02.fk_t02_t08_job =:jobId ",nativeQuery = true)
	public Optional<List<Object[]>> getTradeByJobIdDao(@Param("mandant") String mandant, @Param("jobId") String jobId);
	
	@Query(value="SELECT t08.T08_ID,TO_CHAR(t08.T08_CREATION_DATE, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.COB, 'dd.mm.yyyy'),TO_CHAR(t08.T08_START_BUSINESS_TIME, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.T08_STOP_BUSINESS_TIME, 'dd.mm.yyyy HH24:mm:SS'),t08.T08_STATUS,t08.FK_T08_T07_SOURCE_SYSTEM,TO_CHAR(t08.T08_START_LOAD_TIME, 'dd.mm.yyyy HH24:mm:SS'),\r\n" + 
			"TO_CHAR(t08.T08_STOP_LOAD_TIME, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.T08_START_CONVERT_TIME, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t08.T08_STOP_CONVERT_TIME, 'dd.mm.yyyy HH24:mm:SS'),t08.T08_TOTAL_RECORDS,t08.T08_LOAD_ERRORS,t08.T08_CONVERT_ERRORS,t08.T08_ARCHIVED \r\n" + 
			"FROM T08_JOB t08 join t09_mandant t09  \r\n" + 
			"on t08.FK_T08_T09_MANDANT=t09.t09_id \r\n" + 
			"where  t08.T08_CREATION_DATE>=TO_DATE(:FromDate,'DD-MM-YY') and t08.T08_CREATION_DATE<=TO_DATE(:ToDate,'DD-MM-YY') and t09.t09_name=:mandant  ",nativeQuery = true)
	//79979152
	public List<Object[]> getSelectedDateJobsDao(@Param("mandant") String mandant,@Param("FromDate")  LocalDate FromDate,@Param("ToDate")  LocalDate ToDate);
	
	@Query(value="select distinct(t02.T02_SRC_TRADE_ID),t02.fk_t02_t08_job,t02.T02_SRC_INSTRUMENT,t02.T02_CURRENCY,t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID, \r\n" + 
			"			t02.T02_VOLUME,t02.T02_PRICE,t02.T02_SRC_TRADER_NAME,TO_CHAR(t02.T02_TRADE_DATE, 'dd.mm.yyyy HH24:mm:SS'),TO_CHAR(t02.T02_AMENDED_DATE, 'dd.mm.yyyy HH24:mm:SS') \r\n" + 
			"					from t02_Trade t02,T08_JOB \r\n" + 
			"where fk_t02_t09_mandant=:mandant and  t02.fk_t02_t08_job = T08_ID and t02.T02_TRADE_DATE>=TO_DATE(:FromDate,'DD-MM-YY') and t02.T02_TRADE_DATE<=TO_DATE(:ToDate,'DD-MM-YY')",nativeQuery = true)
	
	public List<Object[]> getSelectedDateTradesDao(@Param("mandant") String mandant,@Param("FromDate")  LocalDate FromDate,@Param("ToDate")  LocalDate ToDate);
	
	
//	@Query(value="select listagg(t120.T120_REPORT_ID,',') WITHIN GROUP (order by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
//			"			t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,\r\n" + 
//			"			t44.T44_LOCATION_TRADER,\r\n" + 
//			"			t120.T120_REPORT_ID) T120_REPORT_ID , t02.T02_BOOK_ID, t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
//			"			t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION, t44.T44_LOCATION_TRADER, t02.T02_SRC_TRADE_ID \r\n" + 
//			"			FROM t09_mandant t09,T02_TRADE t02 full join T57_SUMMIT_BOND t57 \r\n" + 
//			"			ON t02.T02_ID = t57.T57_ID \r\n" + 
//			"			full join T44_BOOK t44\r\n" + 
//			"			ON t02.T02_BOOK_ID=t44.T44_BOOK_ID\r\n" + 
//			"			full join T120_REPORT_CONFIGURATION t120 \r\n" + 
//			"			ON t44.T44_LOCATION_TRADER=t120.T120_CONFIGURATION\r\n" + 
//			"			WHERE t02.T02_SRC_TRADE_ID=:tradeId and t09.t09_id=:mandant \r\n" + 
//			"			group by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE,\r\n" + 
//			"			t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,\r\n" + 
//			"			t44.T44_LOCATION_TRADER,t02.T02_SRC_TRADE_ID",nativeQuery=true)
//	public List<Object[]> getOverviewTradeNOJobIdDao(@Param("mandant") String mandant,@Param("tradeId") String tradeId);
	
//	@Query(value="select listagg(t120.T120_REPORT_ID,',') WITHIN GROUP (order by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
//			"t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION, \r\n" + 
//			"t44.T44_LOCATION_TRADER,t120.T120_REPORT_ID) AS \"T120_REPORT_ID\" , t02.T02_BOOK_ID, t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
//			"t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION, \r\n" + 
//			"t44.T44_LOCATION_TRADER, t02.T02_SRC_TRADE_ID\r\n" + 
//			"FROM T02_TRADE t02, T57_SUMMIT_BOND t57 ,T44_BOOK t44, T120_REPORT_CONFIGURATION t120,t09_mandant t09 \r\n" + 
//			"where t02.T02_ID = t57.T57_ID \r\n" + 
//			"and t02.T02_BOOK_ID=t44.T44_BOOK_ID \r\n" + 
//			"and t44.T44_LOCATION_TRADER=t120.T120_CONFIGURATION \r\n" + 
//			"and t09.t09_id= t02.fk_t02_t09_mandant\r\n" + 
//			"and t09.t09_id=:mandant \r\n" + 
//			"and t02.fk_t02_t08_job =:jobId \r\n" + 
//			"and t02.t02_SRC_Trade_ID=:tradeId \r\n" + 
//			"group by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME, \r\n" + 
//			"t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE,t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS, \r\n" + 
//			"t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,t44.T44_LOCATION_TRADER,t02.T02_SRC_TRADE_ID",nativeQuery = true)
	@Query(value="select listagg(t120.T120_REPORT_ID,',') WITHIN GROUP (order by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
			"			t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,\r\n" + 
			"			t44.T44_LOCATION_TRADER,\r\n" + 
			"			t120.T120_REPORT_ID) T120_REPORT_ID , t02.T02_BOOK_ID, t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,TO_CHAR(t02.T02_AMENDED_DATE, 'dd.mm.yyyy  HH24:mm:SS'), ROUND(t02.T02_PRICE,3),TO_CHAR(t02.T02_TRADE_DATE, 'dd.mm.yyyy  HH24:mm:SS'), \r\n" + 
			"			t57.T57_INSTRUMENT_NAME,TO_CHAR(t57.T57_EXPIRE_DAY, 'dd.mm.yyyy  HH24:mm:SS'),t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION, t44.T44_LOCATION_TRADER, t02.T02_SRC_TRADE_ID \r\n" + 
			"			FROM t09_mandant t09,T02_TRADE t02 full join T57_SUMMIT_BOND t57 \r\n" + 
			"			ON t02.T02_ID = t57.T57_ID \r\n" + 
			"			full join T44_BOOK t44\r\n" + 
			"			ON t02.T02_BOOK_ID=t44.T44_BOOK_ID\r\n" + 
			"			full join T120_REPORT_CONFIGURATION t120 \r\n" + 
			"			ON t44.T44_LOCATION_TRADER=t120.T120_CONFIGURATION\r\n" + 
			"			WHERE t02.T02_SRC_TRADE_ID=:tradeId and t09.t09_id=:mandant  and  t02.fk_t02_t08_job =:jobId\r\n" + 
			"			group by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE,\r\n" + 
			"			t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,\r\n" + 
			"			t44.T44_LOCATION_TRADER,t02.T02_SRC_TRADE_ID",nativeQuery=true)
	public List<Object[]> getOverviewTradeDao(@Param("mandant") String mandant, @Param("jobId") String jobId,@Param("tradeId") String tradeId);
	
	@Query(value="select Distinct t06.T06_REQUEST_STRING,TO_CHAR(t06.T06_REQUEST_DATE, 'dd.mm.yyyy  HH24:mm:SS'),substr((t02.T02_PRICE-t12.t12_price),1,5) as difference,ROUND(t12.t12_price,3) as t12_price ,ROUND(t02.T02_PRICE,3) as T02_PRICE\r\n" + 
			"FROM T02_TRADE t02,T06_REQUEST t06,t12_price t12,t09_mandant t09 where t02.T02_ID = t06.FK_T06_T02_TRADE and  t06.fk_t06_t12_price_result= t12.t12_id  \r\n" + 
			"            and t09.t09_id= t02.fk_t02_t09_mandant\r\n" + 
			"             and t09.t09_id=:mandant  \r\n" + 
			"            and t02.fk_t02_t08_job =:jobId\r\n" + 
			"			and t02.T02_SRC_TRADE_ID=:tradeId",nativeQuery = true)
	public List<Object[]> getOverviewMarketDataDao(@Param("mandant") String mandant,@Param("jobId") String jobId, @Param("tradeId") String tradeId);
	
	@Query(value="select t04.FK_T04_T10_AUTO_STATE_CODE,t04.t04_id,t04.FK_T04_T10_MAN_STATE_CODE,t04.T04_CHANGED_BY,t04.T04_COMMENT \r\n" + 
			"from t04_trade_historie t04, T02_TRADE t02,t09_mandant t09\r\n" + 
			"where FK_T04_T02_TRADE = t02.T02_ID\r\n" + 
			"and t09.t09_id=:mandant \r\n" + 
			"and t02.fk_t02_t08_job =:jobId \r\n" + 
			"and t02.T02_SRC_TRADE_ID = :tradeId",nativeQuery = true)
	public List<Object[]> getOverviewCheckResultDao(@Param("mandant") String mandant,@Param("jobId") String jobId, @Param("tradeId") String tradeId);
	
//	@Query(value="select listagg(t120.T120_REPORT_ID,',') WITHIN GROUP (order by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
//			"t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION, \r\n" + 
//			"t44.T44_LOCATION_TRADER,t120.T120_REPORT_ID) AS \"T120_REPORT_ID\" , t02.T02_BOOK_ID, t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
//			"t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION, \r\n" + 
//			"t44.T44_LOCATION_TRADER, t02.T02_SRC_TRADE_ID\r\n" + 
//			"FROM T02_TRADE t02, T57_SUMMIT_BOND t57 ,T44_BOOK t44, T120_REPORT_CONFIGURATION t120,t09_mandant t09 \r\n" + 
//			"where t02.T02_ID = t57.T57_ID \r\n" + 
//			"and t02.T02_BOOK_ID=t44.T44_BOOK_ID \r\n" + 
//			"and t44.T44_LOCATION_TRADER=t120.T120_CONFIGURATION \r\n" + 
//			"and t09.t09_id= t02.fk_t02_t09_mandant\r\n" + 
//			"and t09.t09_id=:mandant \r\n" + 
//			"and t02.fk_t02_t08_job =:jobId \r\n" + 
//			"and t02.t02_SRC_Trade_ID=:tradeId \r\n" + 
//			"group by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME, \r\n" + 
//			"t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE,t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS, \r\n" + 
//			"t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,t44.T44_LOCATION_TRADER,t02.T02_SRC_TRADE_ID ",nativeQuery = true)
	@Query(value="select Distinct listagg(t120.T120_REPORT_ID,',') WITHIN GROUP (order by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE, \r\n" + 
			"			t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,\r\n" + 
			"			t44.T44_LOCATION_TRADER,\r\n" + 
			"			t120.T120_REPORT_ID) T120_REPORT_ID , t02.T02_BOOK_ID, t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,TO_CHAR(t02.T02_AMENDED_DATE, 'dd.mm.yyyy  HH24:mm:SS'), ROUND(t02.T02_PRICE,3),TO_CHAR(t02.T02_TRADE_DATE, 'dd.mm.yyyy  HH24:mm:SS'), \r\n" + 
			"			t57.T57_INSTRUMENT_NAME,TO_CHAR(t57.T57_EXPIRE_DAY, 'dd.mm.yyyy  HH24:mm:SS'),t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION, t44.T44_LOCATION_TRADER, t02.T02_SRC_TRADE_ID \r\n" + 
			"			FROM t09_mandant t09,T02_TRADE t02 full join T57_SUMMIT_BOND t57 \r\n" + 
			"			ON t02.T02_ID = t57.T57_ID \r\n" + 
			"			full join T44_BOOK t44\r\n" + 
			"			ON t02.T02_BOOK_ID=t44.T44_BOOK_ID\r\n" + 
			"			full join T120_REPORT_CONFIGURATION t120 \r\n" + 
			"			ON t44.T44_LOCATION_TRADER=t120.T120_CONFIGURATION\r\n" + 
			"			WHERE t02.T02_SRC_TRADE_ID=:tradeId  \r\n" + 
			"			group by t02.T02_BOOK_ID,t02.T02_COUNTERPARTY_ID,t02.T02_CURRENCY,t02.T02_SRC_INSTRUMENT,t02.T02_SRC_TRADER_ID,t02.T02_SRC_TRADER_NAME,t02.T02_VOLUME,t02.T02_AMENDED_DATE, ROUND(t02.T02_PRICE,3),t02.T02_TRADE_DATE,\r\n" + 
			"			t57.T57_INSTRUMENT_NAME,t57.T57_EXPIRE_DAY,t57.T57_MARKETER,t57.T57_CATEGORY,t57.T57_STATUS,t57.T57_UPDATED_BY,t57.T57_SUB_TYPE,t57.T57_ALIAS,t57.T57_COUNTERPARTY_REFERENCE, t57.T57_DESCRIPTION,\r\n" + 
			"			t44.T44_LOCATION_TRADER,t02.T02_SRC_TRADE_ID",nativeQuery = true)
	public List<Object[]> getDerivativeOverviewTradeDao(@Param("tradeId") String tradeId);
	
	@Query(value="select T91_AMENDMENT_NPV_CHANGE as NPV,T91_VEGA as VEGA,T91_DELTA as DELTA,T91_PREMIUM AS PREMIUM,(select Distinct t02_volume as notional  from t02_trade t where t02_src_trade_id = :tradeId and  fk_t02_t08_job=:jobId and FK_T02_T09_MANDANT=:mandant) AS NOTIONAL, \r\n" + 
			"T91_TURNOVER AS TURNOVER,T91_DEVIATION AS DEVIATION,T91_AMENDMENT_NPV_CHANGE AS NPV_CHANGE from t91_summit_derivative where t91_id=(select t02_id from t02_trade where  t02_src_trade_id = :tradeId and fk_t02_t08_job=:jobId and FK_T02_T09_MANDANT=:mandant)",nativeQuery = true)
	public List<Object[]> getDerivativeOverviewMarketDataDao(@Param("mandant") String mandant,@Param("jobId") String jobId,@Param("tradeId") String tradeId);
	
	@Query(value="select t04.FK_T04_T10_AUTO_STATE_CODE as AUTOMATIC_STATE,t04.t04_id,t04.FK_T04_T10_MAN_STATE_CODE,t04.T04_CHANGED_BY,t04.T04_COMMENT from t04_trade_historie t04, T02_TRADE t02,t09_mandant t09 where FK_T04_T02_TRADE = t02.T02_ID \r\n" + 
			"and t09.t09_id=:mandant and t02.fk_t02_t08_job =:jobId and t02.T02_SRC_TRADE_ID = :tradeId",nativeQuery = true)
	public List<Object[]> getDerivativeOverviewCheckResultDao(@Param("mandant") String mandant, @Param("jobId") String jobId, @Param("tradeId") String tradeId);

	@Query(value="select t92.T92_ASSET_ID,t92.T92_TRADE_SUB_TYPE,t92.T92_CALL_PUT,t92.T92_PAY_NOTIONAL,t92.T92_PAY_CURRENCY,t92.T92_PAY_INDEX_BASIS,t92.T92_PAY_RATE_SPREAD,t92.T92_PAY_DISCOUNT_CURVE,t92.T92_RECEIVE_NOTIONAL,\r\n" + 
			"t92.T92_RECEIVE_INDEX_BASIS,t92.T92_RECEIVE_RATE_SPREAD,t92.T92_RECEIVE_DISCOUNT_CURVE,t92.T92_CAP_FLOOR,t92.T92_BUY_SELL,t92.T92_QUANTITY,t92.T92_UNDERLYING,t92.T92_STRIKE,t92.T92_STYLE \r\n" + 
			"from t92_asset t92,t09_mandant t09,T02_TRADE t02\r\n" + 
			"where t02.t02_ID =t92.FK_t92_t02_trade\r\n" + 
			"and t09.t09_id = t92.FK_T92_T09_MANDANT\r\n" + 
			"and t02.FK_T02_T09_MANDANT=:mandant \r\n" + 
			"and t02.FK_T02_T08_JOB=:jobId \r\n" + 
			"and t02.T02_SRC_TRADE_ID =:tradeId ",nativeQuery = true)
	public Optional<List<Object[]>> getOverviewAssetsDataDao(@Param("mandant") String mandant, @Param("jobId") String jobId, @Param("tradeId") String tradeId);
	
	
//	@Query(value="select T92_ASSET_ID,T92_TRADE_SUB_TYPE,T92_CALL_PUT,T92_PAY_NOTIONAL,T92_PAY_CURRENCY,T92_PAY_INDEX_BASIS,T92_PAY_RATE_SPREAD,T92_PAY_DISCOUNT_CURVE,T92_RECEIVE_NOTIONAL,\r\n" + 
//			"T92_RECEIVE_INDEX_BASIS,T92_RECEIVE_RATE_SPREAD,T92_RECEIVE_DISCOUNT_CURVE,T92_CAP_FLOOR,\r\n" + 
//			"T92_BUY_SELL,T92_QUANTITY,T92_UNDERLYING,T92_STRIKE,T92_STYLE from t92_asset where fk_t92_t02_trade = (select t02_id from t02_trade where t02_src_trade_id = :tradeId) ",nativeQuery = true)
//	public Optional<List<Object[]>> getOverviewAssetsDataDao1( @Param("tradeId") String tradeId);
	
	@Query(value="select t04_state_type,fk_t04_t10_auto_state_code,t04_state_time,t04_created_by,fk_t04_t15_report_image,t04_comment \r\n" + 
			"from T04_trade_historie t04, t02_trade t02, t09_mandant t09\r\n" + 
			"where fk_t04_t02_trade =  t02_id \r\n" + 
			"and t09.t09_id=:mandant \r\n" + 
			"and t02.fk_t02_t08_job =:jobId \r\n" + 
			"and t02_src_trade_id =:tradeId ",nativeQuery = true)
	public Optional<List<Object[]>> getTradeHistoryDao(@Param("mandant") String mandant, @Param("jobId") String jobId, @Param("tradeId") String tradeId);
	
//	@Query(value="select t04_state_type,fk_t04_t10_auto_state_code,t04_state_time,t04_created_by,fk_t04_t15_report_image,t04_comment \r\n" + 
//			"from T04_trade_historie t04, t02_trade t02, t09_mandant t09\r\n" + 
//			"where fk_t04_t02_trade =  t02_id \r\n" + 
//			"and t09.t09_id=:mandant \r\n" + 			
//			"and t02_src_trade_id =:tradeId ",nativeQuery = true)
//	public Optional<List<Object[]>> getTradeHistoryNOJobIdDao(@Param("mandant") String mandant, @Param("tradeId") String tradeId);
	
	@Query(value="select distinct t104.T104_TRADE_ID_OLD,t104.T104_TRADE_ID_NEW,t104.T104_FIELD_MODIFIED,t104.T104_FIELD_VALUE_NEW,t104.T104_FIELD_VALUE_OLD,t104.T104_FIELD_VALUE_CHANGE \r\n" + 
			"from t104_summit_amend t104, T02_TRADE t02, t09_mandant t09\r\n" + 
			"where t02.T02_SRC_TRADE_ID = t104_trade_id_new\r\n" + 
			"and t02.FK_T02_T09_MANDANT=:mandant \r\n" + 
			"and t02.FK_T02_T08_JOB=:jobId \r\n" + 
			"and t104_trade_id_new =:tradeId",nativeQuery = true)
	public Optional<List<Object[]>> getDerivativeStornoGroupDao(@Param("mandant") String mandant, @Param("jobId") String jobId, @Param("tradeId") String tradeId);

//	@Modifying
//	@Query(value="Update t04_trade_historie t04 set \r\n" + 
//			"			t04.FK_T04_T10_MAN_STATE_CODE=:result1,t04.T04_COMMENT=:comment1, t04.T04_CHANGED_BY =:UserName1\r\n" + 
//			"			where t04.FK_T04_T02_TRADE = (Select t02.T02_ID  from T02_TRADE t02,t09_mandant t09 \r\n" + 
//			"			where t02.T02_SRC_TRADE_ID =:TradeId1 and t09.t09_id=:mandant1 and t02.fk_t02_t08_job =:JobId1)",nativeQuery = true)
//	public void updateCommentDao(String UserName1,String mandant1,String JobId1,String TradeId1, String result1, String comment1);
//	
	
	
//	@Query(value="select distinct t104.T104_TRADE_ID_OLD,t104.T104_TRADE_ID_NEW,t104.T104_FIELD_MODIFIED,t104.T104_FIELD_VALUE_NEW,t104.T104_FIELD_VALUE_OLD,t104.T104_FIELD_VALUE_CHANGE \r\n" + 
//			"from t104_summit_amend t104, T02_TRADE t02, t09_mandant t09\r\n" + 
	
//			"where t02.T02_SRC_TRADE_ID = t104_trade_id_new\r\n" + 
//			"and t09.t09_id=:mandant \r\n" +  
//			"and t104_trade_id_new =:tradeId",nativeQuery = true)
//	public Optional<List<Object[]>> getDerivativeStornoGroupNOJobIdDao(@Param("mandant") String mandant,@Param("tradeId") String tradeId);
	
	@Modifying
	@Query(value="Update t05_instrument set T05_CHANGED_BY=:UserId,T05_CHANGED_DATE=TO_TIMESTAMP(:changedDate,'dd-MM-yyyy HH24:MI:SS:FF'),\r\n" + 
			"			   FK_T05_T11_PRICE_CHECK=( select t11_id from t11_price_check_category  where t11_name=:t11_name and t11_enabled = 'Y' and FK_T11_T09_MANDANT=:mandantId) where  T05_enabled = 'Y'and fk_t05_t09_mandant=:mandantId \r\n" + 
			"	and  T05_INSTRUMENT=:instrument and T05_INSTRUMENT_NAME=:instrument_name",nativeQuery = true)
public   void updatepriceCheck(String mandantId, String instrument, String instrument_name,
	String t11_name,String UserId,String changedDate);
	
	@Modifying
	@Query(value="update  t42_mgb_configuration set t42_value=:t42_value,T42_CHANGED_BY=:userId1,T42_CHANGED_DATE=TO_TIMESTAMP(:changedDate,'dd-MM-yyyy HH24:MI:SS:FF') where t42_key=:t42_key and  FK_T42_T09_MANDANT=:mandantId"
			,nativeQuery = true)
public   void updateConfigurationValue(String mandantId, String t42_key, String t42_value, String userId1,
		String changedDate);
	
	
	@Modifying
	@Query(value="Update t05_instrument set T05_CHANGED_BY=:UserId,T05_CHANGED_DATE=:changedDate \r\n" + 
			" where  FK_T05_T11_PRICE_CHECK=( select distinct t11_id  from t05_instrument t05 , t11_price_check_category t11,t09_mandant t09,t09_mandant t09  where t05.fk_t05_t09_mandant=:mandantId   \r\n" + 
			"and t05.T05_enabled = 'Y' and t05.fk_t05_t11_price_check = t11_id  and t11.t11_enabled = 'Y'			 \r\n" + 
			"	and t05.fk_t05_t09_mandant = t11.FK_T11_T09_MANDANT and t05.t05_instrument=:instrument and t05.t05_instrument_name=:instrument_name )and T05_enabled = 'Y'and fk_t05_t09_mandant=:mandantId\r\n" + 
			" "
			+ ""
			+ " ",nativeQuery = true)
	public void updatepriceCheck1(String mandantId, String instrument, String instrument_name,String UserId,String changedDate);

@Modifying
	@Query(value="Update t04_trade_historie t04 set \r\n" +
			"			t04.FK_T04_T10_MAN_STATE_CODE=:result1,t04.T04_COMMENT=:comment1, t04.T04_CHANGED_BY =:UserName1\r\n" +
			"			where t04.FK_T04_T02_TRADE = (Select t02.T02_ID  from T02_TRADE t02,t09_mandant t09 \r\n" +
			"			where t02.T02_SRC_TRADE_ID =:TradeId1 and t09.t09_id=:mandant1 and t02.fk_t02_t08_job =:JobId1)",nativeQuery = true)
	public void updateCommentDao(String UserName1,String mandant1,String JobId1,String TradeId1, String result1, String comment1);
	
	@Modifying
//	@Query(value="update t15_report_image t15 set t15.t15_name=:fileName,t15.t15_image=:file where t15.t15_id =(select t04.fk_t04_t15_report_image\r\n" +
//			"from t15_report_image t15,t04_trade_historie t04 , T02_TRADE t02,t09_mandant t09 where FK_T04_T02_TRADE = t02.T02_ID \r\n" +
//			"and t15.t15_id =t04.fk_t04_t15_report_image\r\n" +
//			"and t02.fk_t02_t08_job =:JobId1 and t02.T02_SRC_TRADE_ID =:TradeId1  and t09.t09_id=:mandant1 )",nativeQuery = true)
	@Query(value="update t04_trade_historie t04 set t04.fk_t04_t15_report_image=:FileId\r\n" +
			"where  t04.FK_T04_T02_TRADE = (Select t02.T02_ID  from T02_TRADE t02,t09_mandant t09 \r\n" +
			"						where t02.T02_SRC_TRADE_ID =:TradeId1 and t09.t09_id=:mandant and t02.fk_t02_t08_job =:JobId)",nativeQuery = true)
	public void updateFileForCommentsDao(int FileId,String mandant,String JobId,String TradeId1);
	
	
//	@Query(value="update t15_report_image t15 set t15.t15_name=:fileName,t15.t15_image=:file where t15.t15_id =(select t04.fk_t04_t15_report_image\r\n" +
//			"from t15_report_image t15,t04_trade_historie t04 , T02_TRADE t02,t09_mandant t09 where FK_T04_T02_TRADE = t02.T02_ID \r\n" +
//			"and t15.t15_id =t04.fk_t04_t15_report_image\r\n" +
//			"and t02.fk_t02_t08_job ='58621107' and t02.T02_SRC_TRADE_ID = '3135986AD_2'  and t09.t09_id='MMK' )",nativeQuery = true)
	@Modifying
	@Query(value="insert into t15_report_image (t15_id,t15_name,t15_image) values((SELECT MAX(t15_id+1) FROM t15_report_image),"
			+ ":fileName,:file)",nativeQuery = true)
	public void insertFileForCommentsDao(byte[] file,String fileName);
	
	@Query(value="SELECT MAX(t15_id) FROM t15_report_image",nativeQuery = true)
	public int getUpdatedFileIDDao();
	
	@Query(value="SELECT t15_image FROM t15_report_image where t15_id=(select DISTINCT FK_T04_T15_REPORT_IMAGE from t04_trade_historie t04\r\n" + 
			"			where  t04.FK_T04_T02_TRADE = (Select t02.T02_ID  from T02_TRADE t02,t09_mandant t09\r\n" + 
			"									where t02.T02_SRC_TRADE_ID =:tradeId1 and t09.t09_id=:mandant1 and t02.fk_t02_t08_job =:jobId1))",nativeQuery = true)
	public byte[] getFile(String mandant1,String jobId1,String tradeId1);
	
	
	@Query(value="select DISTINCT FK_T04_T15_REPORT_IMAGE from t04_trade_historie t04\r\n" + 
			"			where  t04.FK_T04_T02_TRADE = (Select t02.T02_ID  from T02_TRADE t02,t09_mandant t09\r\n" + 
			"			where t02.T02_SRC_TRADE_ID =:tradeId1 and t09.t09_id=:mandant1 and t02.fk_t02_t08_job =:jobId1)",nativeQuery = true)
	public String getFileStatus(String mandant1,String jobId1,String tradeId1);
	
	@Query(value="select t15_image from t15_report_image t15 where t15.t15_id='80033513'",nativeQuery = true)
	public byte[] getFile1();

	@Query(value="SELECT t15_name FROM t15_report_image where t15_id=(select DISTINCT FK_T04_T15_REPORT_IMAGE from t04_trade_historie t04\r\n" + 
			"						where  t04.FK_T04_T02_TRADE = (Select t02.T02_ID  from T02_TRADE t02,t09_mandant t09 \r\n" + 
			"												where t02.T02_SRC_TRADE_ID =:tradeId and t09.t09_id=:mandant and t02.fk_t02_t08_job =:jobId))",nativeQuery = true)
	public Optional<List<Object[]>> getFileName(String mandant,String jobId,String tradeId);

}
