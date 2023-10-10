package com.wipro.question1.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.wipro.question1.model.Patient;
import com.wipro.question1.model.Service;

@Configuration
public class SampleDataConfig {

	@Bean
	List<Patient> createPatientsList(){
		List<Patient> li=new ArrayList<>();
		
		li.add(new Patient(1L, "Cholakesh" , "Nakkana",new ArrayList<>()));
		li.add(new Patient(2L, "Bharath" , "Nalla",new ArrayList<>()));
		li.add(new Patient(3L, "Manoj" , "Botchu",new ArrayList<>()));
		li.add(new Patient(4L, "Sandeep" , "Kolla",new ArrayList<>()));
		
		return li;
	}
	
	@Bean
	@Scope("singleton")
    List<Service> createSampleServices() {
        List<Service> services = new ArrayList<>();
        
        services.add(new Service(1L, "OPD", 500.0f));
        services.add(new Service(2L, "X-ray", 1000.0f));
        services.add(new Service(3L, "ECG", 2200.0f));
        
        return services;
    }
	
}
