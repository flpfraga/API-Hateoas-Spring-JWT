package com.fraga.APIRest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.APIRest.repository.UserRepository;
import com.fraga.APIRest.security.AccountCredentialsVO;
import com.fraga.APIRest.security.TokenVO;
import com.fraga.APIRest.security.jwt.JwtTokenProvider;
import com.fraga.APIRest.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AuthenticationEndpoint", description = "Authentication for API")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService service;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticaionManager;
	
	@Autowired
	UserRepository repository;

	@SuppressWarnings("rawtypes")
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml",
			"application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			System.out.println("auth controller " + username + " " + password);
			authenticaionManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			System.out.println("depois do authenticate");
			var user = repository.loadUserByUsername(username);
			
			var token = new TokenVO();
			
			if (user != null) {
				token = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ResponseEntity.ok(token);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username password supplied " + e.getMessage());
		}
	}
	
	
	
	
	//	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
//		if (data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null
//				|| data.getPassword().isBlank()) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//		}
//		var token = service.signin(data);
//		if (token == null) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
//		}
//		return token;
//
//	}
}

