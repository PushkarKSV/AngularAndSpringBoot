package com.ibm.mgb.model;

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
@Table(name = "T08_JOB")
public class T08_Job {

	@Id
	@Column(name = "T08_ID", nullable = false)
	private String T08_ID;
	
//	@Column(name = "T08_START_LOAD_TIME")
//	private Calendar T08_START_LOAD_TIME;
//	
//	@Column(name = "T08_STOP_LOAD_TIME")
//	private Calendar T08_STOP_LOAD_TIME;
//	
//	@Column(name = "T08_START_CONVERT_TIME")
//	private Calendar T08_START_CONVERT_TIME;
//	
//	@Column(name = "T08_STOP_CONVERT_TIME")
//	private Calendar T08_STOP_CONVERT_TIME;
//	
//	@Column(name = "T08_START_BUSINESS_TIME")
//	private Calendar T08_START_BUSINESS_TIME;
//	
//	@Column(name = "T08_STOP_BUSINESS_TIME")
//	private Calendar T08_STOP_BUSINESS_TIME;
//	
//	@Column(name = "T08_SYSTEM_TIME")
//	private Calendar T08_SYSTEM_TIME;
//	
	@Column(name = "T08_STATUS")
	private String T08_STATUS;
//	
//	@Column(name = "T08_TOTAL_RECORDS")
//	private int T08_TOTAL_RECORDS;
//	
//	@Column(name = "T08_LOAD_ERRORS")
//	private int T08_LOAD_ERRORS;
//	
//	@Column(name = "T08_CONVERT_ERRORS")
//	private int T08_CONVERT_ERRORS;
//	
	@Column(name = "FK_T08_T09_MANDANT")
	private String FK_T08_T09_MANDANT;
//	
//	@Column(name = "FK_T08_T07_SOURCE_SYSTEM")
//	private String FK_T08_T07_SOURCE_SYSTEM;
//	
//	@Column(name = "T08_CHANGED_DATE")
//	private Calendar T08_CHANGED_DATE;
//	
//	@Column(name = "T08_CHANGED_BY")
//	private String T08_CHANGED_BY;
//	
//	@Column(name = "T08_CREATED_BY")
//	private String T08_CREATED_BY;
//	
//	@Column(name = "T08_CREATION_DATE")
//	private Calendar T08_CREATION_DATE;
//	
//	@Column(name = "T08_ARCHIVED")
//	private char T08_ARCHIVED;
//	
//	@Column(name = "T08_ARCHIVE_FILE")
//	private String T08_ARCHIVE_FILE;
//	
//	@Column(name = "T08_IMPORT_FILE")
//	private String T08_IMPORT_FILE;
//	
	@Column(name = "COB")
	private String COB;
	
//	@Column(name = "T08_COB")
//	private Integer T08_COB;
//	
	public String getT08_ID() {
		return T08_ID;
	}

	public void setT08_ID(String string) {
		T08_ID = string;
	}

//	public Calendar getT08_START_LOAD_TIME() {
//		return T08_START_LOAD_TIME;
//	}
//
//	public void setT08_START_LOAD_TIME(Calendar t08_START_LOAD_TIME) {
//		T08_START_LOAD_TIME = t08_START_LOAD_TIME;
//	}
//
//	public Calendar getT08_STOP_LOAD_TIME() {
//		return T08_STOP_LOAD_TIME;
//	}
//
//	public void setT08_STOP_LOAD_TIME(Calendar t08_STOP_LOAD_TIME) {
//		T08_STOP_LOAD_TIME = t08_STOP_LOAD_TIME;
//	}
//
//	public Calendar getT08_START_CONVERT_TIME() {
//		return T08_START_CONVERT_TIME;
//	}
//
//	public void setT08_START_CONVERT_TIME(Calendar t08_START_CONVERT_TIME) {
//		T08_START_CONVERT_TIME = t08_START_CONVERT_TIME;
//	}
//
//	public Calendar getT08_STOP_CONVERT_TIME() {
//		return T08_STOP_CONVERT_TIME;
//	}
//
//	public void setT08_STOP_CONVERT_TIME(Calendar t08_STOP_CONVERT_TIME) {
//		T08_STOP_CONVERT_TIME = t08_STOP_CONVERT_TIME;
//	}
//
//	public Calendar getT08_START_BUSINESS_TIME() {
//		return T08_START_BUSINESS_TIME;
//	}
//
//	public void setT08_START_BUSINESS_TIME(Calendar t08_START_BUSINESS_TIME) {
//		T08_START_BUSINESS_TIME = t08_START_BUSINESS_TIME;
//	}
//
//	public Calendar getT08_STOP_BUSINESS_TIME() {
//		return T08_STOP_BUSINESS_TIME;
//	}
//
//	public void setT08_STOP_BUSINESS_TIME(Calendar t08_STOP_BUSINESS_TIME) {
//		T08_STOP_BUSINESS_TIME = t08_STOP_BUSINESS_TIME;
//	}
//
//	public Calendar getT08_SYSTEM_TIME() {
//		return T08_SYSTEM_TIME;
//	}
//
//	public void setT08_SYSTEM_TIME(Calendar t08_SYSTEM_TIME) {
//		T08_SYSTEM_TIME = t08_SYSTEM_TIME;
//	}
//
	public String getT08_STATUS() {
		return T08_STATUS;
	}

