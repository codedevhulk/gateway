package com.example.gateway;



import java.util.Date;

//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

//import com.jobBoard.spring.security.login.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  //@Value("${bezkoder.app.jwtSecret}")
  private String jwtSecret="SECRET";

  //@Value("${bezkoder.app.jwtExpirationMs}")
  private int jwtExpirationMs=86400000;

  //@Value("${bezkoder.app.jwtCookieName}")
  private String jwtCookie="session";

  /*public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }*/

  
  public ResponseCookie generateJwtCookie(String userName) {
    String jwt = generateTokenFromUsername(userName);
    System.out.println("SHOW");
    ResponseCookie cookie = ResponseCookie.from("session", jwt).path("/recruiter").maxAge(24*60* 60).httpOnly(true).build();
    return cookie;
  }

  public ResponseCookie getCleanJwtCookie() {
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
    return cookie;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      //System.out.println("Entered here");
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
 
  public String generateTokenFromUsername(String username) {   
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + 86400000))
        .signWith(SignatureAlgorithm.HS512, "SECRET")
        .compact();
	  //return "dummy";
  }
}



