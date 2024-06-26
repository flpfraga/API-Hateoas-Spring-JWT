package com.fraga.APIRest.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fraga.APIRest.exception.InvalidJwtAuthenticationException;
import com.fraga.APIRest.security.Token;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expire-length:36000000}")
	private long validityInMilliseconds = 36000000; // 1h

	private final UserDetailsService userDetailsService;

	Algorithm algorithm = null;

	public JwtTokenProvider(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}

	public Token createAccessToken(String username, List<String> roles) {
		Date now = new Date();
		Date valid = new Date(now.getTime() + validityInMilliseconds);
		var accessToken = getAccessToken(username, roles, now, valid);
		var refreshToken = getRefreshToken(username, roles, now);
		return new Token(username, true, now, valid, accessToken, refreshToken);
	}

	private String getAccessToken(String username, List<String> roles, Date now, Date valid) {
		String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(valid).withSubject(username)
				.withIssuer(issueUrl).sign(algorithm).strip();
	}

	private String getRefreshToken(String username, List<String> roles, Date now) {
		Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));

		return JWT.create().withClaim("roles", roles).withIssuedAt(now).withExpiresAt(validityRefreshToken).withSubject(username)
				.sign(algorithm).strip();
	}
	
	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
	}

	private DecodedJWT decodedToken(String token) {
		Algorithm algoDecoder = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(algoDecoder).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}		
		return null;
	}
	
	public boolean validateToken(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		try {
			return !decodedJWT.getExpiresAt().before(new Date());
		} catch (Exception e) {
			throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
		}
	}

}
