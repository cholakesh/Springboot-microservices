package com.wipro.login.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wipro.login.model.User;

@Configuration
public class LoginConfig {

	@Bean
	Map<String, User> getAllUsers() {

		Map<String, User> users = new HashMap<>();
		users.put("Admin", new User("Admin", "Admin", "Admin"));
		users.put("Admin1", new User("Admin1", "Admin1", "Admin"));
		users.put("Cholakesh", new User("Cholakesh", "Cholakesh", "User"));
		users.put("Bharath", new User("Bharath", "Bharath", "User"));
		users.put("Jeevan", new User("Jeevan", "Jeevan", "User"));
		users.put("Manoj", new User("Manoj", "Manoj", "User"));

		return users;
	}

	/*
	 * public ModelAndView getService(String serviceName) {
	 * 
	 * ServiceInstance instance = loadBalancerClient.choose(serviceName); //services
	 * management RestTemplate restTemplate = new RestTemplate();
	 * 
	 * String url ="http://"+instance.getHost()+":"+instance.getPort();
	 * 
	 * System.out.println(url);
	 * 
	 * 
	 * ResponseEntity<ModelAndView> response = restTemplate.exchange( url,
	 * HttpMethod.GET, null, new ParameterizedTypeReference<ModelAndView>() {} );
	 * 
	 * 
	 * // ModelAndView nextPage = response.getBody();
	 * 
	 * ModelAndView nextPage = restTemplate.getForObject(url, ModelAndView.class);
	 * 
	 * 
	 * return nextPage; }
	 */

}
