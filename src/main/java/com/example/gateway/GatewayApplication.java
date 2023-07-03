package com.example.gateway;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	@Bean
	Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
		return factory->factory.configureDefault(id->new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()).build());
	}
	
	  @Bean public RestTemplate template(){ return new RestTemplate(); }
	 
		/*
		 * @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
		 * config = new CorsConfiguration();
		 * config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); //only
		 * requests from these origins (URL of the web page) are accepted
		 * config.setAllowedMethods(Arrays.asList("GET","POST"));
		 * config.setAllowedHeaders(Arrays.asList("content-type"));
		 * config.setAllowCredentials(true); //this is important in preflight request
		 * (OPTIONS) to tell the browser, that it can send credentials, e.g. cookies
		 * along the subsequent request UrlBasedCorsConfigurationSource configSource =
		 * new UrlBasedCorsConfigurationSource();
		 * configSource.registerCorsConfiguration("/**", config); return configSource; }
		 */
		 
		 
	 
}
