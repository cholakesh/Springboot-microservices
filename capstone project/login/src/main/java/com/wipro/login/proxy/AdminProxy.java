package com.wipro.login.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="adminService", url = "http://localhost:8084")
@Component
public interface AdminProxy {

	@GetMapping("/")
	public String getIndexPage(@RequestParam String userName);
}
