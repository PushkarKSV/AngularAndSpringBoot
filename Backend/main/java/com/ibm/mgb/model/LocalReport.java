package com.ibm.mgb.model;
import java.util.Date;


public class LocalReport {
	private  String datefilter;
	private  Date datefrom;
	private Date dateto;
	private String marketdata;
	private String reportLocation;
	private String reporttype;
	public String getDatefilter() {
		return datefilter;
	}
	public void setDatefilter(String datefilter) {
		this.datefilter = datefilter;
	}
	public Date getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}
	public Date getDateto() {
		return dateto;
	}
	public void setDateto(Date dateto) {
		this.dateto = dateto;
	}
	public String getMarketdata() {
		return marketdata;
	}
	public void setMarketdata(String marketdata) {
		this.marketdata = marketdata;
	}
	public String getReportLocation() {
		return reportLocation;
	}
	public void setReportLocation(String reportLocation) {
		this.reportLocation = reportLocation;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	}
