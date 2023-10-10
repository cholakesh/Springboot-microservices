package com.wipro.patient.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.patient.model.Patient;
import com.wipro.patient.model.Service;
import com.wipro.patient.proxy.ServiceDetails;

import jakarta.annotation.PostConstruct;

@RestController
public class PatientController {
	
	@Autowired
	private Map<Long,Patient> patients;
	
	@Autowired
	private ServiceDetails serviceDetails;
	
	private Map<Long,Service> services;
	
	
	/*
	 * @PostConstruct public void init() { services=serviceDetails.getServices(); }
	 */
	
	
	/*
	 * public PatientController(Map<Long, Service> services) { super();
	 * this.services = serviceDetails.getServices(); }
	 */
	 
	 
	 
	 	
	
	@GetMapping("/patients")
	public ModelAndView getAllPatients(Model model) {
		
		Collection<Patient> patientsLi=patients.values();
		
		model.addAttribute("patients",patientsLi);
		
		return new ModelAndView("patients");
	}
	
	@GetMapping("/searchPatients")
	public ModelAndView searchPatient(@RequestParam("id") Long id,Model model) {
		
		Patient patient=new Patient();
		if(patients.containsKey(id)) {
			patient=patients.get(id);
		}
		
		model.addAttribute("specificPatient",patient);
		
		return new ModelAndView("patients");
	}
	
	
	
	
	
	  @GetMapping("/patientForm") 
	  public ModelAndView patientForm(Model model) {
		  
		  services=serviceDetails.getServices();
		  Collection<Service> servicesLi=services.values();
		  
		  
		  model.addAttribute("services",servicesLi);
		  return new ModelAndView("patientForm"); 
	  }
	 
	  
	  
	 
	
	
	  @PostMapping("/savePatients") 
	  public ModelAndView savePatients(Model model,@ModelAttribute("id") Long id,@ModelAttribute("fname") String firstName,@ModelAttribute("lname") String lastName,@RequestParam("services") List<Long> serviceIds) {
		  List<Service> li=new ArrayList<>();
		  for(Long sid:serviceIds) {
			  li.add(services.get(sid));
		  }
		  patients.put(id, new Patient(id,firstName,lastName,li));
		  return getAllPatients(model);
	  }

		/*
		 * public Service getServiceById(Long id) { for (Service s : services) { if (id
		 * == s.getId()) { return s; } } return null; }
		 */
	 
}
