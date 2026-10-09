package com.demo.hpsh.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${JWT_SECRET}")
	private String secret;
	
	public String generateJwtToken(String username) {
		SecretKey key = Keys.hmacShaKeyFor(
					Decoders.BASE64.decode(secret)
				);
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(key)
				.compact();
	}

}
