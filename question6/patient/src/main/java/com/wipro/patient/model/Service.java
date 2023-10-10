package com.wipro.patient.model;

public class Service {

	private Long id;
	private String service_name;
	private float fees;
	
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Service(Long id, String service_name, float fees) {
		super();
		this.id = id;
		this.service_name = service_name;
		this.fees = fees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", service_name=" + service_name + ", fees=" + fees + "]";
	}
	
	
	
}