	public void setT08_STATUS(String t08_STATUS) {
		T08_STATUS = t08_STATUS;
	}
//
//	public int getT08_TOTAL_RECORDS() {
//		return T08_TOTAL_RECORDS;
//	}
//
//	public void setT08_TOTAL_RECORDS(int t08_TOTAL_RECORDS) {
//		T08_TOTAL_RECORDS = t08_TOTAL_RECORDS;
//	}
//
//	public int getT08_LOAD_ERRORS() {
//		return T08_LOAD_ERRORS;
//	}
//
//	public void setT08_LOAD_ERRORS(int t08_LOAD_ERRORS) {
//		T08_LOAD_ERRORS = t08_LOAD_ERRORS;
//	}
//
//	public int getT08_CONVERT_ERRORS() {
//		return T08_CONVERT_ERRORS;
//	}
//
//	public void setT08_CONVERT_ERRORS(int t08_CONVERT_ERRORS) {
//		T08_CONVERT_ERRORS = t08_CONVERT_ERRORS;
//	}
//
	public String getFK_T08_T09_MANDANT() {
		return FK_T08_T09_MANDANT;
	}

	public void setFK_T08_T09_MANDANT(String fK_T08_T09_MANDANT) {
		FK_T08_T09_MANDANT = fK_T08_T09_MANDANT;
	}
//
//	public String getFK_T08_T07_SOURCE_SYSTEM() {
//		return FK_T08_T07_SOURCE_SYSTEM;
//	}
//
//	public void setFK_T08_T07_SOURCE_SYSTEM(String fK_T08_T07_SOURCE_SYSTEM) {
//		FK_T08_T07_SOURCE_SYSTEM = fK_T08_T07_SOURCE_SYSTEM;
//	}
//
//	public Calendar getT08_CHANGED_DATE() {
//		return T08_CHANGED_DATE;
//	}
//
//	public void setT08_CHANGED_DATE(Calendar t08_CHANGED_DATE) {
//		T08_CHANGED_DATE = t08_CHANGED_DATE;
//	}
//
//	public String getT08_CHANGED_BY() {
//		return T08_CHANGED_BY;
//	}
//
//	public void setT08_CHANGED_BY(String t08_CHANGED_BY) {
//		T08_CHANGED_BY = t08_CHANGED_BY;
//	}
//
//	public String getT08_CREATED_BY() {
//		return T08_CREATED_BY;
//	}
//
//	public void setT08_CREATED_BY(String t08_CREATED_BY) {
//		T08_CREATED_BY = t08_CREATED_BY;
//	}
//
//	public Calendar getT08_CREATION_DATE() {
//		return T08_CREATION_DATE;
//	}
//
//	public void setT08_CREATION_DATE(Calendar t08_CREATION_DATE) {
//		T08_CREATION_DATE = t08_CREATION_DATE;
//	}
//
//	public char getT08_ARCHIVED() {
//		return T08_ARCHIVED;
//	}
//
//	public void setT08_ARCHIVED(char t08_ARCHIVED) {
//		T08_ARCHIVED = t08_ARCHIVED;
//	}
//
//	public String getT08_ARCHIVE_FILE() {
//		return T08_ARCHIVE_FILE;
//	}
//
//	public void setT08_ARCHIVE_FILE(String t08_ARCHIVE_FILE) {
//		T08_ARCHIVE_FILE = t08_ARCHIVE_FILE;
//	}
//
//	public String getT08_IMPORT_FILE() {
//		return T08_IMPORT_FILE;
//	}
//
//	public void setT08_IMPORT_FILE(String t08_IMPORT_FILE) {
//		T08_IMPORT_FILE = t08_IMPORT_FILE;
//	}
//
	public String getCOB() {
		return COB;
	}

	public void setCOB(String string) {
		COB = string;
	}
//
//	public Integer getT08_COB() {
//		return T08_COB;
//	}
//
//	public void setT08_COB(Integer t08_COB) {
//		T08_COB = t08_COB;
//	}


}
