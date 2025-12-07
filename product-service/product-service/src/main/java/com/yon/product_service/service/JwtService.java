package com.yon.product_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
//
//	application:
//		  security:
//		    jwt:
//		      secret-key: "abcdopendadi#@vanthavadivanvaratiumpodi"
		    	  
	@Value("${application..security.jwt.secret-key}")
	private String secret;
	
	public String extractUserName(String token) {
		
		return Jwts.parser().setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean isValid(String token) {
		
		try {
			Jwts.parser().setSigningKey(secret.getBytes())
			.parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
