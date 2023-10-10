package com.wipro.question1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.question1.model.Service;

@RestController
public class ServiceController {
	
	@Autowired
	private List<Service> services;
	
	@GetMapping("/services")
	public ModelAndView getAllServices(Model model) {
		
		model.addAttribute("services",services);
		
		return new ModelAndView("services");
	}
	
	@GetMapping("/searchServices")
	public ModelAndView searchService(@RequestParam("id") Long id,Model model) {
		Service service=null;
		
		for(Service s:services) {
			if(id==s.getId()) {
				service=s;
			}
		}
		model.addAttribute("specificService",service);
		
		return new ModelAndView("services");
	}
	
	@GetMapping("/serviceForm") 
	public ModelAndView serviceForm(Model model) {
		  return new ModelAndView("serviceForm"); 
	}
	
	@PostMapping("/saveServices")
	public ModelAndView saveServices(Model model,@ModelAttribute("id") Long id,@ModelAttribute("sname") String serviceName,@ModelAttribute("fees") float fees) {
		
		int k=0;
		for(Service s:services) {
			if(!(s.getId()==id || s.getService_name().equals(serviceName))) {
				k++;
			}
			else break;
		}
		if(k==services.size()) {
			services.add(new Service(id,serviceName,fees));
			return getAllServices(model);
		}
		
		return new ModelAndView("serviceForm");
	}

}
