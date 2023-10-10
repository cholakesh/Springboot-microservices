package com.wipro.patient.proxy;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.wipro.patient.model.Service;

@FeignClient(name="service-ms", url = "http://localhost:8083")
@Component
public interface ServiceDetails {
	
	@GetMapping("/servicesDetails")
	Map<Long,Service> getServices();

}
