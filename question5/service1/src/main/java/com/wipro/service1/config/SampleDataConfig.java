package com.wipro.service1.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.wipro.service1.model.Service;

@Configuration
public class SampleDataConfig {
	
	@Bean
	@Scope("singleton")
    Map<Long,Service> createSampleServices() {
        Map<Long,Service> services = new HashMap<>();
        
        services.put(1L, new Service(1L, "OPD", 500.0f));
        services.put(2L, new Service(2L,"X-ray", 1000.0f));
        services.put(3L, new Service(3L, "ECG", 2200.0f));
        
        return services;
    }
	
}
