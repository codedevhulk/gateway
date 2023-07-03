package com.example.gateway;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	JwtUtils jwtutils;

	// @Autowired
//    private RestTemplate template;
	// @Autowired
	// private JwtUtil jwtUtil;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				// header contains token or not
				
				if(exchange.getRequest().getHeaders().containsKey("Authorization")) {
					String jwt=exchange.getRequest().getHeaders().get("Authorization").get(0);
					System.out.println(jwt);
					boolean validjwt=jwtutils.validateJwtToken(jwt);
					  if(!validjwt) 
						  throw new RuntimeException("Please login again"); 
				}
				
				/*
				 * if(exchange.getRequest().getPath().toString().contains("recruiter")) {
				 * Map<String, List<HttpCookie>> cookie=exchange.getRequest().getCookies();
				 * //System.out.println(cookie.get("session").get(0).getValue());
				 * 
				 * if(cookie!=null && cookie.containsKey("session")) {
				 * //restTemplate.getForObject("http://RECRUITERSERVICE/recruiter/all" //
				 * ,List.class); boolean
				 * validjwt=jwtutils.validateJwtToken(cookie.get("session").get(0).getValue());
				 * if(!validjwt) throw new RuntimeException("Not a valid jwt"); }
				 * 
				 * else { throw new RuntimeException("session cookie not present.please login");
				 * }
				 * 
				 * } else if(exchange.getRequest().getPath().toString().contains("jobseeker")) {
				 * 
				 * }
				 */
				 

			}
			return chain.filter(exchange);
		});
	}

	public static class Config {

	}
}
