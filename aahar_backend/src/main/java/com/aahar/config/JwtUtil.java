package com.aahar.config;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.aahar.entities.Customer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
	@Value(value = "${jwt.token.expiration.millis}")
	public long jwtExpiration;
	@Value(value = "${jwt.token.secret}")
	public String jwtSecret;
	private Key jwtKey;

	@PostConstruct
	public void init() {
		jwtKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	public String createToken(Authentication auth) {
		Customer user = (Customer) auth.getPrincipal();
		System.out.println("User"+ user.toString());
		String subject = "" + user.getEmail(); // user.getUsername(); // user email
		String roles = user.getAuthorities().stream() // user role e.g. ROLE_USER or ROLE_ADMIN
				.map(authority -> authority.getAuthority()).collect(Collectors.joining(","));
		String token = Jwts.builder().setSubject(subject).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)).claim("role", roles)
				.claim("id", user.getId()).signWith(jwtKey, SignatureAlgorithm.HS256).compact();
		return token;
	}

	public Authentication validateToken(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(jwtKey).build();
		Claims claims = parser.parseClaimsJws(token).getBody();
		String custId = claims.getSubject();
		String roles = (String) claims.get("role");
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		Authentication auth = new UsernamePasswordAuthenticationToken(custId, null, authorities);
		return auth;
	}

	public Long extractId(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(jwtKey).build();
		Claims claims = parser.parseClaimsJws(token).getBody();
		Long id = claims.get("id", Long.class);
	    return id;
	}

	public String extractTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
