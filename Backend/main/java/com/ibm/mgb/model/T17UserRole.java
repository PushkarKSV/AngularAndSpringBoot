package com.ibm.mgb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "T17_USER_ROLE")
public class T17UserRole {

	@Id
	@Column(name = "T17_ID", nullable = false)
	private int t17Id;
	
	/*@Column(name = "FK_T17_T01_EMPLOYEE")
	private int FK_T17_T01_EMPLOYEE;*/
	/*
	@Column(name = "FK_T17_T09_MANDANT")
	private String FK_T17_T09_MANDANT;*/
	
	@Column(name = "FK_T17_T29_Role")
	private String fkT17T29Role;
	
	@Column(name = "T17_CHANGED_DATE")
	private Date t17ChangedDate;
	
	@Column(name = "T17_CHANGED_BY")
	private String t17ChangedBy;
	
	@Column(name = "T17_CREATED_BY")
	private String t17CreatedBy;
	
	@Column(name = "T17_CREATION_DATE")
	private Date t17CreationDate;
	
	@Column(name = "T17_LOCATION")
	private String t17Location;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_T17_T09_MANDANT")
	@JsonBackReference
    private T09Mandant t09Mandant;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_T17_T01_EMPLOYEE")
    @JsonBackReference
    private T01Employee t01Employee;*/
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_T17_T09_MANDANT")
	@JsonManagedReference
    //@JsonBackReference
    private T09Mandant t09Mandant;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_T17_T01_EMPLOYEE")
    //@JsonBackReference
    @JsonManagedReference
    private T01Employee t01Employee;

	public int getT17Id() {
		return t17Id;
	}

	public void setT17Id(int t17Id) {
		this.t17Id = t17Id;
	}

	public String getFkT17T29Role() {
		return fkT17T29Role;
	}

	public void setFkT17T29Role(String fkT17T29Role) {
		this.fkT17T29Role = fkT17T29Role;
	}

	public Date getT17ChangedDate() {
		return t17ChangedDate;
	}

	public void setT17ChangedDate(Date t17ChangedDate) {
		this.t17ChangedDate = t17ChangedDate;
	}

	public String getT17ChangedBy() {
		return t17ChangedBy;
	}

	public void setT17ChangedBy(String t17ChangedBy) {
		this.t17ChangedBy = t17ChangedBy;
	}

	public String getT17CreatedBy() {
		return t17CreatedBy;
	}

	public void setT17CreatedBy(String t17CreatedBy) {
		this.t17CreatedBy = t17CreatedBy;
	}

	public Date getT17CreationDate() {
		return t17CreationDate;
	}

	public void setT17CreationDate(Date t17CreationDate) {
		this.t17CreationDate = t17CreationDate;
	}

	public String getT17Location() {
		return t17Location;
	}

	public void setT17Location(String t17Location) {
		this.t17Location = t17Location;
	}

	public T09Mandant getT09Mandant() {
		return t09Mandant;
	}

	public void setT09Mandant(T09Mandant t09Mandant) {
		this.t09Mandant = t09Mandant;
	}

	public T01Employee getT01Employee() {
		return t01Employee;
	}

	public void setT01Employee(T01Employee t01Employee) {
		this.t01Employee = t01Employee;
	}

}
