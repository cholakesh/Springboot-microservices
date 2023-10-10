package com.wipro.employee.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.employee.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	String type;
	
	String userName;
	
	@GetMapping(value="/",produces="text/html")
	public String getIndexPage(@RequestParam String userName,Model model) {
		this.userName=userName;
		model.addAttribute("userName",userName);
		return "index";
	}
	
	@GetMapping("/techRegistration")
	public ModelAndView getTechReg(Model model) {
		
		List<String> techs=Arrays.asList("Java", "C#", "PHP", "PERL");
		
		type="TECH";
		
		model.addAttribute("userName",userName);
		model.addAttribute("techs", techs);
		return new ModelAndView("empForm");
	}
	
	@GetMapping("/behavRegistration")
	public ModelAndView getBehavReg(Model model) {
		
		List<String> techs=Arrays.asList("Step Plus", "Email Etiquette", "Learning Agility" );
		
		type="BEH";
		
		model.addAttribute("userName",userName);
		model.addAttribute("techs", techs);
		return new ModelAndView("empForm");
	}
	
	@PostMapping("/saveEmpDetails")
	public String saveEmpDetails(Model model,@ModelAttribute("selectedOption")String assessment,@RequestParam("date")LocalDate date) throws ParseException {

		empService.saveEmpDetails(userName,assessment,date,type);
		return "index";
	}
	
	/*
	 * public static ModelAndView loadIndexPage() { return new
	 * ModelAndView("index"); }
	 */
	

}
