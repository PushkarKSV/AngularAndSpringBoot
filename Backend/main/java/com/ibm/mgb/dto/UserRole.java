package com.ibm.mgb.dto;

import java.util.Date;

public class UserRole {

	private int T17_ID;
	private int FK_T17_T01_EMPLOYEE;
	private String FK_T17_T09_MANDANT;
	private String FK_T17_T29_Role;
	private Date T17_CHANGED_DATE;
	private String T17_CHNAGED_BY;
	private String T17_CREATED_BY;
	private Date T17_CREATION_DATE;
	private String T17_LOCATION;

	public int getT17_ID() {
		return T17_ID;
	}

	public void setT17_ID(int t17_ID) {
		T17_ID = t17_ID;
	}

	public int getFK_T17_T01_EMPLOYEE() {
		return FK_T17_T01_EMPLOYEE;
	}

	public void setFK_T17_T01_EMPLOYEE(int fK_T17_T01_EMPLOYEE) {
		FK_T17_T01_EMPLOYEE = fK_T17_T01_EMPLOYEE;
	}

	public String getFK_T17_T09_MANDANT() {
		return FK_T17_T09_MANDANT;
	}

	public void setFK_T17_T09_MANDANT(String fK_T17_T09_MANDANT) {
		FK_T17_T09_MANDANT = fK_T17_T09_MANDANT;
	}

	public String getFK_T17_T29_Role() {
		return FK_T17_T29_Role;
	}

	public void setFK_T17_T29_Role(String fK_T17_T29_Role) {
		FK_T17_T29_Role = fK_T17_T29_Role;
	}

	public Date getT17_CHANGED_DATE() {
		return T17_CHANGED_DATE;
	}

	public void setT17_CHANGED_DATE(Date t17_CHANGED_DATE) {
		T17_CHANGED_DATE = t17_CHANGED_DATE;
	}

	public String getT17_CHNAGED_BY() {
		return T17_CHNAGED_BY;
	}

	public void setT17_CHNAGED_BY(String t17_CHNAGED_BY) {
		T17_CHNAGED_BY = t17_CHNAGED_BY;
	}

	public String getT17_CREATED_BY() {
		return T17_CREATED_BY;
	}

	public void setT17_CREATED_BY(String t17_CREATED_BY) {
		T17_CREATED_BY = t17_CREATED_BY;
	}

	public Date getT17_CREATION_DATE() {
		return T17_CREATION_DATE;
	}

	public void setT17_CREATION_DATE(Date t17_CREATION_DATE) {
		T17_CREATION_DATE = t17_CREATION_DATE;
	}

	public String getT17_LOCATION() {
		return T17_LOCATION;
	}

	public void setT17_LOCATION(String t17_LOCATION) {
		T17_LOCATION = t17_LOCATION;
	}
}
