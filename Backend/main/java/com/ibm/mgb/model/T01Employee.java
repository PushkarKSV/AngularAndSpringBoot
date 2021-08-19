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

@Entity
@Table(name = "T01_EMPLOYEE")
public class T01Employee{

	@Id
	@Column(name = "T01_ID", nullable = false)
	private Long t01Id;
	
	@Column(name = "T01_NT_ID")
	private String t01NtId;
	
/*	@Column(name = "FK_T01_T02_MANDANT")
	private String FK_T01_T02_MANDANT;*/
	
	@Column(name = "T01_FIRSTNAME")
	private String t01FirstName;
	
	@Column(name = "T01_LASTNAME")
	private String t01LastName;
	
	@Column(name = "T01_PHONE")
	private String t01Phone;
	
	@Column(name = "T01_EMAIL")
	private String t01Email;
	
	@Column(name = "T01_CHANGED_DATE")
	private Date t01ChangedDate;
	
	@Column(name = "T01_CHANGED_BY")
	private String t01ChangedBy;
	
	@Column(name = "T01_CREATED_BY")
	private String t01CreatedBy;
	
	@Column(name = "T01_CREATION_DATE")
	private String to1CreationDate;
	
	@Column(name = "T01_LAST_LOGIN_DATE")
	private Date t01LastLoginDate;
	
	@OneToMany(mappedBy="t01Employee", fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
	//@JsonManagedReference
	@JsonBackReference
    private List<T17UserRole> t17UserRole;
	
	//added june 16th
	 /*@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "FK_T01_T02_MANDANT")
	//@JsonManagedReference
    @JsonBackReference
	private T09Mandant t09Mandant;

	public T09Mandant getT09Mandant() {
		return t09Mandant;
	}

	public void setT09Mandant(T09Mandant t09Mandant) {
		this.t09Mandant = t09Mandant;
	}*/

	public Long getT01Id() {
		return t01Id;
	}

	public void setT01Id(Long t01Id) {
		this.t01Id = t01Id;
	}

	public String getT01NtId() {
		return t01NtId;
	}

	public void setT01NtId(String t01NtId) {
		this.t01NtId = t01NtId;
	}

	public String getT01FirstName() {
		return t01FirstName;
	}

	public void setT01FirstName(String t01FirstName) {
		this.t01FirstName = t01FirstName;
	}

	public String getT01LastName() {
		return t01LastName;
	}

	public void setT01LastName(String t01LastName) {
		this.t01LastName = t01LastName;
	}

	public String getT01Phone() {
		return t01Phone;
	}

	public void setT01Phone(String t01Phone) {
		this.t01Phone = t01Phone;
	}

	public String getT01Email() {
		return t01Email;
	}

	public void setT01Email(String t01Email) {
		this.t01Email = t01Email;
	}

	public Date getT01ChangedDate() {
		return t01ChangedDate;
	}

	public void setT01ChangedDate(Date t01ChangedDate) {
		this.t01ChangedDate = t01ChangedDate;
	}

	public String getT01ChangedBy() {
		return t01ChangedBy;
	}

	public void setT01ChangedBy(String t01ChangedBy) {
		this.t01ChangedBy = t01ChangedBy;
	}

	public String getT01CreatedBy() {
		return t01CreatedBy;
	}

	public void setT01CreatedBy(String t01CreatedBy) {
		this.t01CreatedBy = t01CreatedBy;
	}

	public String getTo1CreationDate() {
		return to1CreationDate;
	}

	public void setTo1CreationDate(String to1CreationDate) {
		this.to1CreationDate = to1CreationDate;
	}

	public Date getT01LastLoginDate() {
		return t01LastLoginDate;
	}

	public void setT01LastLoginDate(Date t01LastLoginDate) {
		this.t01LastLoginDate = t01LastLoginDate;
	}

	public List<T17UserRole> getT17UserRole() {
		return t17UserRole;
	}

	public void setT17UserRole(List<T17UserRole> t17UserRole) {
		this.t17UserRole = t17UserRole;
	}
	
}
