package com.descomplica.spring_blog.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.descomplica.spring_blog.models.User;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);

		return JWT.create()
				.withSubject(user.getUsername())
				.withClaim("role", user.getRole().name())
				.withIssuedAt(now)
				.withExpiresAt(expiryDate)
				.sign(Algorithm.HMAC256(secret));
	}

	public String getUsernameFromToken(String token) {
		return JWT.require(Algorithm.HMAC256(secret))
				.build()
				.verify(token)
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
			return true;
		} catch (JWTVerificationException exception) {
			return false;
		}
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
