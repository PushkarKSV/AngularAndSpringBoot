package com.ibm.mgb.model;

import org.springframework.stereotype.Component;

@Component
public class Employee{
	
	String nt_ID;
	String FirstName;
	String LastName;
	String Phone;
	String Email;
	String trader_code;
	String name;
	String trader_name;
	String Role;
	
	
	
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getTrader_name() {
		return trader_name;
	}
	public void setTrader_name(String trader_name) {
		this.trader_name = trader_name;
	}
	public String getTrader_code() {
		return trader_code;
	}
	public void setTrader_code(String trader_code) {
		this.trader_code = trader_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getnt_ID() {
		return nt_ID;
	}
	public void setNT_ID(String nT_ID) {
		nt_ID = nT_ID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((FirstName == null) ? 0 : FirstName.hashCode());
		result = prime * result + ((LastName == null) ? 0 : LastName.hashCode());
		result = prime * result + ((Phone == null) ? 0 : Phone.hashCode());
		result = prime * result + ((Role == null) ? 0 : Role.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nt_ID == null) ? 0 : nt_ID.hashCode());
		result = prime * result + ((trader_code == null) ? 0 : trader_code.hashCode());
		result = prime * result + ((trader_name == null) ? 0 : trader_name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (FirstName == null) {
			if (other.FirstName != null)
				return false;
		} else if (!FirstName.equals(other.FirstName))
			return false;
		if (LastName == null) {
			if (other.LastName != null)
				return false;
		} else if (!LastName.equals(other.LastName))
			return false;
		if (Phone == null) {
			if (other.Phone != null)
				return false;
		} else if (!Phone.equals(other.Phone))
			return false;
		if (Role == null) {
			if (other.Role != null)
				return false;
		} else if (!Role.equals(other.Role))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nt_ID == null) {
			if (other.nt_ID != null)
				return false;
		} else if (!nt_ID.equals(other.nt_ID))
			return false;
		if (trader_code == null) {
			if (other.trader_code != null)
				return false;
		} else if (!trader_code.equals(other.trader_code))
			return false;
		if (trader_name == null) {
			if (other.trader_name != null)
				return false;
		} else if (!trader_name.equals(other.trader_name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Employees [nt_ID=" + nt_ID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Phone=" + Phone
				+ ", Email=" + Email + ", trader_code=" + trader_code + ", name=" + name + ", trader_name="
				+ trader_name + ", Role=" + Role + "]";
	}
	
}
