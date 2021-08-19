package com.ibm.mgb.model;

public class Instrument {
	private  String t05_instrument;
	private  String t05_instrument_name;
	private String t11_name;
	private String T05_CHANGED_DATE;
	private String T05_CHANGED_BY;
	
	
	public String getT05_CHANGED_DATE() {
		return T05_CHANGED_DATE;
	}
	public void setT05_CHANGED_DATE(String t05_CHANGED_DATE) {
		T05_CHANGED_DATE = t05_CHANGED_DATE;
	}
	public String getT05_CHANGED_BY() {
		return T05_CHANGED_BY;
	}
	public void setT05_CHANGED_BY(String t05_CHANGED_BY) {
		T05_CHANGED_BY = t05_CHANGED_BY;
	}
	public String getT05_instrument() {
		return t05_instrument;
	}
	public void setT05_instrument(String t05_instrument) {
		this.t05_instrument = t05_instrument;
	}
	public String getT05_instrument_name() {
		return t05_instrument_name;
	}
	public void setT05_instrument_name(String t05_instrument_name) {
		this.t05_instrument_name = t05_instrument_name;
	}
	public String getT11_name() {
		return t11_name;
	}
	public void setT11_name(String t11_name) {
		this.t11_name = t11_name;
	}
	
}
