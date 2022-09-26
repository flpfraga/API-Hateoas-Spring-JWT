package com.fraga.APIRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fraga.APIRest.repository.UserRepository;
import com.fraga.APIRest.security.AccountCredentialsVO;
import com.fraga.APIRest.security.TokenVO;
import com.fraga.APIRest.security.jwt.JwtTokenProvider;

@Service
public class AuthService implements UserDetailsService{

	
	@Autowired
	UserRepository repository;
	
	public AuthService (UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.loadUserByUsername(username);
		if (user != null) {
			return (UserDetails) user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		
	}

	

}
