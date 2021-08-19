package com.ibm.mgb.model;

public class Configuration {
	private String key;
	private String value;
	private String FK_T42_T09_MANDANT;
	private String T42_CHANGED_DATE;
	public String getFK_T42_T09_MANDANT() {
		return FK_T42_T09_MANDANT;
	}
	public void setFK_T42_T09_MANDANT(String fK_T42_T09_MANDANT) {
		FK_T42_T09_MANDANT = fK_T42_T09_MANDANT;
	}
	public String getT42_CHANGED_DATE() {
		return T42_CHANGED_DATE;
	}
	public void setT42_CHANGED_DATE(String t42_CHANGED_DATE) {
		T42_CHANGED_DATE = t42_CHANGED_DATE;
	}
	private String changedBy;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	

}
