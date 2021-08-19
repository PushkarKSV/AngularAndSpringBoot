package com.ibm.mgb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@DynamicUpdate
@Table(name = "T05_INSTRUMENT")
public class Instruments {

	@Id
	@Column(name = "T05_ID", nullable = false)
	private int T05_ID;
	
	@Column(name = "T05_INSTRUMENT")
	private String T05_INSTRUMENT;
	
	@Column(name = "T05_INSTRUMENT_NAME")
	private String T05_INSTRUMENT_NAME;
	
	@Column(name = "FK_T05_T11_PRICE_CHECK")
	private int FK_T05_T11_PRICE_CHECK;
	
	@Column(name = "FK_T05_T09_MANDANT")
	private String FK_T05_T09_MANDANT;
	
	@Column(name = "T05_CHANGED_DATE")
	private Date T05_CHANGED_DATE;
	
	@Column(name = "T05_CHANGED_BY")
	private String T05_CHANGED_BY;
	
	@Column(name = "T05_CREATED_BY")
	private String T05_CREATED_BY;
	
	@Column(name = "T05_CREATION_DATE")
	private Date T05_CREATION_DATE;
	
	@Column(name = "T05_BLB_REQUEST_SOURCES")
	private String T05_BLB_REQUEST_SOURCES;
	
	@Column(name = "T05_INSTRUMENT_TYPE")
	private String T05_INSTRUMENT_TYPE;
	
	@Column(name = "T05_ENABLED")
	private char T05_ENABLED;

	public int getT05_ID() {
		return T05_ID;
	}

	public void setT05_ID(int t05_ID) {
		T05_ID = t05_ID;
	}

	public String getT05_INSTRUMENT() {
		return T05_INSTRUMENT;
	}

	public void setT05_INSTRUMENT(String t05_INSTRUMENT) {
		T05_INSTRUMENT = t05_INSTRUMENT;
	}

	public String getT05_INSTRUMENT_NAME() {
		return T05_INSTRUMENT_NAME;
	}

	public void setT05_INSTRUMENT_NAME(String t05_INSTRUMENT_NAME) {
		T05_INSTRUMENT_NAME = t05_INSTRUMENT_NAME;
	}

	public int getFK_T05_T11_PRICE_CHECK() {
		return FK_T05_T11_PRICE_CHECK;
	}

	public void setFK_T05_T11_PRICE_CHECK(int fK_T05_T11_PRICE_CHECK) {
		FK_T05_T11_PRICE_CHECK = fK_T05_T11_PRICE_CHECK;
	}

	public String getFK_T05_T09_MANDANT() {
		return FK_T05_T09_MANDANT;
	}

	public void setFK_T05_T09_MANDANT(String fK_T05_T09_MANDANT) {
		FK_T05_T09_MANDANT = fK_T05_T09_MANDANT;
	}

	public Date getT05_CHANGED_DATE() {
		return T05_CHANGED_DATE;
	}

	public void setT05_CHANGED_DATE(Date t05_CHANGED_DATE) {
		T05_CHANGED_DATE = t05_CHANGED_DATE;
	}

	public String getT05_CHANGED_BY() {
		return T05_CHANGED_BY;
	}

	public void setT05_CHANGED_BY(String t05_CHANGED_BY) {
		T05_CHANGED_BY = t05_CHANGED_BY;
	}

	public String getT05_CREATED_BY() {
		return T05_CREATED_BY;
	}

	public void setT05_CREATED_BY(String t05_CREATED_BY) {
		T05_CREATED_BY = t05_CREATED_BY;
	}

	public Date getT05_CREATION_DATE() {
		return T05_CREATION_DATE;
	}

	public void setT05_CREATION_DATE(Date t05_CREATION_DATE) {
		T05_CREATION_DATE = t05_CREATION_DATE;
	}

	public String getT05_BLB_REQUEST_SOURCES() {
		return T05_BLB_REQUEST_SOURCES;
	}

	public void setT05_BLB_REQUEST_SOURCES(String t05_BLB_REQUEST_SOURCES) {
		T05_BLB_REQUEST_SOURCES = t05_BLB_REQUEST_SOURCES;
	}

	public String getT05_INSTRUMENT_TYPE() {
		return T05_INSTRUMENT_TYPE;
	}

	public void setT05_INSTRUMENT_TYPE(String t05_INSTRUMENT_TYPE) {
		T05_INSTRUMENT_TYPE = t05_INSTRUMENT_TYPE;
	}

	public char getT05_ENABLED() {
		return T05_ENABLED;
	}

	public void setT05_ENABLED(char t05_ENABLED) {
		T05_ENABLED = t05_ENABLED;
	}
}
