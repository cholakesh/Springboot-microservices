package com.wipro.patient.model;

import java.util.List;


public class Patient {
	
	private Long id;
	private String firstName;
	private String lastName;
	private List<Service> services;
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(Long id, String firstName, String lastName,List<Service> services) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.services=services;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}
