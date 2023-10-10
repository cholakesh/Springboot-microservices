package com.wipro.patient.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wipro.patient.model.Patient;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
@ConfigurationProperties(prefix = "resilience4j.circuitbreaker.instances.service-detail")
public class SampleDataConfig {

	/*
	 * @Autowired LoadBalancerClient loadBalancerClient;
	 */

	@Bean
	Map<Long, Patient> createPatientsMap() {
		Map<Long, Patient> patients = new HashMap<>();
		patients.put(1L, new Patient(1L, "Cholakesh", "Nakkana", new ArrayList<>()));
		patients.put(2L, new Patient(2L, "Bharath", "Nalla", new ArrayList<>()));
		patients.put(3L, new Patient(3L, "Manoj", "Botchu", new ArrayList<>()));
		patients.put(4L, new Patient(4L, "Sandeep", "Kolla", new ArrayList<>()));
		return patients;
	}

	/*
	 * @CircuitBreaker(name="servicesDetails",fallbackMethod =
	 * "getAllServiceDetails") public Map<Long, Service> getServiceMap() {
	 * 
	 * // ServiceInstance instance =
	 * loadBalancerClient.choose("services management"); //services management
	 * 
	 * }
	 */

	@Bean
	Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfig() {

		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom().failureRateThreshold(80)
				.waitDurationInOpenState(Duration.ofSeconds(10))
				.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(10)
				.minimumNumberOfCalls(5)
				.automaticTransitionFromOpenToHalfOpenEnabled(true)
				.permittedNumberOfCallsInHalfOpenState(3)
				.build();

		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(10))
				.build();

		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.timeLimiterConfig(timeLimiterConfig).circuitBreakerConfig(circuitBreakerConfig).build());
	}
}
