package com.wipro.question1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.question1.model.Patient;
import com.wipro.question1.model.Service;

@RestController
public class PatientController {
	
	@Autowired
	private List<Patient> patients;
	
	@Autowired
	private List<Service> services;
	
	
	@GetMapping("/patients")
	public ModelAndView getAllPatients(Model model) {
		
		model.addAttribute("patients",patients);
		
		return new ModelAndView("patients");
	}
	
	@GetMapping("/searchPatients")
	public ModelAndView searchPatient(@RequestParam("id") Long id,Model model) {
		Patient patient=null;
		
		for(Patient p:patients) {
			if(id==p.getId()) {
				patient=p;
			}
		}
		model.addAttribute("specificPatient",patient);
		
		return new ModelAndView("patients");
	}
	
	
	
	
	@GetMapping("/patientForm") 
	public ModelAndView patientForm(Model model) {
		model.addAttribute("services",services);
	  
		return new ModelAndView("patientForm"); 
	}
	  
	  
	 
	
	@PostMapping("/savePatients")
	public ModelAndView savePatients(Model model,@ModelAttribute("id") Long id,@ModelAttribute("fname") String firstName,@ModelAttribute("lname") String lastName,@RequestParam("services") List<Long> serviceIds) {
		List<Service> respServices=new ArrayList<>();
		for(Long serviceId:serviceIds) {
			respServices.add(getServiceById(serviceId));
		}
		
		Patient patient=new Patient(id,firstName,lastName,respServices);
		if(!patients.contains(patient)) {
			patients.add(patient);
		}
		
		return getAllPatients(model);
	}
	
	public Service getServiceById(Long id) {
		for(Service s:services) {
			if(id==s.getId()) {
				return s;
			}
		}
		return null;
	}
}
