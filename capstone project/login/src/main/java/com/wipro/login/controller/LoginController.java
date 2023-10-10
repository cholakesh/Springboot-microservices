package com.wipro.login.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wipro.login.config.LoginConfig;
import com.wipro.login.model.User;
import com.wipro.login.proxy.AdminProxy;
import com.wipro.login.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	Map<String, User> users;

	@Autowired
	LoginService loginService;

	@Autowired
	LoginConfig loginConfig;

	@Autowired
	AdminProxy adminProxy;
	
	/*
	 * @Value("${pivotal.adminservice.name}") protected String adminService;
	 */

	@PostMapping(value="/nextPage")
	public String getNextPage(Model model, @ModelAttribute("userName") String userName,
			@ModelAttribute("password") String password, @ModelAttribute("userRole") String userRole,HttpServletResponse response) throws IOException {
		String userType = loginService.verifyUserDetails(userName, password, userRole, users);
		String url = null;
		if (userType.equals("Admin")) {

			System.out.println("Into the Admin Service");
			url = loginService.getService("admin-service");
//			return adminProxy.getIndexPage(userName);

//			return adminProxy.getIndexPage();
		}
		else if (userType.equals("User")) {
			System.out.println("Into the Employee Service");
			url = loginService.getService("employee-service");
		}
		
		else {
			return loadIndexPage(model);
		}
		
		url=url+"?userName="+userName;
		
		
		/*
		 * ModelAndView nextPage= new ModelAndView();
		 * 
		 * nextPage.setViewName("redirect:/"+url);
		 */
		 
		
			/*
			 * String nextPage="redirect:/"+url; System.out.println(nextPage);
			 */

		
//		  RestTemplate restTemplate=new RestTemplate();
		  
		  
		  
		  
			/*
			 * ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.GET,
			 * null, new ParameterizedTypeReference<ModelAndView>() {} );
			 */
		  
		  
//		  ModelAndView nextPage = response.getBody();
		  
		  
		  
		  
			
			/*
			 * String nextPage = restTemplate.getForObject(url, String.class);
			 * 
			 * System.out.println(nextPage);
			 */
			 
		
		  response.sendRedirect(url);
		return null;
	}
	
	public String loadIndexPage(Model model) {
		
		model.addAttribute("errorMessage","Invalid username or passowrd");
		
		return "index";
	}

}
