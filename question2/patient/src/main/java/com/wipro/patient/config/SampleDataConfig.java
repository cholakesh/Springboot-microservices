package com.wipro.patient.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.wipro.patient.model.Patient;
import com.wipro.patient.model.Service;

@Configuration
public class SampleDataConfig {


	@Bean
	Map<Long,Patient> createPatientsMap(){
		Map<Long,Patient> patients= new HashMap<>();
		patients.put(1L, new Patient(1L, "Cholakesh" , "Nakkana",new ArrayList<>()));
		patients.put(2L, new Patient(2L, "Bharath" , "Nalla",new ArrayList<>()));
		patients.put(3L, new Patient(3L, "Manoj" , "Botchu",new ArrayList<>()));
		patients.put(4L, new Patient(4L, "Sandeep" , "Kolla",new ArrayList<>()));
		return patients;
	}
	
	public Map<Long,Service> getServiceMap(){
		RestTemplate restTemplate= new RestTemplate();
		
		String url = "http://localhost:8083/servicesDetails";

        ResponseEntity<Map<Long, Service>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<Long, Service>>() {}
        );

        Map<Long,Service> services = response.getBody();
        
        System.out.println(services);
		
		return services;
	}
	
	
}
