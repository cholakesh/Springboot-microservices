package com.wipro.patient.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.patient.model.Patient;
import com.wipro.patient.model.Service;


@RestController
public class PatientController {

	@Autowired
	private Map<Long, Patient> patients;
	
	private Map<Long, Service> services;
	
	
	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
    
	
	RestTemplate restTemplate= new RestTemplate();

	@GetMapping("/patients")
	public ModelAndView getAllPatients(Model model) {

		Collection<Patient> patientsLi = patients.values();

		model.addAttribute("patients", patientsLi);

		return new ModelAndView("patients");
	}

	@GetMapping("/searchPatients")
	public ModelAndView searchPatient(@RequestParam("id") Long id, Model model) {

		Patient patient = new Patient();
		if (patients.containsKey(id)) {
			patient = patients.get(id);
		}

		model.addAttribute("specificPatient", patient);

		return new ModelAndView("patients");
	}

	@GetMapping("/patientForm")
//	@CircuitBreaker(name="servicesDetails",fallbackMethod = "getAllServiceDetails")
	public ModelAndView patientForm(Model model) {
		
		
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("service-detail");

		RestTemplate restTemplate = new RestTemplate();
		
		Map<Long, Service> services= (Map<Long, Service>) (circuitBreaker).run(() -> restTemplate.getForObject("http://localhost:8083/servicesDetails", Map.class),throwable -> getAllServiceDetails());

		Collection<Service> servicesLi = services.values();

		model.addAttribute("services", servicesLi);
		return new ModelAndView("patientForm");
	}
	
	public Map<Long,Service> getAllServiceDetails() {
		Map<Long, Service> services = new HashMap<>();

		services.put(1L, new Service(1L, "OPD", 500.0f));
		services.put(2L, new Service(2L, "X-ray", 1000.0f));
		services.put(3L, new Service(3L, "ECG", 2200.0f));

		/*
		 * Collection<Service> servicesLi = services.values();
		 * model.addAttribute("services", servicesLi);
		 */
		return services;
	}
	 

	@PostMapping("/savePatients")
	public ModelAndView savePatients(Model model, @ModelAttribute("id") Long id,
			@ModelAttribute("fname") String firstName, @ModelAttribute("lname") String lastName,
			@RequestParam("services") List<Long> serviceIds) {
		List<Service> li = new ArrayList<>();
		for (Long sid : serviceIds) {
			li.add(services.get(sid));
		}
		patients.put(id, new Patient(id, firstName, lastName, li));
		return getAllPatients(model);
	}
	
	@GetMapping("/details")
//	@CircuitBreaker(name="details", fallbackMethod = "getOptionalDetails")
	public String getDetails() {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("details");

		RestTemplate restTemplate = new RestTemplate();
		
		String services= (String) (circuitBreaker).run(() -> restTemplate.getForObject("http://jsonplaceholder.typicode.com/todoshds", String.class),throwable -> getOptionalDetails());
		/*
		 * ResponseEntity<String> entity=restTemplate.getForEntity(
		 * "http://jsonplaceholder.typicode.com/todosgdshg",String.class);
		 */
		return services;
	}
	
	public String getOptionalDetails(){
		return "SERVICE! Down, Please try after sometime";
	}

}
