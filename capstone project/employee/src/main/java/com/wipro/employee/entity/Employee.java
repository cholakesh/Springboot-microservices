package com.wipro.employee.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {

	@Id
	private String userName;
	private String assessment;
	private LocalDate date;
	private String type;
	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Employee(String userName, String assessment, LocalDate date, String type) {
		super();
		this.userName = userName;
		this.assessment = assessment;
		this.date = date;
		this.type = type;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getAssessment() {
		return assessment;
	}



	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "Employee [userName=" + userName + ", assessment=" + assessment + ", date=" + date + ", type=" + type
				+ "]";
	}
	
	
}
