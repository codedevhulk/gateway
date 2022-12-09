package com.example.gateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

	
	
	@GetMapping("/productServiceFallBack")
	public String productServiceFallBack() {
		return "Product service is down!";
	}
	
	
	@GetMapping("/orderServiceFallBack")
	public String orderServiceFallBack() {
		return "Order service is down!";
	}
	
	@GetMapping("/paymentServiceFallBack")
	public String paymentServiceFallBack() {
		return "Payment service is down!";
	}
}
