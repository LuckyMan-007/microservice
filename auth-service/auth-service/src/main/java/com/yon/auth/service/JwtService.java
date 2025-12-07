package com.yon.auth.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${application.security.jwt.secret-key}")
	private String secretKey;
	
	@Value("${application.security.jwt.expiration}")
	private long expiration;
	
	public String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()+expiration))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
}
