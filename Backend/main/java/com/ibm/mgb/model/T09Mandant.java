package com.ibm.mgb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "T09_MANDANT")
public class T09Mandant {

	@Id
	@Column(name = "T09_ID", nullable = false)
	private String t09Id;
	
	@Column(name = "T09_NAME")
	private String t09Name;
	
	@Column(name = "T09_CHANGED_DATE")
	private Date t09ChangedDate;
	
	@Column(name = "T09_CHANGED_BY")
	private String t09ChangedBy;
	
	@Column(name = "T09_CREATED_BY")
	private String t09CreatedBy;
	
	@Column(name = "T09_CREATION_DATE")
	private Date t09CreationDate;
	
	@Column(name = "T09_Enabled")
	private char t09Enabled;
	
	@Column(name = "T09_CLIENT")
	private char t09Client;
	
	@Column(name = "T09_PRODUCT_CLASS")
	private String t09ProductClass;
	
	@Column(name = "T09_LOCATION_OVERRIDE")
	private String t09LocationOverride;
	
	@Column(name = "AUTO_CHECK")
	private char autoCheck;
	
	@OneToMany(mappedBy="t09Mandant",fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL
            )
	@JsonBackReference
	//@JsonManagedReference
    private List<T17UserRole> t17UserRole;
	
	//added june 16th
	/*@OneToMany(mappedBy="t09Mandant",fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL
            )
	//@JsonBackReference
	@JsonManagedReference
    private List<T01Employee> t01Emploee;
	
	public List<T01Employee> getT01Emploee() {
		return t01Emploee;
	}

	public void setT01Emploee(List<T01Employee> t01Emploee) {
		this.t01Emploee = t01Emploee;
	}
*/
	public String getT09Id() {
		return t09Id;
	}

	public void setT09Id(String t09Id) {
		this.t09Id = t09Id;
	}

	public String getT09Name() {
		return t09Name;
	}

	public void setT09Name(String t09Name) {
		this.t09Name = t09Name;
	}

	public Date getT09ChangedDate() {
		return t09ChangedDate;
	}

	public void setT09ChangedDate(Date t09ChangedDate) {
		this.t09ChangedDate = t09ChangedDate;
	}

	public String getT09ChangedBy() {
		return t09ChangedBy;
	}

	public void setT09ChangedBy(String t09ChangedBy) {
		this.t09ChangedBy = t09ChangedBy;
	}

	public String getT09CreatedBy() {
		return t09CreatedBy;
	}

	public void setT09CreatedBy(String t09CreatedBy) {
		this.t09CreatedBy = t09CreatedBy;
	}

	public Date getT09CreationDate() {
		return t09CreationDate;
	}

	public void setT09CreationDate(Date t09CreationDate) {
		this.t09CreationDate = t09CreationDate;
	}

	public char getT09Enabled() {
		return t09Enabled;
	}

	public void setT09Enabled(char t09Enabled) {
		this.t09Enabled = t09Enabled;
	}

	public char getT09Client() {
		return t09Client;
	}

	public void setT09Client(char t09Client) {
		this.t09Client = t09Client;
	}

	public String getT09ProductClass() {
		return t09ProductClass;
	}

	public void setT09ProductClass(String t09ProductClass) {
		this.t09ProductClass = t09ProductClass;
	}

	public String getT09LocationOverride() {
		return t09LocationOverride;
	}

	public void setT09LocationOverride(String t09LocationOverride) {
		this.t09LocationOverride = t09LocationOverride;
	}

	public char getAutoCheck() {
		return autoCheck;
	}

	public void setAutoCheck(char autoCheck) {
		this.autoCheck = autoCheck;
	}

	public List<T17UserRole> getT17UserRole() {
		return t17UserRole;
	}

	public void setT17UserRole(List<T17UserRole> t17UserRole) {
		this.t17UserRole = t17UserRole;
	}

}
