package com.user.mgmt.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebSecurityConfig {

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtAutFilter jwtAutFilter;

	@Bean
	public SecurityFilterChain SecurityFiletChain(HttpSecurity httpSecurity) {
		httpSecurity.csrf(config -> config.disable())
				.sessionManagement(
						sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/user/**").hasRole("ADMIN")
						// .requestMatchers(HttpMethod.GET).hasRole("DOCTER")
						.requestMatchers("/auth/**").permitAll().anyRequest().authenticated())

				.addFilterBefore(jwtAutFilter, UsernamePasswordAuthenticationFilter.class)
				.oauth2Login(auth2 -> auth2.failureHandler((HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) -> {
                     log.error("Authentication Failure");
				}));
		// .formLogin(Customizer.withDefaults());
		return httpSecurity.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}
}
