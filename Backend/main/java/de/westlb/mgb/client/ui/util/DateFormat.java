package de.westlb.mgb.client.ui.util;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DateFormat extends Format {

	/**
     * 
     */
    private static final long serialVersionUID = 7825098088336757242L;
    public static final int TIME_FORMAT = 0;
	public static final int TIME_FORMAT_LONG = 1;
	public static final int DATE_FORMAT = 2;
	public static final int DATE_FORMAT_LONG = 3;
	public static final int DATETIME_FORMAT = 4;
    public static final int DATETIME_FORMAT_LONG = 5;
    public static final int COMPACT_DATE_FORMAT = 6;

	public static final String TIME_FORMAT_STR = "time";
	public static final String TIME_FORMAT_LONG_STR = "timelong";
	public static final String DATE_FORMAT_STR = "date";
	public static final String DATE_FORMAT_LONG_STR = "datelong";
	public static final String DATETIME_FORMAT_STR = "datetime";
    public static final String DATETIME_FORMAT_LONG_STR = "datetimelong";
    public static final String COMPACT_DATE_FORMAT_STR = "compactdate";
	
	private SimpleDateFormat format;
	private String[] patterns = {
		"dd.MM.yyyy HH:mm",
		"dd.MM.yyyy HH:mm:ss z",
		"dd.MM.yyyy",
		"dd.MM.yyyy z",
		"yyyy.MM.dd_HH:mm:ss,SSS",
		"yyyy.MM.dd_HH:mm:ss,SSS_z",
		"yyyyMMdd"
	};	

    private String MGB_LOCAL_TIME_ZONE = "Europe/Berlin";
    /**
     * Constructor for TimeFormatter.
     */
    public DateFormat(int iFormat) {
        super();
        this.format = new SimpleDateFormat(patterns[iFormat]);
        if (iFormat == 2 || iFormat == 3) {
            this.format.setTimeZone(TimeZone.getTimeZone(MGB_LOCAL_TIME_ZONE));            
        }
    }
 
    public DateFormat(String sFormat) {
        super();
        if (TIME_FORMAT_STR.equals(sFormat)) {
            this.format = new SimpleDateFormat(patterns[TIME_FORMAT]);
        } else if (TIME_FORMAT_LONG_STR.equals(sFormat)) {
            this.format = new SimpleDateFormat(patterns[TIME_FORMAT_LONG]);
        } else if (DATE_FORMAT_STR.equals(sFormat)) {
            this.format = new SimpleDateFormat(patterns[DATE_FORMAT]);
            this.format.setTimeZone(TimeZone.getTimeZone(MGB_LOCAL_TIME_ZONE));
        } else if (DATE_FORMAT_LONG_STR.equals(sFormat)) {
            this.format = new SimpleDateFormat(patterns[DATE_FORMAT_LONG]);
            this.format.setTimeZone(TimeZone.getTimeZone(MGB_LOCAL_TIME_ZONE));
        } else if (DATETIME_FORMAT_STR.equals(sFormat)) {
            this.format = new SimpleDateFormat(patterns[DATETIME_FORMAT]);  
        } else if (DATETIME_FORMAT_LONG_STR.equals(sFormat)) {
            this.format = new SimpleDateFormat(patterns[DATETIME_FORMAT_LONG]);  
        } else if (COMPACT_DATE_FORMAT_STR.equals(sFormat)) {
            this.format = new SimpleDateFormat(patterns[COMPACT_DATE_FORMAT]);  
        } else {
            throw new IllegalArgumentException("Only '"+TIME_FORMAT_STR+"', '"+TIME_FORMAT_LONG_STR+"', '"+DATE_FORMAT_STR+"', '"+DATE_FORMAT_LONG_STR+"', '"+DATETIME_FORMAT_STR+"', '"+DATETIME_FORMAT_LONG_STR+"' or '"+COMPACT_DATE_FORMAT_STR+"' are valid parameter.");
        }
    }

    /**
     * Formats a Date/Calendar into a date/time string.
     * @return the formatted time string.
     */
    public final String formatObject(Object obj) {
    	if (obj instanceof Calendar) {
    		return format.format(((Calendar)obj).getTime());
    	} else if (obj instanceof Date) {
    		return format.format(obj);
    	} else {
	    	return null;
    	}
    }

    public Calendar parse(String dateString) throws ParseException {
    	if (dateString == null) {
    		return null;
    	}
    	GregorianCalendar cal = new GregorianCalendar();
    	cal.setTime(format.parse(dateString));
    	return cal;	
    }
    
    /* (Kein Javadoc)
     * @see java.text.Format#format(java.lang.Object, java.lang.StringBuffer, java.text.FieldPosition)
     */
    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
    	if (obj instanceof Calendar) {
    		format.format(((Calendar)obj).getTime(), toAppendTo, pos);
    	} else {
			format.format(obj, toAppendTo, pos);
    	}
    	
    	return toAppendTo;
    }

    /* (Kein Javadoc)
     * @see java.text.Format#parseObject(java.lang.String, java.text.ParsePosition)
     */
    @Override
    public Object parseObject(String dateString, ParsePosition pos) {
		GregorianCalendar cal = null;
		Date date = format.parse(dateString, pos);
		if (date != null) {
			cal = new GregorianCalendar();
			cal.setTime(date);
		} 
		return cal;	
    }

}
