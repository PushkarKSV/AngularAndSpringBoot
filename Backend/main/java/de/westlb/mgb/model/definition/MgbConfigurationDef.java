package de.westlb.mgb.model.definition;


/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface MgbConfigurationDef {

	public final static String MGB_CONFIGURATION_LIST_DELIMITER = ",";

	public final static String BAGATELLE_LIMIT = "BAGATELLE_LIMIT";
	public final static String TURNOVER_LIMIT = "TURNOUT_LIMIT";
	public final static String BLOOMBERG_INTERVAL_PRICE_DEVIATION_TOLERANCE_FACTOR = "BLOOMBERG_INTERVAL_PRICE_DEVIATION_TOLERANCE_FACTOR";
	public final static String MARKET_DATA_PROXY_DIRECTORY = "MARKET_DATA_PROXY_DIRECTORY";
	public final static String LOOK_AND_FEEL = "LOOK_AND_FEEL";
	public final static String MAX_AUTOCHECK_TRADES = "MAX_AUTOCHECK_TRADES";
	public final static String BOND_BAGATELLE_LIMIT = "BOND_BAGATELLE_LIMIT";
	public final static String BOND_TURNOVER_LIMIT = "BOND_TURNOVER_LIMIT";
	public final static String BOND_BLOOMBERG_DEFAULT_SOURCES = "BOND_BLOOMBERG_DEFAULT_SOURCES";
	public final static String BOND_BLOOMBERG_EXOTIC_SOURCES = "BOND_BLOOMBERG_EXOTIC_SOURCES";
	public final static String BOND_BLOOMBERG_SMALL_CUSTOMER_SOURCES = "BOND_BLOOMBERG_SMALL_CUSTOMER_SOURCES";
	public final static String BOND_EXOTIC_BOOKS = "BOND_EXOTIC_BOOKS";
	public final static String NON_STRUCTURE_TOLERANCE_CATEGORY = "NON_STRUCTURE_TOLERANCE_CATEGORY";
	public final static String WESTLB_NON_STRUCTURE_TOLERANCE_CATEGORY = "WESTLB_NON_STRUCTURE_TOLERANCE_CATEGORY";
	public final static String LATE_ENTRY_RECLAMATION_CODE ="LATE_ENTRY_RECLAMATION_CODE";
    public final static String LATE_TRADE_VOLUME_LIMIT ="LATE_TRADE_VOLUME_LIMIT";
	public final static String REPO_BAGATELLE_LIMIT = "REPO_BAGATELLE_LIMIT";
	public final static String SELO_BAGATELLE_LIMIT = "SELO_BAGATELLE_LIMIT";
    public final static String AWPL_BAGATELLE_LIMIT = "AWPL_BAGATELLE_LIMIT";
    public final static String BWPL_BAGATELLE_LIMIT = "BWPL_BAGATELLE_LIMIT";
	public final static String REPO_TURNOVER_LIMIT = "REPO_TURNOVER_LIMIT";
	public final static String REPO_OPEN_END_DURATION = "REPO_OPEN_END_DURATION";
	public final static String BOOKS_WITH_BACK_TO_BACK_TRADES = "BOOKS_WITH_BACK_TO_BACK_TRADES";
    public final static String MAX_BACK_TO_BACK_SPREAD = "MAX_BACK_TO_BACK_SPREAD";
	public final static String MATURITY_DEPENDENT_COMODITY = "MATURITY_DEPENDENT_COMODITY";
	
	public final static String IRG_BAGATELLE_LIMIT = "IRG_BAGATELLE_LIMIT";
	public final static String SWAP_BAGATELLE_LIMIT = "SWAP_BAGATELLE_LIMIT";
	public final static String FRA_BAGATELLE_LIMIT = "FRA_BAGATELLE_LIMIT";
	public final static String SWAPTION_BAGATELLE_LIMIT = "SWAPTION_BAGATELLE_LIMIT";
	public final static String FXOPT_BAGATELLE_LIMIT = "FXOPT_BAGATELLE_LIMIT";
	public final static String BONDOPT_BAGATELLE_LIMIT = "BONDOPT_BAGATELLE_LIMIT";
	public final static String EXOTIC_BAGATELLE_LIMIT = "EXOTIC_BAGATELLE_LIMIT";
    public final static String DERIVATIVE_TURNOVER_LIMIT = "DERIVATIVE_TURNOVER_LIMIT";

    public final static String DUMMY_BOOKS = "DUMMY_BOOKS";
    public final static String MOSCOW_EXCHANGE_TRADE_COUNTERPARTY = "MOSCOW_EXCHANGE_TRADE_COUNTERPARTY";
	
	public final static String MAIL_CTRL_RECL_SUBJECT = "MAIL_CTR_RECLAMATION.SUBJECT";
	public final static String MAIL_CTRL_RECL_TEXT = "MAIL_CTR_RECLAMATION.TEXT";
	public final static String MAIL_CTRL_RECL_SENDER = "MAIL_CTR_RECLAMATION.SENDER";
	public final static String MAIL_CTRL_RECL_ACKNOWLEDGEMENT = "MAIL_CTR_RECLAMATION.ACKNOWLEDGEMENT";
	
	public final static String MAIL_CTRL_REPLY_SUBJECT = "MAIL_CTR_REPLY.SUBJECT";
	public final static String MAIL_CTRL_REPLY_TEXT = "MAIL_CTR_REPLY.TEXT";
	public final static String MAIL_CTRL_REPLY_SENDER = "MAIL_CTR_REPLY.SENDER";
	public final static String MAIL_CTRL_REPLY_ACKNOWLEDGEMENT = "MAIL_CTR_REPLY.ACKNOWLEDGEMENT";

	public final static String MAIL_TRD_RESPONSE_SUBJECT = "MAIL_TRD_RESPONSE.SUBJECT";
	public final static String MAIL_TRD_RESPONSE_TEXT = "MAIL_TRD_RESPONSE.TEXT";
	public final static String MAIL_TRD_RESPONSE_SENDER = "MAIL_TRD_RESPONSE.SENDER";
	public final static String MAIL_TRD_RESPONSE_ACKNOWLEDGEMENT = "MAIL_TRD_RESPONSE.ACKNOWLEDGEMENT";
	
	public final static String AUTO_MGB_ALLOWED_CLIENT= "AUTO_MGB_ALLOWED_CLIENT";
	public final static String AUTO_MGB_CRON= "AUTO_MGB_CRON";
    public final static String AUTO_MGB_DEFAULT_ALLOWED_CLIENT= "AUTO_MGB_DEFAULT_ALLOWED_CLIENT";
    public final static String AUTO_MGB_DEFAULT_CRON= "AUTO_MGB_DEFAULT_CRON";

	public final static String SAMPLE_GLOBAL_PERCENTAGE= "SAMPLE_GLOBAL_PERCENTAGE";
	public final static String SAMPLE_GLOBAL_STATE= "SAMPLE_GLOBAL_STATE";
	public final static String SAMPLE_BACK_TO_BACK_PERCENTAGE= "SAMPLE_BACK_TO_BACK_PERCENTAGE";

    public static final String SAMPLE_REPORT_LOCATION_PERCENTAGE = "SAMPLE_REPORT_LOCATION_PERCENTAGE";

	public static final String REPO_BOND_MANDANT = "REPO_BOND_MANDANT";
	
}
