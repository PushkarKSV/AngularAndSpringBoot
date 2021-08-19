package de.westlb.mgb.model.impl.finder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.vo.InstrumentSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.TradeReclRequiredVo;
import de.westlb.mgb.model.definition.DualControlJobProcessorDef;
import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.AbstractManualState;
import de.westlb.mgb.model.impl.AccessControlImpl;
import de.westlb.mgb.model.impl.AddonImpl;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.BatchStatisticReportImpl;
import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.ConditionImpl;
import de.westlb.mgb.model.impl.CounterpartyImpl;
import de.westlb.mgb.model.impl.CurrencyCategoryMappingImpl;
import de.westlb.mgb.model.impl.DualControlJobImpl;
import de.westlb.mgb.model.impl.EmployeeImpl;
import de.westlb.mgb.model.impl.ExchangeImpl;
import de.westlb.mgb.model.impl.ExchangeMappingEntryImpl;
import de.westlb.mgb.model.impl.ExchangeMappingEntryPrioImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.ManualSampleStateImpl;
import de.westlb.mgb.model.impl.ManualStateImpl;
import de.westlb.mgb.model.impl.MgbConfigurationIdImpl;
import de.westlb.mgb.model.impl.MgbConfigurationImpl;
import de.westlb.mgb.model.impl.PriceCheckCategoryImpl;
import de.westlb.mgb.model.impl.PriceCheckInstrumentImpl;
import de.westlb.mgb.model.impl.PrimeEquityImpl;
import de.westlb.mgb.model.impl.RatingCategoryMappingImpl;
import de.westlb.mgb.model.impl.ReclamationStateImpl;
import de.westlb.mgb.model.impl.ReportConfigurationImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.model.impl.RequestMappingImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.StateImpl;
import de.westlb.mgb.model.impl.StateRulesImpl;
import de.westlb.mgb.model.impl.SummitAmendImpl;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.model.impl.TimePeriodCategoryImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.TradeTypeCategoryMappingImpl;
import de.westlb.mgb.model.impl.TraderImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

public class MgbFinderImpl extends AbstractFinder {

	private static Logger logger = Logger.getLogger(MgbFinderImpl.class);

	public MgbFinderImpl(Session sess) {
		super(sess);
	}

	protected MgbFinderImpl() {
		super(null);
	}
	
    public Collection<AddonImpl> findAddons(SearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("add.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
		}
		Query query = buildQuery("from "+AddonImpl.class.getName()+" add", columnValues, clauseTokens);
		Collection<AddonImpl> result = query.list();
	    return result;
	}

    public Collection<AccessControlImpl> findAccessControls(SearchParams params) throws PersistenceException {
		Query query = sess.createQuery("from "+AccessControlImpl.class.getName()+" acc join fetch acc.role");
		Collection<AccessControlImpl> result = query.list();
	    return result;
	}
	
