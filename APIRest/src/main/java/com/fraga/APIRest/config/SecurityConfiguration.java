package com.fraga.APIRest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fraga.APIRest.security.jwt.JwtConfigurer;
import com.fraga.APIRest.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public AuthenticationManager authenticate(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailService)
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}
	
	


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.httpBasic().disable()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers("/auth/signin", "/api-docs/**", "/swagger-ui.html**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/user/v1/**").anonymous()
			.antMatchers("/api/user/**", "/api/movie/**").authenticated()
			.antMatchers("/api/admin/**").hasAuthority("ADMIN")
		.and()
			.cors()
		.and()
		.apply(new JwtConfigurer(tokenProvider));
		return http.build();

	}
	

}
