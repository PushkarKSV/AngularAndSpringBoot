package com.ibm.mgb.model;

public class Job {
	
	private String T08_ID;
	private String T08_STATUS;
	private String COB;
	private String FK_t08_T09_Mandant;
	private String T02_SRC_TRADE_ID;
	private String T02_BOOK_ID;
	private String T02_COUNTERPARTY_ID;
	private String T02_VOLUME;
	private String T02_PRICE;
	private String T02_SRC_TRADER_NAME;
	private String T02_TRADE_DATE;
	private String T02_AMENDED_DATE;
	private String Fk_t02_T08_job;
	private String FK_T02_T04_MANUAL_STATE;
	public String getFK_T02_T04_MANUAL_STATE() {
		return FK_T02_T04_MANUAL_STATE;
	}
	public void setFK_T02_T04_MANUAL_STATE(String fK_T02_T04_MANUAL_STATE) {
		FK_T02_T04_MANUAL_STATE = fK_T02_T04_MANUAL_STATE;
	}
	public String getT44_LOCATION_TRADER() {
		return T44_LOCATION_TRADER;
	}
	public void setT44_LOCATION_TRADER(String t44_LOCATION_TRADER) {
		T44_LOCATION_TRADER = t44_LOCATION_TRADER;
	}
	private String T44_LOCATION_TRADER;
	public String getFk_t02_T08_job() {
		return Fk_t02_T08_job;
	}
	public void setFk_t02_T08_job(String fk_t02_T08_job) {
		Fk_t02_T08_job = fk_t02_T08_job;
	}
	public String getT08_ID() {
		return T08_ID;
	}
	public void setT08_ID(String t08_ID) {
		T08_ID = t08_ID;
	}
	public String getT08_STATUS() {
		return T08_STATUS;
	}
	public void setT08_STATUS(String t08_STATUS) {
		T08_STATUS = t08_STATUS;
	}
	public String getCOB() {
		return COB;
	}
	public void setCOB(String cOB) {
		COB = cOB;
	}
	public String getFK_t08_T09_Mandant() {
		return FK_t08_T09_Mandant;
	}
	public void setFK_t08_T09_Mandant(String fK_t08_T09_Mandant) {
		FK_t08_T09_Mandant = fK_t08_T09_Mandant;
	}
	public String getT02_SRC_TRADE_ID() {
		return T02_SRC_TRADE_ID;
	}
	public void setT02_SRC_TRADE_ID(String t02_SRC_TRADE_ID) {
		T02_SRC_TRADE_ID = t02_SRC_TRADE_ID;
	}
	public String getT02_BOOK_ID() {
		return T02_BOOK_ID;
	}
	public void setT02_BOOK_ID(String t02_BOOK_ID) {
		T02_BOOK_ID = t02_BOOK_ID;
	}
	public String getT02_COUNTERPARTY_ID() {
		return T02_COUNTERPARTY_ID;
	}
	public void setT02_COUNTERPARTY_ID(String t02_COUNTERPARTY_ID) {
		T02_COUNTERPARTY_ID = t02_COUNTERPARTY_ID;
	}
	public String getT02_VOLUME() {
		return T02_VOLUME;
	}
	public void setT02_VOLUME(String t02_VOLUME) {
		T02_VOLUME = t02_VOLUME;
	}
	public String getT02_PRICE() {
		return T02_PRICE;
	}
	public void setT02_PRICE(String t02_PRICE) {
		T02_PRICE = t02_PRICE;
	}
	public String getT02_SRC_TRADER_NAME() {
		return T02_SRC_TRADER_NAME;
	}
	public void setT02_SRC_TRADER_NAME(String t02_SRC_TRADER_NAME) {
		T02_SRC_TRADER_NAME = t02_SRC_TRADER_NAME;
	}
	public String getT02_TRADE_DATE() {
		return T02_TRADE_DATE;
	}
	public void setT02_TRADE_DATE(String t02_TRADE_DATE) {
		T02_TRADE_DATE = t02_TRADE_DATE;
	}
	public String getT02_AMENDED_DATE() {
		return T02_AMENDED_DATE;
	}
	public void setT02_AMENDED_DATE(String t02_AMENDED_DATE) {
		T02_AMENDED_DATE = t02_AMENDED_DATE;
	}
	

}
