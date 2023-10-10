package com.wipro.service1.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.service1.model.Service;

@RestController
public class ServiceController {
	
	@Autowired
	private Map<Long,Service> services;
	
	@GetMapping("/services")
	public ModelAndView getAllServices(Model model) {
		
		Collection<Service> servicesLi=services.values();
		
		model.addAttribute("services",servicesLi);
		
		return new ModelAndView("services");
	}
	
	@GetMapping("/searchServices")
	public ModelAndView searchService(@RequestParam("id") Long id,Model model) {
		Service service=new Service();
		if(services.containsKey(id)) {
			service=services.get(id);
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
		
		services.put(id, new Service(id,serviceName,fees));
		
		return getAllServices(model);
	}
	
	@GetMapping("/servicesDetails")
	public Map<Long,Service> getServices(){
		return services;
	}

}
