package com.user.mgmt.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAutFilter extends OncePerRequestFilter {

	@Autowired
	private UserRespository userRespositor;

	@Autowired
	private AuthUtil authUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("Incoming request" + request.getRequestURI());
		final String requestHeader = request.getHeader("Authorization");
		if (requestHeader == null || !requestHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtToken = requestHeader.split("Bearer ")[1];
		String userName = authUtil.getUserNameFromToken(jwtToken);

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserInfo user = (UserInfo) userRespositor.findByUserName(userName).orElse(null);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userName, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		filterChain.doFilter(request, response);

	}
}
