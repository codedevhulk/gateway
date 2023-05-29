package com.example.gateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

	
	
	
	@GetMapping("/index")
	String hello() {
		return "Hellooo world";
	}
}
