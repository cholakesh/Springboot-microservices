package com.wipro.login.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.wipro.login.model.User;

@Service
public class LoginService {
	
	@Autowired
	LoadBalancerClient loadBalancerClient;

	public String verifyUserDetails(String userName, String password, String userRole, Map<String, User> users) {
		if(users.containsKey(userName)) {
			User user=users.get(userName);
			if(user.getPaasword().equals(password) && user.getUserRole().equals(userRole)) return userRole;
		}
		return "User not found";
	}
	
	public String getService(String serviceName) {
		
			System.out.println(serviceName);
			
			ServiceInstance instance = loadBalancerClient.choose(serviceName); 
	
			String url ="http://"+instance.getHost()+":"+instance.getPort();
			
			System.out.println(url);
			
			return url;
	}

}