	public Collection<ConditionImpl> findConditions(SourceSystemImpl sourceSystem) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (sourceSystem != null) {
			clauseTokens.add("cond.sourceSystem = :sourceSystem");
			columnValues.put("sourceSystem", sourceSystem);
		}
		Query query = buildQuery("from "+ConditionImpl.class.getName()+" cond", columnValues, clauseTokens);
        Collection<ConditionImpl> result = query.list();
        return result;
	}

	public Collection<EmployeeImpl> findEmployees(EmployeeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
        String table = "select emp from "+EmployeeImpl.class.getName()+" emp";
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("emp.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
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
				table = table + ", "+TraderImpl.class.getName()+" tid";
			}
			if (params.hasRoles()) {
			    table = table + " join fetch emp.roles rol";
				clauseTokens.add("size(rol) > 0");				
			}
		}
		List<EmployeeImpl> list = buildQuery(table, columnValues, clauseTokens, " order by emp.lastName, emp.mandant.name").list();
		ArrayList<EmployeeImpl> arrayList = new ArrayList<EmployeeImpl>(); 
		for (Iterator<EmployeeImpl> it = list.iterator(); it.hasNext();) {
		    EmployeeImpl o = it.next();
		    if (!arrayList.contains(o)) {
		        arrayList.add(o);
		    }
		}
		return arrayList;

	}

    public Collection<MandantImpl> findMandants(EmployeeSearchParams params) throws PersistenceException {
        HashMap<String, Object> columnValues = new HashMap<String, Object>();
        ArrayList<String> clauseTokens = new ArrayList<String>();
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
        List<MandantImpl> result = buildQuery(table, columnValues, clauseTokens, " order by mand.name").list();
        return result;
   }

    public Collection<MandantImpl> findMandants(boolean onlyEnabled) throws PersistenceException {
        HashMap<String, Object> columnValues = new HashMap<String, Object>();
        ArrayList<String> clauseTokens = new ArrayList<String>();
        String table = "from "+MandantImpl.class.getName()+" m ";
        if (onlyEnabled) {
            clauseTokens.add("m.enabled = 'Y'");
        }
        List<MandantImpl> result = buildQuery(table, columnValues, clauseTokens, " order by m.name").list();
        return result;
   }

	public Collection<ExchangeMappingEntryImpl> findExchangeMappings(ExchangeMappingSearchParams params) throws PersistenceException {
		List<ExchangeMappingEntryPrioImpl> list = sess.createQuery("from "+ExchangeMappingEntryPrioImpl.class.getName()+" mapprio order by mapprio.prio").list();
		ExchangeMappingEntryPrioImpl[] exchangeMappingEntryPrio = list.toArray(new ExchangeMappingEntryPrioImpl[0]);

		for (int i = 0; i < exchangeMappingEntryPrio.length; i++) {
			boolean validParam = true;
			HashMap<String, Object> columnValues = new HashMap<String, Object>();
			ArrayList<String> clauseTokens = new ArrayList<String>();
			if (params != null) {
				if (exchangeMappingEntryPrio[i].isCurrencyNotNull()) {
					if (isNotNullOrZero(params.getCurrency())) {
						clauseTokens.add("map.currency = :currency");
						columnValues.put("currency", params.getCurrency());
					} else {
						validParam = false;
					}
				} else {
					clauseTokens.add("map.currency is null");
				}
			}
			if (params != null) {
				if (exchangeMappingEntryPrio[i].isSourceSystemNotNull()) {
					if (params.getSourceSystem() != null) {
						clauseTokens.add("map.sourceSystem = :sourceSystem");
						columnValues.put("sourceSystem", params.getSourceSystem());
					} else {
						validParam = false;
					}
				} else {
					clauseTokens.add("map.sourceSystem is null");
				}
			}
			if (params != null) {
				if (exchangeMappingEntryPrio[i].isSourceSystemExchangeIdNotNull()) {
					if (isNotNullOrZero(params.getSourceSystemExchangeId())) {
						clauseTokens.add("map.sourceSystemExchangeId = :sourceSystemExchangeId");
						columnValues.put("sourceSystemExchangeId", params.getSourceSystemExchangeId());
					} else {
						validParam = false;
					}
				} else {
					clauseTokens.add("map.sourceSystemExchangeId is null");
				}
			}
			if (params != null) {
				if (exchangeMappingEntryPrio[i].isIsinNotNull()) {
					if (isNotNullOrZero(params.getIsin())) {
						clauseTokens.add("map.isin = :isin");
						columnValues.put("isin", params.getIsin());
					} else {
						validParam = false;
					}
				} else {
					clauseTokens.add("map.isin is null");
				}
			}
			if (params != null) {
				if (exchangeMappingEntryPrio[i].isIsinCountryPrefixNotNull()) {
					if (isNotNullOrZero(params.getIsinCountryPrefix())) {
						clauseTokens.add("map.isinCountryPrefix = :isinCountryPrefix");
						columnValues.put("isinCountryPrefix", params.getIsinCountryPrefix());
					} else {
						validParam = false;
					}
				} else {
					clauseTokens.add("map.isinCountryPrefix is null");
				}
			}
			if (validParam) {
		        List<ExchangeMappingEntryImpl> resultList = buildQuery("from "+ExchangeMappingEntryImpl.class.getName()+" map", columnValues, clauseTokens).list();
		        if (resultList != null && !resultList.isEmpty()) {
		            return resultList;
		        }
			}
		}
		return null;
	}

	public Collection<ExchangeMappingEntryPrioImpl> findExchangeMappingPrios() throws PersistenceException {
		Collection<ExchangeMappingEntryPrioImpl> result = sess.createQuery("from "+ExchangeMappingEntryPrioImpl.class.getName()+" mapprio order by mapprio.prio").list();
        return result;
	}

	public ExchangeMappingEntryPrioImpl findExchangeMappingEntryPrio(boolean sourceSystemExchangeIdNotNull, boolean isinNotNull, boolean isinCountryPrefixNotNull, boolean currencyNotNull) throws PersistenceException {
	    Query query = sess.createQuery("from "+ExchangeMappingEntryPrioImpl.class.getName()+" mapprio where mapprio.sourceSystemNotNull = :sourceSystemExchangeIdNotNull and mapprio.sourceSystemExchangeIdNotNull = :sourceSystemExchangeIdNotNull and mapprio.isinNotNull = :isinNotNull and mapprio.isinCountryPrefixNotNull = :isinCountryPrefixNotNull and mapprio.currencyNotNull = :currencyNotNull");
        query.setParameter("sourceSystemExchangeIdNotNull", Boolean.valueOf(sourceSystemExchangeIdNotNull));
        query.setParameter("isinNotNull", Boolean.valueOf(isinNotNull));
        query.setParameter("isinCountryPrefixNotNull", Boolean.valueOf(isinCountryPrefixNotNull));
        query.setParameter("currencyNotNull", Boolean.valueOf(currencyNotNull));
        Iterator<ExchangeMappingEntryPrioImpl> result = query.iterate();
        if (result.hasNext()) {
            return result.next();
        }
        return null;
	}

	public HashMap<String, String> findExchangeMappings() throws PersistenceException {
		HashMap<String, String> result = new HashMap<String, String>();
		Collection<ExchangeMappingEntryImpl> col = sess.createQuery("from "+ExchangeMappingEntryImpl.class.getName()+" map").list();
		Iterator<ExchangeMappingEntryImpl> it = col.iterator();
		while (it.hasNext()) {
			ExchangeMappingEntryImpl exchangeMappingEntry = it.next();
			if (exchangeMappingEntry != null && exchangeMappingEntry.getExchange() != null) {
				String key = exchangeMappingEntry.buildRequestKey();
				String value = exchangeMappingEntry.getExchange().getBloombergId();
				logger.debug("key " + key + " is mapped to " + value);
				result.put(key, value);
			}
		}
		return result;
	}

    public HashMap<String, Double> findRequestPriceConversionFaktors() throws PersistenceException {
        HashMap<String, Double> result = new HashMap<String, Double>();
        Collection<RequestMappingImpl> col = sess.createQuery("from "+RequestMappingImpl.class.getName()+" map where map.conversionFactor <> 1").list();
        Iterator<RequestMappingImpl> it = col.iterator();
        while (it.hasNext()) {
            RequestMappingImpl exchangeMappingEntry = it.next();
            if (exchangeMappingEntry != null) {
                String key = exchangeMappingEntry.getRequestString();
                logger.debug("key " + key + " is mapped to " + exchangeMappingEntry.getConversionFactor());
                result.put(key, exchangeMappingEntry.getConversionFactor());
            }
        }
        return result;
    }

    public List<String> findReportClients() throws PersistenceException {
        List<String> result = new ArrayList<String>();
        Collection<String> col = sess.createQuery("select distinct rep.clientId from "+ReportConfigurationImpl.class.getName()+" rep order by rep.clientId").list();
        Iterator<String> it = col.iterator();
        while (it.hasNext()) {
             result.add(it.next());
        }
        return result;
    }

    public HashMap<String, List<String>> findReportConfiguration() throws PersistenceException {
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();
        Collection<ReportConfigurationImpl> col = sess.createQuery("from "+ReportConfigurationImpl.class.getName()+" rep order by rep.reportId").list();
        Iterator<ReportConfigurationImpl> it = col.iterator();
        List<String> configList = null;
        String lastReport = null;
        while (it.hasNext()) {
            ReportConfigurationImpl reportConfig = it.next();
            if (!reportConfig.getReportId().equals(lastReport)) {
                if (configList != null) {
                    result.put(lastReport, configList);
                }
                configList = new ArrayList<String>();
            }
            configList.add(reportConfig.getConfiguration());
            lastReport = reportConfig.getReportId();
        }
        if (configList != null && lastReport != null) {
            result.put(lastReport, configList);
        }
        return result;
    }

    public List<String> findReports(String type) throws PersistenceException {
        List<String> result = new ArrayList<String>();
        Query query = sess.createQuery("select distinct rep.reportId from "+ReportConfigurationImpl.class.getName()+" rep where rep.clientId = :client");
        query.setParameter("client", type);
        Collection<String> col = query.list();
        Iterator<String> it = col.iterator();
        while (it.hasNext()) {
             result.add(it.next());
        }
        return result;
    }

	public Collection<ExchangeImpl> findExchanges(SearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("exchange.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
		}
		Query query = buildQuery("from "+ExchangeImpl.class.getName()+" exchange", columnValues, clauseTokens);
        Collection<ExchangeImpl> result = query.list();
        return result;
	}

	public Collection<InstrumentSearchResultEntryVo> findInstrumentVos(InstrumentSearchParams params) throws PersistenceException {
		ArrayList<InstrumentSearchResultEntryVo> resultArray = new ArrayList<InstrumentSearchResultEntryVo>();
		String table;
		Query query;
		
		// Find all pending instrument dual control jobs
		table = "select dcj from "+DualControlJobImpl.class.getName()+" dcj where dcj.state = '" + DualControlJobProcessorDef.OPEN 	+ "' and dcj.instrument.id is not null";
		query = sess.createQuery(table);
		logger.debug("Found " + query.list().size() + " pending instrument dual control jobs.");
		HashMap<Long, DualControlJobImpl> pendingDcjs = new HashMap<Long, DualControlJobImpl>();	
		Iterator<DualControlJobImpl> dcit = query.list().iterator();
		while (dcit.hasNext()) {
			DualControlJobImpl dcj = dcit.next();
			pendingDcjs.put(dcj.getInstrument().getLongId(), dcj);
		}
		
		// Find instruments
		if (params != null && params.isOnlyPriceCheckInstruments()){
			table = "from "+PriceCheckInstrumentImpl.class.getName()+" instr";
		} else {
			table = "from "+InstrumentImpl.class.getName()+" instr";
		}
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("instr.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (isNotNullOrZero(params.getInstrument())) {
				if (params.isExactMatch()) {
					clauseTokens.add("instr.instrument = :instrument");
					columnValues.put("instrument", params.getInstrument());
				} else {
					clauseTokens.add("upper(instr.instrument) like upper(:instrument)");
					columnValues.put("instrument", params.getInstrument());
				}
			}else { // start with empty result set
				clauseTokens.add("1=0");
			}
	        if (params.isOnlyEnabled()) {
	            clauseTokens.add("instr.enabled = 'Y'");
	        }
		}
		query = buildQuery(table, columnValues, clauseTokens);
		Collection<? extends InstrumentImpl> coll = query.list(); 
		logger.debug("Found " + coll.size() + " instruments.");		
        Iterator<? extends InstrumentImpl> it = coll.iterator();
		while (it.hasNext()) {
			InstrumentImpl instrument = it.next();
			DualControlJobImpl dcj = pendingDcjs.get(instrument.getLongId());
			
			InstrumentSearchResultEntryVo vo = new InstrumentSearchResultEntryVo();
			vo.setId(instrument.getLongId());
			vo.setIsin(instrument.getInstrument());
			vo.setInstrumentName(instrument.getInstrumentName());
			if (instrument instanceof PriceCheckInstrumentImpl && ((PriceCheckInstrumentImpl)instrument).getPriceCheckCategory() != null) {
				vo.setPriceCheckCategoryId(((PriceCheckInstrumentImpl)instrument).getPriceCheckCategory().getLongId());
			}
			vo.setChangePending(false);
			if (dcj != null) {
				vo.setChangePending(true);
				EmployeeImpl employee = dcj.getRequestedBy();
				if (employee != null) {
					vo.setChangeRequestedByName(employee.getFullName());
				}
			}
			
			resultArray.add(vo);
		}
					
		return resultArray;
	}

	public Collection<? extends InstrumentImpl> findInstruments(InstrumentSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "from "+InstrumentImpl.class.getName()+" instr";
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("instr.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (isNotNullOrZero(params.getInstrument())) {
				if (params.isExactMatch()) {
					clauseTokens.add("instr.instrument = :instrument");
					columnValues.put("instrument", params.getInstrument());
				} else {
                    clauseTokens.add("upper(instr.instrument) like upper(:instrument)");
                    clauseTokens.add("instr.instrument not like '**old**%'");
					columnValues.put("instrument", params.getInstrument());
				}
			}
			if (params.isOnlyPriceCheckInstruments()) {
				table = "from "+PriceCheckInstrumentImpl.class.getName()+" instr";
			}
		}
		Collection<? extends InstrumentImpl> result = buildQuery(table, columnValues, clauseTokens).list();
		return result;
	}

	public Collection<PriceCheckInstrumentImpl> findNewInstruments(SearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		clauseTokens.add("instr.priceCheckCategory is null");
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("instr.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
		}
		Collection<PriceCheckInstrumentImpl> result = buildQuery("from "+PriceCheckInstrumentImpl.class.getName()+" instr", columnValues, clauseTokens).list();
		return result;
	}
	
	public Collection<TradeImpl> findNewLocations(SearchParams params) throws PersistenceException
	{
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		clauseTokens.add("t.bookId in (select bk.bookId from "+
		        BookImpl.class.getName()+" bk where bk.locationTrader not in (select rc.configuration from "+
                ReportConfigurationImpl.class.getName()+" rc where rc.reportId in (select sr.sqlParam from "+
				BatchStatisticReportImpl.class.getName()+" sr)))");
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("t.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
		}
		Calendar today = Calendar.getInstance();
		Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.YEAR,-1);
		clauseTokens.add("t.job.stopConvertTime between :fromConvertTime and :toConvertTime");
		columnValues.put("fromConvertTime", lastYear);
		columnValues.put("toConvertTime",today);
		
		Collection<TradeImpl> result = buildQuery("from "+
            (params != null && params.getMandant() != null?
                AbstractFinder.getTradeClassForMandantCode(params.getMandant()).getName() : TradeImpl.class.getName())+
            " t", columnValues, clauseTokens).list();
		return result;
	}
	
	public Collection<TraderImpl> findTrader(TraderSearchParams param) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (param != null) {
			if (param.getMandant() != null) {
				clauseTokens.add("trader.mandant = :mandant");
				columnValues.put("mandant", param.getMandant());
			}
			if (param.getSourceSystem() != null) {
				clauseTokens.add("trader.sourceSystem = :sourceSystem");
				columnValues.put("sourceSystem", param.getSourceSystem());
			}
			if (isNotNullOrZero(param.getTraderCode())) {
				clauseTokens.add("trader.traderCode = :traderCode");
				columnValues.put("traderCode", param.getTraderCode());
			}
			if (param.getEmployee() != null) {
				clauseTokens.add("trader.employee = :employee");
				columnValues.put("employee", param.getEmployee());
			}
		}
		Collection<TraderImpl> result = buildQuery("from "+TraderImpl.class.getName()+" trader", columnValues, clauseTokens).list();
		return result;
	}

	public TradeReclRequiredVo[] findReclRequiredTradeVos(JobImpl[] jobs) throws PersistenceException {

		if (jobs == null || jobs.length == 0) {
			return new TradeReclRequiredVo[0];
		}

		ArrayList<TradeReclRequiredVo> result = new ArrayList<TradeReclRequiredVo>();
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("SELECT trade.id ");
			strBuf.append(",trade.currency ");
			strBuf.append(",trade.sourceSystemInstrument ");
			strBuf.append(",trade.price ");
			strBuf.append(",trade.settlementDate ");
			strBuf.append(",trade.systemDate ");
			strBuf.append(",trade.tradeDate ");
			strBuf.append(",trade.tradeId ");
			strBuf.append(",trade.traderId ");
			strBuf.append(",trade.volume ");
			strBuf.append(",trade.isLateDeal ");
			strBuf.append(",trade.trader.employee.id ");
			strBuf.append(",trade.trader.employee.firstName ");
			strBuf.append(",trade.trader.employee.lastName ");
			strBuf.append(",trade.trader.employee.email ");
			strBuf.append(",trade.trader.employee.phone ");
			strBuf.append(",trade.currentManualStateHistEntry.manualState.stateCode ");
			strBuf.append("FROM ").append(TradeImpl.class.getName()).append(" trade ");
			strBuf.append("WHERE trade.job.id IN (");
			strBuf.append(jobs[0].getId());
			for (int i = 1; i < jobs.length; i++) {
				strBuf.append(",");
				strBuf.append(jobs[i].getId());
			}
			strBuf.append(") ");
			strBuf.append("AND (trade.currentReclStateHistEntry is null) AND(");
			strBuf.append("trade.currentManualStateHistEntry.manualState.isReclamationRequired = 'Y' ");
			strBuf.append(") ");
			Query query = sess.createQuery(strBuf.toString());
			Iterator<Object[]> iterator = query.list().iterator();
			while (iterator.hasNext()) {
				Object[] row = iterator.next();
				TradeReclRequiredVo vo = new TradeReclRequiredVo();
				int i = 0;
				vo.setId((Long) row[i++]);
				vo.setCurrency((String) row[i++]);
				vo.setSourceSystemInstrument((String) row[i++]);
				vo.setPrice(row[i] == null ? 0d : ((Double) row[i]).doubleValue());
				i++;
				vo.setSettlementDate((Calendar) row[i++]);
				vo.setSystemDate((Calendar) row[i++]);
				vo.setTradeDate((Calendar) row[i++]);
				vo.setTradeId((String) row[i++]);
				vo.setTraderId((String) row[i++]);
				vo.setVolume(row[i] == null ? 0d : ((Double) row[i]).doubleValue());
				i++;
				vo.setLateEntry((Boolean)row[i++]);
				vo.setEmployeeId((Long)row[i++]);
				vo.setEmployeeName(EmployeeImpl.createFullName((String)row[i++], (String)row[i++]));
				vo.setEmail((String) row[i++]);
				vo.setPhone((String) row[i++]);
				vo.setManualStateCode((String)row[i++]);
				result.add(vo);
			}
		
		return result.toArray(new TradeReclRequiredVo[0]);
	}

	public Collection<TradeImpl> findTrades(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
        String table = "from "+TradeImpl.class.getName()+" trade";
        String hint = " left outer join fetch trade.currentAutoStateHistEntry left outer join fetch trade.currentManualStateHistEntry";
        
		if (params != null) {
			if (params.getJobs() != null && params.getJobs().length == 0) {
                logger.warn("Returning no data since no jobs are selected");
				return Collections.emptyList();
			}

			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
                table = "from " + getTradeClassForMandantCode(params.getMandant()).getName() + " trade";
			}
            if (params.getClassName() != null) {
                table = "from " + params.getClassName() + " trade";
            }
			if (params.getFromDate() != null) {
				clauseTokens.add("trade.tradeDate >= :fromDate");
				columnValues.put("fromDate", params.getFromDate());
			}
			if (params.getToDate() != null) {
				clauseTokens.add("trade.tradeDate <= :toDate");
				columnValues.put("toDate", params.getToDate());
			}
			
			if (params.getJobStatus() != null && params.getJobStatus().length > 0) {
				clauseTokens.add("trade.job.status in ("+array2InClause(params.getJobStatus())+ ")");
	            if (params.getMandant() != null) {
	                clauseTokens.add("trade.job.mandant = :mandant");
	                columnValues.put("mandant", params.getMandant());
	            }
			}

			if (params.getFromJobCobDate() != null) {
				Calendar jobFromDate = new GregorianCalendar();
				jobFromDate.setTimeInMillis(params.getFromJobCobDate().getTimeInMillis());
				jobFromDate.set(Calendar.HOUR_OF_DAY, 0);
				jobFromDate.set(Calendar.MINUTE, 0);
				jobFromDate.set(Calendar.SECOND, 0);
				jobFromDate.set(Calendar.MILLISECOND, 0);

                Calendar jobToDate = new GregorianCalendar();
				if (params.getToJobCobDate() != null) {
				    jobToDate.setTimeInMillis(params.getToJobCobDate().getTimeInMillis());
				}
                jobToDate.set(Calendar.HOUR_OF_DAY, 0);
                jobToDate.set(Calendar.MINUTE, 0);
                jobToDate.set(Calendar.SECOND, 0);
                jobToDate.set(Calendar.MILLISECOND, 0);                 
                jobToDate.add(Calendar.DAY_OF_YEAR, 1);

				clauseTokens.add("trade.job.cob >= :jobFromDate");
				columnValues.put("jobFromDate", jobFromDate);
				clauseTokens.add("trade.job.cob < :jobToDate");
				columnValues.put("jobToDate", jobToDate);
                if (params.getMandant() != null) {
                    clauseTokens.add("trade.job.mandant = :mandant");
                    columnValues.put("mandant", params.getMandant());
                }
			}
			
			/* Filtering by job creation date does not reset the time component. That's
			 * because we introduced it to reproduce the email report from within
			 * the GUI, and that one implicitly filters with time component 19:00
			 * in the stop date parameter because it's the time when it runs. */
			if(params.getFromJobCreationDate() != null)
			{
			    clauseTokens.add("trade.job.creationDate >= :jobFromCreationDate");
			    columnValues.put("jobFromCreationDate",params.getFromJobCreationDate());
			    clauseTokens.add("trade.job.creationDate <= :jobToCreationDate");
			    columnValues.put("jobToCreationDate",params.getToJobCreationDate());
			}

            if (params.getLocations() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.book.locationTrader in (");
                for (int i = 0; i < params.getLocations().length; i++) {
                    columnValues.put("loc" + df.format(i), params.getLocations()[i]);
                    if (i == 0) {
                        s.append(":loc").append(df.format(i));                        
                    } else {
                        s.append(", :loc").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
            }
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
				for (int i = 0; i < params.getJobs().length; i++) {
					columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
				}
                s.append(")");
                clauseTokens.add(s.toString());
			}
			if (params.getTradeIds() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer();
				for (int i = 0; i < params.getTradeIds().length; i++) {
					columnValues.put("tid" + df.format(i), params.getTradeIds()[i].replace('*','%'));
                    if (i == 0) {
                        s.append("(trade.tradeId like :tid").append(df.format(i));
                    } else {
                        s.append(" or trade.tradeId like :tid").append(df.format(i));
                    }
				}
				if (s.length() > 0) {
	                s.append(")");
	                clauseTokens.add(s.toString());
				}
			}
			if (params.getEmployee() != null) {
				clauseTokens.add("trade.trader.employee = :employee");
				columnValues.put("employee", params.getEmployee());
			}
			if (params.getStateId() != null) {
				StateImpl state = (StateImpl) sess.select(StateImpl.class, params.getStateId());
				if (state instanceof AutomaticStateImpl) {
					clauseTokens.add(
						"trade.currentAutoStateHistEntry.automaticState.stateId.stateCode = :stateCode and trade.currentAutoStateHistEntry.automaticState.stateId.mandant = :stateMandant");
				} else if (state instanceof AbstractManualState) {
					clauseTokens.add(
						"trade.currentManualStateHistEntry.manualState.stateId.stateCode = :stateCode and trade.currentManualStateHistEntry.manualState.stateId.mandant = :stateMandant");
				} else if (state instanceof ReclamationStateImpl) {
					clauseTokens.add(
						"trade.currentReclStateHistEntry.reclamationState.stateId.stateCode = :stateCode and trade.currentReclStateHistEntry.reclamationState.stateId.mandant = :stateMandant");
				}
				columnValues.put("stateCode", params.getStateId().getStateCode());
				columnValues.put("stateMandant", params.getStateId().getMandant());
			}
			if (params.getIsMarketDataRequestRequired() != null ) {
				if (params.getIsMarketDataRequestRequired().booleanValue()) {
					clauseTokens.add("trade.currentAutoStateHistEntry.automaticState.marketDataRequestType is not null");
				} else {
					clauseTokens.add("trade.currentAutoStateHistEntry.automaticState.marketDataRequestType is null");
				}
			}
			if (params.getIsManualCheckRequired() != null) {
				clauseTokens.add("trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired = :isManualCheckRequired");
                columnValues.put("isManualCheckRequired", params.getIsManualCheckRequired());
			}
			if (params.getIsManualCheckRequiredButNotHandled() != null) {
				clauseTokens.add("trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired = :isManualCheckRequired and trade.currentManualStateHistEntry is null");
                columnValues.put("isManualCheckRequired", params.getIsManualCheckRequiredButNotHandled());
			}
			if (params.getIsReclamationRequiredButNotHandled() != null) {
		           clauseTokens.add("trade.currentManualStateHistEntry.manualState.isReclamationRequired = :isReclamationRequired and trade.currentReclStateHistEntry is null");
                   columnValues.put("isReclamationRequired", params.getIsReclamationRequiredButNotHandled());
            }
			if (params.getIsReclamationClosed() != null) {
				clauseTokens.add("trade.currentReclStateHistEntry.isClosed = :isClosed");
                columnValues.put("isClosed", params.getIsReclamationClosed());
			}
			if (params.getIsSampleChecked() != null) {
				clauseTokens.add("trade.currentManualStateHistEntry.manualState.class = ManualSampleStateImpl");
			}

			if (params.getIsCurrentReclamationStateNull() != null) {
				if (Boolean.TRUE.equals(params.getIsCurrentReclamationStateNull())) {
					clauseTokens.add("trade.currentReclStateHistEntry is null");
				} else {
					clauseTokens.add("trade.currentReclStateHistEntry is not null");
				}
			}
			
			if (params.getIsLateEntry() != null && params.getIsLateEntry().booleanValue()) {
				clauseTokens.add("trade.isLateDeal = :isLateEntry");
                columnValues.put("isLateEntry", params.getIsLateEntry());
			}
			if (params.getIsSmallCustomer() != null) {
				// fields only available for SummitBondImpl 
				table = "from "+SummitBondImpl.class.getName()+" trade";
				clauseTokens.add("trade.isSmallCustomer = :isSmallCustomer");
                columnValues.put("isSmallCustomer", params.getIsSmallCustomer());
			}
			if (params.getIsStorno() != null) {
				// fields only available for SummitBondImpl 
				table = "from "+SummitBondImpl.class.getName()+" trade";
				clauseTokens.add("(trade.isStorno = :isStorno or trade.isIntraDayStorno = :isStorno)");
                columnValues.put("isStorno", params.getIsStorno());
			}
			if (params.getHasPreDecessor() != null) {
				clauseTokens.add("trade.isTradeGroup = :isTradeGroup");
                columnValues.put("isTradeGroup", params.getHasPreDecessor());
			}

			if (isNotNullOrZero(params.getSparkassenId())) {
				clauseTokens.add("trade.counterparty.sparkasse.id = :sparkassenId");
				columnValues.put("sparkassenId", params.getSparkassenId());
			}
			if (params.getHasLowTurnover() != null) {
				// fields only available for SummitBondImpl 
				table = "from "+SummitBondImpl.class.getName()+" trade";
				clauseTokens.add("trade.isBagatelle = :isBagatelle");
                columnValues.put("isBagatelle", params.getHasLowTurnover());
			}
            if (params.getInstruments() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.sourceSystemInstrument in (");
                if (params.getMandant() != null) {
                    if (SummitBondImpl.class.isAssignableFrom( getTradeClassForMandantCode(params.getMandant())) ){
                        s = new StringBuffer("trade.bloombergInstrument.instrument in (");
                    }
                }
                for (int i = 0; i < params.getInstruments().length; i++) {
                    columnValues.put("inst" + df.format(i), params.getInstruments()[i]);
                    if (i == 0) {
                        s.append(":inst").append(df.format(i));                        
                    } else {
                        s.append(", :inst").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
            }
			


		}
		final Query q=buildQuery(table+hint, columnValues, clauseTokens);
        if (params != null && params.getMaxResults() > 0) {
            logger.debug("Setting max results to: "+params.getMaxResults());
            q.setMaxResults(params.getMaxResults());
        }
		Collection<TradeImpl> result = q.list();
        return result;
	}

	public Collection<JobImpl> findJobs(JobSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
        ArrayList<String> clauseTokens = new ArrayList<String>();
		Collection<SourceSystemImpl> sourceSystemList = null;
		String table = "from "+JobImpl.class.getName()+" jjj";
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("jjj.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
				sourceSystemList = findSourceSystems(new String[] { params.getMandant().getCode()});
			}
			if (params.getJobIds() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("jjj.longId in (");
				for (int i = 0; i < params.getJobIds().length; i++) {
					columnValues.put("job" + df.format(i), params.getJobIds()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
				}
				s.append(")");
				clauseTokens.add(s.toString());
			}
			if (params.getStatus() != null) {
			    String likeClause = null;
                String inClause = null;
                for (int i = 0; i < params.getStatus().length; i++) {
                    if (JobStateDef.JOB_ERROR_STATUS.equals(params.getStatus()[i])) {
                        likeClause = "jjj.status like :status";
                        columnValues.put("status", params.getStatus()[i] + "%");
                    } else {
                        if (inClause == null) {
                            inClause = "jjj.status in (:status0";
                        } else { 
                            inClause = inClause + ", :status" + i;
                        }
                        columnValues.put("status" + i, params.getStatus()[i]);
                    }
                }
                if (inClause != null) {
                    inClause = inClause + ")";
                    if (likeClause != null) {
                        clauseTokens.add("("+inClause + " or "+likeClause+")");
                    } else {
                        clauseTokens.add(inClause);
                    }
                } else {
                    if (likeClause != null) {
                        clauseTokens.add(likeClause);
                    }
                }
			}
			if (params.getCob() != null) {
				clauseTokens.add("jjj.cobDayString = :cobDayString");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				columnValues.put("cobDayString", format.format(params.getCob().getTime()));
			}
			if (params.getImportDayFrom() != null) {
				clauseTokens.add("jjj.stopLoadTime > :convertTimeString");
				columnValues.put("convertTimeString", params.getImportDayFrom());
			}
			// as default the most recent jobs per sourceSystem are selected.
			if (params.isDefaultSelection()) {
                clauseTokens.add("jjj.sourceSystem = :sourceSystem");
                ArrayList<String> whereClauseTokens = new ArrayList<String>();
                for (Iterator<String> iterator = clauseTokens.iterator(); iterator.hasNext();) {
                    whereClauseTokens.add(iterator.next().replaceAll("jjj", "kkk"));
                }
                String whereClause = wereClauseFromTokenList(whereClauseTokens);
				table = "from "+JobImpl.class.getName()+" kkk "+whereClause+" and kkk.cob >= (select trunc(max(jjj.cob)) from "+JobImpl.class.getName()+" jjj";
                
				Collection<JobImpl> resultList = new ArrayList<JobImpl>();
				Iterator<SourceSystemImpl> it = sourceSystemList.iterator();
				while (it.hasNext()) {
					columnValues.put("sourceSystem", it.next());

					Query query = buildQuery(table, columnValues, clauseTokens, ") order by kkk.longId desc ");
					if (params.getMaxResults() > 0) {
						query.setMaxResults(params.getMaxResults());
					}
			        Collection<JobImpl> resultForSourceSystemList = query.list();
					resultList.addAll(resultForSourceSystemList);
				}
				return resultList;
			}
            if (params.getSourceSystem() != null) {
            	clauseTokens.add("jjj.sourceSystem = :sourceSystem");
            	columnValues.put("sourceSystem", params.getSourceSystem());
            }
		}
		Query query = buildQuery(table, columnValues, clauseTokens, " order by jjj.longId desc ");
		if (params != null && params.getMaxResults() > 0) {
			query.setMaxResults(params.getMaxResults());
		}
        Collection<JobImpl> result = query.list(); 
		return result;
	}

    public Collection<? extends StateImpl> findStateCodes(MandantImpl mandant, String stateType) throws PersistenceException {
        return findStateCodes(mandant, stateType, true);
    }
        
    public Collection<? extends StateImpl> findStateCodes(MandantImpl mandant, String stateType, boolean enabledOnly) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "from ";
		if (StateCodeTypeDef.AUTO.equals(stateType)) {
			table = table + AutomaticStateImpl.class.getName();
		} else if (StateCodeTypeDef.ALL_MANUAL.equals(stateType)) {
			table = table + AbstractManualState.class.getName();
		} else if (StateCodeTypeDef.MANUAL.equals(stateType)) {
			table = table + ManualStateImpl.class.getName();
		} else if (StateCodeTypeDef.SAMPLE.equals(stateType)) {
			table = table + ManualSampleStateImpl.class.getName();
		} else if (StateCodeTypeDef.RECLAMATION.equals(stateType)) {
			table = table + ReclamationStateImpl.class.getName();
		} else {
			table = table + StateImpl.class.getName();
		}
		table = table + " state";
		if (mandant != null) {
			clauseTokens.add("state.stateId.mandant = :mandant");
			columnValues.put("mandant", mandant);
		}
		if (enabledOnly) {
		    clauseTokens.add("state.enabled = 'Y'");
		}
		Collection<? extends StateImpl> result = buildQuery(table, columnValues, clauseTokens).list();
		return result;
	}

	public Collection<StateRulesImpl> findStateRules(MandantImpl mandant, int stage) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "from "+StateRulesImpl.class.getName()+" rule join fetch rule.sourceSystem ";
		if (mandant != null) {
			clauseTokens.add("rule.sourceSystem.mandant = :mandant");
			columnValues.put("mandant", mandant);
		}
		clauseTokens.add("rule.stage = :stage");
		columnValues.put("stage", Integer.valueOf(stage));
		Collection<StateRulesImpl> result = buildQuery(table, columnValues, clauseTokens, "order by rule.priority asc").list();
		return result;
	}

    public Collection<ConditionImpl> findStateRuleConditions(SourceSystemImpl sourceSystem) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "from "+ConditionImpl.class.getName()+" cond";
		if (sourceSystem != null) {
			clauseTokens.add("cond.sourceSystem = :sourceSystem");
			columnValues.put("sourceSystem", sourceSystem);
		}
		Collection<ConditionImpl> result = buildQuery(table, columnValues, clauseTokens).list();
		return result;
	}

	public Collection<Object[]> findNewTraders(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "select distinct trade.trader, trade.traderName from "+TradeImpl.class.getName()+" trade ";
		if (params != null) {
			if (params.getFromDate() != null) {
				clauseTokens.add("trade.tradeDate >= :fromDate");
				columnValues.put("fromDate", params.getFromDate());
			}
			if (params.getToDate() != null) {
				clauseTokens.add("trade.tradeDate <= :toDate");
				columnValues.put("toDate", params.getToDate());
			}
			if (params.getJobs() != null && params.getJobs().length > 0) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
			if (clauseTokens.isEmpty()) {
				// do not query if no select restiction is specified
				clauseTokens.add("1 = 2");
			}

			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
		}
		clauseTokens.add("trade.trader.employee is null");
		Collection<Object[]> result = buildQuery(table, columnValues, clauseTokens).list();
		return result;
	}
	
		

	public Collection<RequestImpl> findRequests(RequestSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "from "+RequestImpl.class.getName()+" request";
		if (params != null) {
            if (params.getJobs() != null && params.getJobs().length == 0) {
                logger.warn("Returning no data since no jobs are selected");
                return Collections.emptyList(); // no jobs selected
            }
			if (params.getClassName() != null) {
				table = "from "+params.getClassName()+" request";			}
			if (params.getMandant() != null) {
				clauseTokens.add("request.trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (params.getFromDate() != null) {
				clauseTokens.add("request.trade.tradeDate >= :fromDate");
				columnValues.put("fromDate", params.getFromDate());
			}
			if (params.getToDate() != null) {
				clauseTokens.add("request.trade.tradeDate <= :toDate");
				columnValues.put("toDate", params.getToDate());
			}
			if (params.getJobs() != null && params.getJobs().length > 0) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("request.trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
			if (params.isStateNotOkOnly()) {
			    //fetch only empty requests, timeout requests or COMERROR-requests(any error from the dll) 
				clauseTokens.add("(request.requestState like '" + MarketDataRequestStateDef.COMERROR + "%' OR request.requestState = '" + MarketDataRequestStateDef.RETRY + "' OR request.requestState like '" + MarketDataRequestStateDef.TIMEOUT + "%' OR request.requestState is null)");
			}
			if (isNotNullOrZero(params.getState())) {
				clauseTokens.add("request.requestState like :state");
				columnValues.put("state", params.getState() + "%");
			}
		}
		Query query = buildQuery(table, columnValues, clauseTokens);
		if (params != null && params.getMaxResults() > 0) {
			query.setMaxResults(params.getMaxResults());
		}
        Collection<RequestImpl> result = query.list();
        return result;
	}
	
	public Collection<SourceSystemImpl> findSourceSystems(String[] mandantCodes) throws PersistenceException {
		String table = "select source from "+SourceSystemImpl.class.getName()+" source";
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (mandantCodes != null && mandantCodes.length > 0) {
            DecimalFormat df = new DecimalFormat("000");
            StringBuffer s = new StringBuffer("source.mandant.code in (");
            for (int i = 0; i < mandantCodes.length; i++) {
                columnValues.put("mandant" + df.format(i), mandantCodes[i]);
                if (i == 0) {
                    s.append(":mandant").append(df.format(i));                        
                } else {
                    s.append(", :mandant").append(df.format(i));                        
                }
            }
            s.append(")");
            clauseTokens.add(s.toString());
		}
		clauseTokens.add("source.enabled = 'Y'");
		Collection<SourceSystemImpl> result = buildQuery(table, columnValues, clauseTokens," order by source.name").list();
		return result;
	}

	public Collection<PriceCheckCategoryImpl> findPriceCheckCategories(PriceCheckCategorySearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "from "+PriceCheckCategoryImpl.class.getName()+" pcc";
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("pcc.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (isNotNullOrZero(params.getName())) {
				clauseTokens.add("pcc.name like :name");
				columnValues.put("name", params.getName());
			}
	        if (params.isOnlyEnabled()) {
	            clauseTokens.add("pcc.enabled = 'Y'");
	        }
		}
        Collection<PriceCheckCategoryImpl> result = buildQuery(table, columnValues, clauseTokens).list();
        return result;
	}

	public Collection<TradeImpl> findTradeGroups(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String table = "from "+TradeImpl.class.getName()+" trade";
		if (params != null) {
            if (params.getMandant() != null) {
                clauseTokens.add("trade.mandant = :mandant");
                columnValues.put("mandant", params.getMandant());
                table = "from " + getTradeClassForMandantCode(params.getMandant()).getName() + " trade";
            }
			clauseTokens.add("(trade.tradeGroupId != '0'");
			if (params.getClassName() != null) {
				table = "from "+ params.getClassName() +" trade";
			}
			if (isNotNullOrZero(params.getTradeGroupId())) {
				clauseTokens.add("trade.tradeGroupId = :tradeGroupId");
				clauseTokens.add("trade.isTradeGroup = 'Y'");
				columnValues.put("tradeGroupId", params.getTradeGroupId());
			}
			if (params.getFromDate() != null) {
				clauseTokens.add("trade.tradeDate >= :fromDate");
				columnValues.put("fromDate", params.getFromDate());
			}
			if (params.getToDate() != null) {
				clauseTokens.add("trade.tradeDate <= :toDate");
				columnValues.put("toDate", params.getToDate());
			}
			if (params.getJobs() != null && params.getJobs().length > 0) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
		}
		Query query = buildQuery(table, columnValues, clauseTokens, ")");
		Collection<TradeImpl> result = query.list();
		Query query2 = sess.createQuery(table + " where trade.tradeId = :tradeGroupId order by trade.tradeId");
        if (params != null) {
            query2.setParameter("tradeGroupId", params.getTradeGroupId());
        }
        Collection<TradeImpl> result2 = query2.list();
        result.addAll(result2);
		return result;
	}

	public String findMgbConfigurationValue(MandantImpl mandant, String key) throws PersistenceException {
		MgbConfigurationIdImpl id = new MgbConfigurationIdImpl(mandant, key);
		MgbConfigurationImpl config = (MgbConfigurationImpl)sess.select(MgbConfigurationImpl.class, id);
		return config == null ? null : config.getValue();
	}
	

	public TimePeriodCategoryImpl findTimePeriod(MandantImpl mandant, double days) throws PersistenceException {
		Query query = sess.createQuery("from "+TimePeriodCategoryImpl.class.getName()+" tp where tp.minPeriodDays <= :days and  tp.maxPeriodDays > :days and tp.mandant = :mandant");
		query.setParameter("days", new Double(days));
		query.setParameter("mandant", mandant);
		Collection<TimePeriodCategoryImpl> col = query.list();
		if (col.size() > 1) {
			Iterator<TimePeriodCategoryImpl> it = col.iterator();
			StringBuffer sb = new StringBuffer("Multiple TimePeriodCategoryImpl found for the period of " + days + " days. (");
			while (it.hasNext()) {
				sb.append(" " + it.next().getName());
			}
			sb.append(")");
			throw new PersistenceException(sb.toString());
		}
        Iterator<TimePeriodCategoryImpl> it = col.iterator();
        if (it.hasNext()) {
        	return it.next();
        }
        throw new PersistenceException("No TimePeriodCategoryImpl found for the period of " + days + " days.");
	}

	public Collection<TimePeriodCategoryImpl> findAllTimePeriods(MandantImpl mandant) throws PersistenceException {
	    Query query = sess.createQuery("from "+TimePeriodCategoryImpl.class.getName()+" tp where tp.mandant = :mandant");
	    query.setParameter("mandant", mandant);
        Collection<TimePeriodCategoryImpl> result = query.list();
        return result;
	}

    /**
     * @return a map that maps a location to a comma separated list of report configurations
     * @throws PersistenceException
     */
    public Map<String, String> findReportConfigMapping(boolean excludeDummyEntries) throws PersistenceException {
        Map<String, String> result = new HashMap<String, String>();
        String whereClause = "";
        if (excludeDummyEntries) {
            whereClause = " where rep.reportId != 'ADMIN'";
        }
        Query query = sess.createQuery("from "+ReportConfigurationImpl.class.getName()+" rep"+whereClause+ "order by rep.reportId");
        Iterator<ReportConfigurationImpl> reportConfigurations = query.list().iterator();
        while (reportConfigurations.hasNext()) {
            ReportConfigurationImpl reportConfiguration = reportConfigurations.next();
            if (result.containsKey(reportConfiguration.getConfiguration())) {
                String newIds = result.get(reportConfiguration.getConfiguration())+","+reportConfiguration.getReportId();
                result.put(reportConfiguration.getConfiguration(), newIds);
            } else {
                result.put(reportConfiguration.getConfiguration(), reportConfiguration.getReportId());
            }
        }
        return result;
    }

    public Collection<MgbConfigurationImpl> findMgbConfigurations(MgbConfigurationSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("cfg.mgbConfigurationId.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			} 
			if (isNotNullOrZero(params.getKey())) {
				clauseTokens.add("cfg.mgbConfigurationId.key like :key");
				columnValues.put("key", params.getKey());
			}
		}
		Query query = buildQuery("from "+MgbConfigurationImpl.class.getName()+" cfg", columnValues, clauseTokens);
        Collection<MgbConfigurationImpl> result = query.list();
        return result;
	}

	public Collection<Object[]> findUnlinkedTradeAndBook(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
		}
		clauseTokens.add("book.bookId = trade.bookId");
		clauseTokens.add("trade.book is null");
		Query query = buildQuery("select trade, book from "+TradeImpl.class.getName()+" trade, "+BookImpl.class.getName()+" book", columnValues, clauseTokens);
        Collection<Object[]> result = query.list();
        return result;
	}

	public Collection<Object[]> findUnlinkedTradeAndTrader(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
		}
		clauseTokens.add("trader.traderCode = trade.traderId");
		clauseTokens.add("trader.sourceSystem = trade.sourceSystem");
		clauseTokens.add("trade.trader is null");
		Query query = buildQuery("select trade, trader from "+TradeImpl.class.getName()+" trade, "+TraderImpl.class.getName()+" trader", columnValues, clauseTokens);
        Collection<Object[]> result = query.list();
        return result;
	}

	public Collection<Object[]> findUnlinkedTradeAndCounterparty(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
		} 
		clauseTokens.add("counterparty.counterpartyId = trade.counterpartyId");
		clauseTokens.add("counterparty.sourceSystem = trade.sourceSystem");
		clauseTokens.add("trade.counterparty is null");
		Query query = buildQuery("select trade, counterparty from "+TradeImpl.class.getName()+" trade, "+CounterpartyImpl.class.getName()+" counterparty", columnValues, clauseTokens);
        Collection<Object[]> result = query.list();
        return result;
	}

	
	public Collection<Object[]> findNewSourceSystemTrader(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
		}
		clauseTokens.add("not exists (from "+TraderImpl.class.getName()+" trader where trader.traderCode = trade.traderId and trader.sourceSystem = trade.sourceSystem)");
		clauseTokens.add("trade.trader is null");
		Query query = buildQuery("select distinct trade.sourceSystem, trade.traderId from "+TradeImpl.class.getName()+" trade", columnValues, clauseTokens);
        Collection<Object[]> result = query.list();
        return result;
	}

	public Collection<Object[]> findUnlinkedTradeAndInstrument(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
		}
		clauseTokens.add("instrument.instrument = trade.sourceSystemInstrument");
		clauseTokens.add("trade.instrument is null");
		Query query = buildQuery("select trade.longId, instrument from "+TradeImpl.class.getName()+" trade, "+PriceCheckInstrumentImpl.class.getName()+" instrument", columnValues, clauseTokens);
        Collection<Object[]> result = query.list();
        return result;
	}
	
	/**
	 * Searches in trade table for all book ids without an entry in the book table.
	 */
	public Collection<String> findNewSourceSystemBookIds(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String select = "select distinct trade.bookId from "+TradeImpl.class.getName()+" trade";
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
			}
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());
			}
		}
		clauseTokens.add("not exists (from "+BookImpl.class.getName()+" book where book.bookId = trade.bookId)");
		clauseTokens.add("trade.bookId is not null");
		clauseTokens.add("trade.book is null");
		Query query = buildQuery(select, columnValues, clauseTokens);
        Collection<String> result = query.list();
        return result;
	}

	public Collection<Object[]> findNewSourceSystemInstrument(TradeSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String select = "select distinct trade.sourceSystemInstrument, trade.sourceSystemInstrument from "+TradeImpl.class.getName()+" trade";
		String instrumentMandantClause = ""; 
		if (params != null) {
			if (params.getMandant() != null) {
				clauseTokens.add("trade.mandant = :mandant");
				columnValues.put("mandant", params.getMandant());
				instrumentMandantClause = " and instrument.mandant = :mandant ";
			}
			if (params.getJobs() != null) {
                DecimalFormat df = new DecimalFormat("000");
                StringBuffer s = new StringBuffer("trade.job in (");
                for (int i = 0; i < params.getJobs().length; i++) {
                    columnValues.put("job" + df.format(i), params.getJobs()[i]);
                    if (i == 0) {
                        s.append(":job").append(df.format(i));                        
                    } else {
                        s.append(", :job").append(df.format(i));                        
                    }
                }
                s.append(")");
                clauseTokens.add(s.toString());

			}
		}
		select = "select distinct trade.sourceSystemInstrument, trade.instrumentName from "+PrimeEquityImpl.class.getName()+" trade";
		
		clauseTokens.add("not exists (from "+InstrumentImpl.class.getName()+" instrument where instrument.instrument = trade.sourceSystemInstrument"+instrumentMandantClause+")");
		clauseTokens.add("trade.instrument is null");
		Query query = buildQuery(select, columnValues, clauseTokens);
        Collection<Object[]> result = query.list();
        return result;
	}

    /**
     * @return
     */
    public Collection<BookImpl> findBooks() throws PersistenceException {
       Collection<BookImpl> result = (Collection<BookImpl>)sess.select(BookImpl.class);
	   return result;
    }
    
	/**
	 * Finds the first employee assigned to the given NT-ID.
	 * There might be more than.
	 * @param ntId
	 * @return
	 * @throws PersistenceException
	 */
	public EmployeeImpl findFirstEmployeeWithNtId(String ntId, MandantImpl mandant, boolean hasRoles) throws PersistenceException {
		EmployeeSearchParams params = new EmployeeSearchParams();
		params.setMandant(mandant);
		params.setNtId(ntId);
		params.setHasRoles(hasRoles);

		Collection<EmployeeImpl> employees = findEmployees(params);
		logger.debug("Found " + employees.size() + " employees with NT-ID '" + ntId + "'. Mandant = " + mandant);
		Iterator<EmployeeImpl> iterator = employees.iterator();
		if (iterator.hasNext()) {
			return iterator.next();
		}
		
		return null;
	}

	public HashMap<String, String> findCurrencyCategoryMappings(SearchParams params) throws PersistenceException {
		HashMap<String, String> result = new HashMap<String, String>();
		Collection<CurrencyCategoryMappingImpl> col = null;
		if (params != null && params.getMandant() != null) {
			Query query = sess.createQuery("from "+CurrencyCategoryMappingImpl.class.getName()+" map where map.category.mandant = :mandant");
			query.setParameter("mandant", params.getMandant());
			col = query.list();
		} else {
			col = sess.createQuery("from "+CurrencyCategoryMappingImpl.class.getName()+" map").list();
		}
		Iterator<CurrencyCategoryMappingImpl> it = col.iterator();
		while (it.hasNext()) {
			CurrencyCategoryMappingImpl currencyCategoryMapping = it.next();
			if (currencyCategoryMapping != null && currencyCategoryMapping.getCategory() != null) {
				String key = currencyCategoryMapping.getCurrency();
				String value = currencyCategoryMapping.getCategory().getCode();
				logger.debug("key " + key + " is mapped to " + value);
				result.put(key, value);
			}
		}
		return result;
	}

  	public HashMap<String, String> findRatingCategoryMappings(SearchParams params) throws PersistenceException {
		HashMap<String, String> result = new HashMap<String, String>();
		Collection<RatingCategoryMappingImpl> col = null;
		if (params != null && params.getMandant() != null) {
			Query query = sess.createQuery("from "+RatingCategoryMappingImpl.class.getName()+" map where map.category.mandant = :mandant");
			query.setParameter("mandant", params.getMandant());
			col = query.list();
		} else {
			col = sess.createQuery("from "+RatingCategoryMappingImpl.class.getName()+" map").list();
		}
		Iterator<RatingCategoryMappingImpl> it = col.iterator();
		while (it.hasNext()) {
			RatingCategoryMappingImpl ratingCategoryMapping = it.next();
			if (ratingCategoryMapping != null && ratingCategoryMapping.getCategory() != null) {
				String key = ratingCategoryMapping.getRating();
				String value = ratingCategoryMapping.getCategory().getCode();
				logger.debug("key " + key + " is mapped to " + value);
				result.put(key, value);
			}
		}
		return result;
	}

	public HashMap<String, String> findTradeTypeCategoryMappings(SearchParams params) throws PersistenceException {
		HashMap<String, String> result = new HashMap<String, String>();
		Collection<TradeTypeCategoryMappingImpl> col = null;
		if (params != null && params.getMandant() != null) {
			Query query = sess.createQuery("from "+TradeTypeCategoryMappingImpl.class.getName()+" map where map.category.mandant = :mandant");
			query.setParameter("mandant", params.getMandant());
			col = query.list();
		} else {
			col = sess.createQuery("from "+TradeTypeCategoryMappingImpl.class.getName()+" map").list();
		}
		Iterator<TradeTypeCategoryMappingImpl> it = col.iterator();
		while (it.hasNext()) {
			TradeTypeCategoryMappingImpl tradeTypeCategoryMapping = it.next();
			if (tradeTypeCategoryMapping != null && tradeTypeCategoryMapping.getCategory() != null) {
				String key = tradeTypeCategoryMapping.getTradeType();
				String value = tradeTypeCategoryMapping.getCategory().getCode();
				logger.debug("key " + key + " is mapped to " + value);
				result.put(key, value);
			}
		}
		return result;
	}

    public Collection<SummitAmendImpl> findSummitAmends(String tradeId) throws PersistenceException {
       HashMap<String, Object> columnValues = new HashMap<String, Object>();
       ArrayList<String> clauseTokens = new ArrayList<String>();
       if (tradeId != null) {
           clauseTokens.add("amend.tradeIdNew = :tradeId");
           columnValues.put("tradeId", tradeId);
       }
       Query query = buildQuery("select amend from "+SummitAmendImpl.class.getName()+" amend", columnValues, clauseTokens);
       Collection<SummitAmendImpl> result = query.list();
       return result;
    }

	private String array2InClause(String[] array) {
		StringBuffer result = new StringBuffer();
		if (array.length > 0) {
			result.append("'").append(array[0]).append("'");
		}
		for (int i = 1; i < array.length; i++) {
			result.append(",'").append(array[i]).append("'");			
		}
		return result.toString();
	}
	
	protected boolean isNotNullOrZero(Object o) {
		if (o != null) {
			if (o instanceof String) {
				return ((String)o).length() > 0;
			} else if (o instanceof Number) {
				return ((Number)o).intValue() != 0;				
			} else {
				logger.warn("no additional null-check for class "+o.getClass().getName());
				return true;
			}
		}
        return false;
	}
	
	
}