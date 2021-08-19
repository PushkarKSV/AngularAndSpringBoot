package de.westlb.mgb.model.definition;


/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface StateCodeDef {

	public static final String MAN_REQUIRED_BUT_NOT_HANDLED_YET = "MAN_REQUIRED_BUT_NOT_HANDLED_YET";


	public static final String AUTO_ERROR = "AUTO_ERROR";
	public static final String AUTO_NO_CHECK = "AUTO_NO_CHECK";
	public static final String AUTO_EUWAX_CHECK = "AUTO_EUWAX_CHECK";
	public static final String AUTO_HIGH_LOW_CHECK = "AUTO_HIGH_LOW_CHECK";
	public static final String AUTO_HISTORIC_CHECK = "AUTO_HISTORIC_CHECK";
	public static final String AUTO_HISTORIC_CORP_CHECK = "AUTO_HISTORIC_CORP_CHECK";
	public static final String AUTO_SOURCE_REQUEST_CHECK = "AUTO_SOURCE_REQUEST_CHECK";
	public static final String AUTO_HIGH_LOW_SOURCE_REQUEST_CHECK = "AUTO_HIGH_LOW_SOURCE_REQUEST_CHECK";
	public static final String AUTO_MANUAL_CHECK = "AUTO_MANUAL_CHECK";
	public static final String AUTO_MANUAL_INTERNAL_CHECK = "AUTO_MANUAL_INTERNAL_CHECK";
	public static final String AUTO_OK = "AUTO_OK";
	public static final String AUTO_OUT_OF_PRICE = "AUTO_OUT_OF_PRICE";
	public static final String AUTO_OUT_OF_TIME = "AUTO_OUT_OF_TIME";
	public static final String AUTO_OUT_OF_PRICE_AND_TIME = "AUTO_OUT_OF_PRICE_AND_TIME";
	public static final String AUTO_NO_PRICE = "AUTO_NO_PRICE";
	public static final String AUTO_LATE = "AUTO_LATE";
	public static final String AUTO_OUT_OF_PRICE_AND_LATE = "AUTO_OUT_OF_PRICE_AND_LATE";
	public static final String AUTO_OUT_OF_TIME_AND_LATE = "AUTO_OUT_OF_TIME_AND_LATE";
	public static final String AUTO_OUT_OF_PRICE_AND_TIME_AND_LATE = "AUTO_OUT_OF_PRICE_AND_TIME_AND_LATE";
	public static final String AUTO_NO_PRICE_AND_LATE = "AUTO_NO_PRICE_AND_LATE";
	public static final String AUTO_BUY_SELL_IDENTICAL = "AUTO_BUY_SELL_IDENTICAL";
	public static final String AUTO_WARRANT = "AUTO_WARRANT";
	public static final String AUTO_BOND_INSTRUMENT = "AUTO_BOND_INSTRUMENT";
	public static final String AUTO_SELO_INSTRUMENT = "AUTO_SELO_INSTRUMENT";
	public static final String AUTO_DEPOSIT_INSTRUMENT = "AUTO_DEPOSIT_INSTRUMENT";
	public static final String AUTO_BACK_TO_BACK = "AUTO_BACK_TO_BACK";
	public static final String AUTO_BACK_TO_BACK_CHECK = "AUTO_BACK_TO_BACK_CHECK";
	public static final String AUTO_BACK_TO_BACK_BLB_CHECK = "AUTO_BACK_TO_BACK_BLB_CHECK";
    public static final String AUTO_IPO = "AUTO_IPO";
    public static final String AUTO_INTERNAL_BOND_TRADE = "AUTO_INTERNAL_BOND_TRADE";
    public static final String AUTO_UMBUCHUNG = "AUTO_UMBUCHUNG";
	
	public static final String AUTO_MITTE_BOOK = "AUTO_MITTE_BOOK";
	public static final String AUTO_NONEURO_MITTE_BOOK = "AUTO_NONEURO_MITTE_BOOK";
	public static final String AUTO_NEW_FX_OPTION_FORWARD = "AUTO_NEW_FX_OPTION_FORWARD";
	public static final String AUTO_NEW_FX_DRAWDOWN = "AUTO_NEW_FX_DRAWDOWN";
	public static final String AUTO_HISTORY_INFO = "AUTO_HISTORY_INFO";
    public static final String AUTO_GL_FUNDING = "AUTO_GL_FIXING";
    public static final String AUTO_EONIA_FIXING = "AUTO_EONIA_FIXING";
	public static final String AUTO_FLOATING_DEPOSIT = "AUTO_FLOATING_DEPOSIT";
	public static final String AUTO_TECHNICAL_USER = "AUTO_TECHNICAL_USER";
	public static final String AUTO_PSEUDO_BOOK = "AUTO_PSEUDO_BOOK";
	public static final String AUTO_NO_REUTERS_PRICE_MM = "AUTO_NO_REUTERS_PRICE_MM";
	public static final String AUTO_NO_REUTERS_PRICE_FX = "AUTO_NO_REUTERS_PRICE_FX";
	public static final String AUTO_OUT_OF_REUTERS_PRICE_MM = "AUTO_OUT_OF_REUTERS_PRICE_MM";
	public static final String AUTO_OUT_OF_REUTERS_PRICE_FX = "AUTO_OUT_OF_REUTERS_PRICE_FX";
	public static final String AUTO_REUTERS_HIGH_TURN_OVER = "AUTO_REUTERS_HIGH_TURN_OVER";
	public static final String AUTO_BLOOMBERG_HIGH_TURN_OVER = "AUTO_BLOOMBERG_HIGH_TURN_OVER";
	public static final String AUTO_HIGH_TURN_OVER = "AUTO_HIGH_TURN_OVER";

	public static final String AUTO_REUTERS_FAILED = "AUTO_REUTERS_FAILED";
	public static final String AUTO_REUTERS_OK_BUT_CHECK = "AUTO_REUTERS_OK_BUT_CHECK";
	public static final String AUTO_REUTERS_OK = "AUTO_REUTERS_OK";
	public static final String AUTO_BLOOMBERG_OK_BUT_CHECK = "AUTO_BLOOMBERG_OK_BUT_CHECK";
	public static final String AUTO_BLOOMBERG_OK = "AUTO_BLOOMBERG_OK";
	public static final String AUTO_THEORETICAL_OK_BUT_CHECK = "AUTO_THEORETICAL_OK_BUT_CHECK";
	public static final String AUTO_THEORETICAL_OK = "AUTO_THEORETICAL_OK";
	public static final String AUTO_PRICE_FAILED = "AUTO_PRICE_FAILED";
	public static final String AUTO_BAGATELLE_BUT_CHECK = "AUTO_BAGATELLE_BUT_CHECK";
	public static final String AUTO_FO_CONFIRMED = "AUTO_FO_CONFIRMED";
	public static final String AUTO_OLD_VERSION = "AUTO_OLD_VERSION";
	public static final String AUTO_ALREADY_CHECK_OLDER_VERSION = "AUTO_ALREADY_CHECK_OLDER_VERSION";
	public static final String AUTO_SELO = "AUTO_SELO";
	public static final String AUTO_COLL = "AUTO_COLL";

	public static final String AUTO_SMALL_RATE = "AUTO_SMALL_RATE";
	public static final String AUTO_SELO_PASSED = "AUTO_SELO_PASSED";
	public static final String AUTO_SELO_BAGATELLE = "AUTO_SELO_BAGATELLE";
	public static final String AUTO_SELO_TURNOVER = "AUTO_SELO_TURNOVER";
	public static final String AUTO_SELO_FAILED = "AUTO_SELO_FAILED";
	public static final String AUTO_REPO_PASSED = "AUTO_REPO_PASSED";
	public static final String AUTO_REPO_BAGATELLE = "AUTO_REPO_BAGATELLE";
	public static final String AUTO_REPO_TURNOVER = "AUTO_REPO_TURNOVER";
	public static final String AUTO_REPO_FAILED = "AUTO_REPO_FAILED";	
	public static final String AUTO_AWPL_PASSED = "AUTO_AWPL_PASSED";
	public static final String AUTO_AWPL_BAGATELLE = "AUTO_AWPL_BAGATELLE";
	public static final String AUTO_AWPL_TURNOVER = "AUTO_AWPL_TURNOVER";
	public static final String AUTO_FAILED_UNDERLYING_AND_RATE = "AUTO_FAILED_UNDERLYING_AND_RATE";
	public static final String AUTO_FAILED_UNDERLYING = "AUTO_FAILED_UNDERLYING";
    public static final String AUTO_FAILED_RATE = "AUTO_FAILED_RATE";
    public static final String AUTO_TERMINIERUNG = "AUTO_TERMINIERUNG";
	
	public static final String AUTO_IRG_PASSED = "AUTO_IRG_PASSED";
	public static final String AUTO_IRG_BAGATELLE = "AUTO_IRG_BAGATELLE";
	public static final String AUTO_IRG_NON_STD_FAILED = "AUTO_IRG_NON_STD_FAILED";
	public static final String AUTO_IRG_FAILED = "AUTO_IRG_FAILED";
	public static final String AUTO_SWAP_PASSED = "AUTO_SWAP_PASSED";
	public static final String AUTO_SWAP_BAGATELLE = "AUTO_SWAP_BAGATELLE";
	public static final String AUTO_SWAP_NON_STD_FAILED = "AUTO_SWAP_NON_STD_FAILED";
	public static final String AUTO_SWAP_FAILED = "AUTO_SWAP_FAILED";
	public static final String AUTO_FRA_PASSED = "AUTO_FRA_PASSED";
	public static final String AUTO_FRA_BAGATELLE = "AUTO_FRA_BAGATELLE";
	public static final String AUTO_FRA_FAILED = "AUTO_FRA_FAILED";
	public static final String AUTO_SWOPT_PASSED = "AUTO_SWOPT_PASSED";
	public static final String AUTO_SWOPT_BAGATELLE = "AUTO_SWOPT_BAGATELLE";
	public static final String AUTO_SWOPT_NON_STD_FAILED = "AUTO_SWOPT_NON_STD_FAILED";
	public static final String AUTO_SWOPT_FAILED = "AUTO_SWOPT_FAILED";
	public static final String AUTO_FXOPT_EXPIRED_EXERCISED = "AUTO_FXOPT_EXPIRED_EXERCISED";
	public static final String AUTO_FXOPT_PASSED = "AUTO_FXOPT_PASSED";
	public static final String AUTO_FXOPT_BAGATELLE = "AUTO_FXOPT_BAGATELLE";
	public static final String AUTO_FXOPT_NON_STD_FAILED = "AUTO_FXOPT_NON_STD_FAILED";
	public static final String AUTO_FXOPT_FAILED = "AUTO_FXOPT_FAILED";
	public static final String AUTO_FXOPT_NO_CHECK = "AUTO_FXOPT_NO_CHECK";
    public static final String AUTO_EXOTIC_PASSED = "AUTO_EXOTIC_PASSED";
	public static final String AUTO_EXOTIC_BAGATELLE = "AUTO_EXOTIC_BAGATELLE";
	public static final String AUTO_EXOTIC_NON_STD_FAILED = "AUTO_EXOTIC_NON_STD_FAILED";
	public static final String AUTO_EXOTIC_FAILED = "AUTO_EXOTIC_FAILED";
	public static final String AUTO_EARLY_TERMINATION = "AUTO_EARLY_TERMINATION";
	public static final String AUTO_MICEX_TRADE = "AUTO_MICEX_TRADE";
    public static final String AUTO_BANK_D_ORSAY_BOOK = "AUTO_BANK_D_ORSAY_BOOK";
    public static final String AUTO_RATE_RESET = "AUTO_RATE_RESET";
    public static final String AUTO_DUMMY_BOOK = "AUTO_DUMMY_BOOK";

	
	public static final String SAMPLE_NO_CHECK = "SAMPLE_NO_CHECK";

	public static final String MAN_BLOOMBERG = "MAN_BLOOMBERG";
	public static final String MAN_HIGH_VOLATILITY_IPO = "MAN_HIGH_VOLATILITY_IPO";
	public static final String MAN_EXERCISE = "MAN_EXERCISE";
	public static final String MAN_HIGH_VOLUME_AVERAGEPRICE = "MAN_HIGH_VOLUME_AVERAGEPRICE";
	public static final String MAN_EUREX_OPTION_EXPIRY = "MAN_EUREX_OPTION_EXPIRY";
	public static final String MAN_NO_CHECK_RANDOM = "MAN_NO_CHECK_RANDOM";
	public static final String MAN_NET_PRICE = "MAN_NET_PRICE";
	public static final String MAN_MISCELLANEOUS = "MAN_MISCELLANEOUS";
	public static final String MAN_INTERNAL_REBOOKING = "MAN_INTERNAL_REBOOKING";
	public static final String MAN_CLARIFIED_WITH_TRADER = "MAN_CLARIFIED_WITH_TRADER";
	public static final String MAN_GREY_MARKET = "MAN_GREY_MARKET";
	public static final String MAN_SMALL_VOLUMNE = "MAN_SMALL_VOLUMNE";
	public static final String MAN_IPO_PRICE = "MAN_IPO_PRICE";
	public static final String MAN_MANUAL_STORNO = "MAN_MANUAL_STORNO";
	public static final String MAN_RC = "MAN_RC";
	public static final String MAN_HI_LO = "MAN_HI_LO";
	public static final String MAN_FIDESSA = "MAN_FIDESSA";
	public static final String MAN_OPTION_EXPIRY = "MAN_OPTION_EXPIRY";
	public static final String MAN_SINGAPORE_TRADE = "MAN_SINGAPORE_TRADE";

	public static final String MAN_OUTSIDE_OTC_CHECK = "MAN_OUTSIDE_OTC_CHECK";
	public static final String MAN_WESTLB_OPTION = "MAN_WESTLB_OPTION";
	public static final String MAN_HIGH_VOLATILITY = "MAN_HIGH_VOLATILITY";
	public static final String MAN_SMILE = "MAN_SMILE";
	public static final String MAN_TRADELINK_TRADE = "MAN_TRADELINK_TRADE";

	public static final String MAN_RECLAIMED = "MAN_RECLAIMED";
	public static final String MAN_OUTSIDE_TOOL = "MAN_OUTSIDE_TOOL";
	public static final String MAN_IS_MARKET_CONFORM = "MAN_IS_MARKET_CONFORM";

	public static final String MAN_ALLREADY_CORRECTED = "MAN_ALLREADY_CORRECTED";
	public static final String MAN_TO_BE_NEGLECTED = "MAN_TO_BE_NEGLECTED";
	public static final String MAN_EURIBOR_PLUS_TRADE = "MAN_EURIBOR_PLUS_TRADE";
	public static final String MAN_OPT_FRD_EQ_DRAWDOWN = "MAN_OPT_FRD_EQ_DRAWDOWN";
	public static final String MAN_LATE_ENTRY = "MAN_LATE_ENTRY";
	public static final String MAN_FX_POINTS_OK = "MAN_FX_POINTS_OK";
	public static final String MAN_POSITION_RATE = "MAN_POSITION_RATE";
	public static final String MAN_RATE_PROLONGATION = "MAN_RATE_PROLONGATION";
	public static final String MAN_STORNO = "MAN_STORNO";
	
	public static final String RECL_OPEN = "OPEN";
	public static final String RECL_WAITING = "WAITING";
	public static final String RECL_CLOSED = "CLOSED";
	public static final String RECL_CHECK_MARKET_PRICE = "CHECK_MARKET_PRICE";
	public static final String RECL_CHECK_TRADE_DATA = "CHECK_TRADE_DATA";
	public static final String RECL_LATE_ENTRY = "RECL_LATE_ENTRY";
    public static final String RECL_LOCAL_CHECK = "RECL_LOCAL_CHECK";

	// REPO
	public static final String AUTO_BAGATELLE = "Bagatelle";
	public static final String AUTO_BAGATELLE_AND_LATE = "AUTO_BAGATELLE_AND_LATE";
	public static final String AUTO_FAILED = "Failed";
	public static final String AUTO_FISHY_STORNO = "Fishy_Storno";
	public static final String AUTO_OLD_STORNO = "Old_Storno";
	public static final String AUTO_PASSED = "Passed";
	public static final String AUTO_STORNO = "Storno";

}
