package com.wipro.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.admin.entity.Employee;
import com.wipro.admin.repo.EmployeeRepo;

@Controller
public class AdminController {
	
	@Autowired
	EmployeeRepo empRepo;
	
	@GetMapping(value="/",produces="text/html")
	public String getIndexPage() {
		return "index";
	}
	
	/*
	 * //this is for proxy method
	 * 
	 * @GetMapping(value="/",produces="text/html") public String getIndexPage(Model
	 * model,String userName) { System.out.println(userName);
	 * model.addAttribute("userName",userName); return "index"; }
	 */

	@GetMapping("/allRegistrationDetails")
	public ModelAndView getAllDetails(Model model) {
		
		List<Employee> employees=empRepo.findAll();
		model.addAttribute("employees", employees);
		return new ModelAndView("regDetails");
	}
}
