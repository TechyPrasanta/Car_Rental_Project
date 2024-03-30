package com.example.demo.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.UserService;
import com.example.demo.utils.JWTutils;

import lombok.NonNull;   

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

@Autowired
JWTutils jwtUtils;
	
@Autowired
UserService userService;
	
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, 
			                        @NonNull HttpServletResponse response,
			                        @NonNull FilterChain filterChain)
			throws ServletException, IOException {
         final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String UserEmail;
		if(StringUtils.isEmpty(authHeader)|| !StringUtils.startsWithIgnoreCase(authHeader, "Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
	jwt = authHeader.substring(7);
		
	UserEmail = jwtUtils.extractUserName(jwt);	
	if(StringUtils.isEmpty(UserEmail)
			&& SecurityContextHolder.getContext().getAuthentication()== null) {
	UserDetails userDetails = userService.userDetailsService().loadUserByUsername(UserEmail);
	if(jwtUtils.isTokenValid(jwt, userDetails)) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			     userDetails, null, userDetails.getAuthorities() );
	authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	context.setAuthentication(authToken);
	  }
	}
	filterChain.doFilter(request, response);	
		
	}

	
	
	
	
}
