package com.example.gateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	
	
	@GetMapping("/jobSeekerServiceFallBack")
	public String jobSeekerServiceFallBack() {
		return "Job seeker service is down! Please try again after some time";
	}
	
	
	@GetMapping("/recruiterServiceFallBack")
	public String recruiterServiceFallBack() {
		return "Recruiter service is down! Please try again after some time";
	}
	
	@GetMapping("/securityServiceFallBack")
	public String securityServiceFallBack() {
		return "Security service is down! Please try again after some time";
	}
	
	
	@GetMapping("/jobServiceFallBack")
	public String jobServiceFallBack() {
		return "Job service is down! Please try again after some time";
	}
}
