package com.sipag.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sipag.configuracoes.security.CustomAuthenticationProvider;
import com.sipag.configuracoes.security.CustomUserDetails;
import com.sipag.configuracoes.security.jwt.CurrentUser;
import com.sipag.configuracoes.security.jwt.JwtAuthenticationRequest;
import com.sipag.configuracoes.security.jwt.JwtUtil;
import com.sipag.service.CustomUseDetailsManager;

@RestController
@CrossOrigin(origins = "*")
public class SecurityController {

	@Autowired
	private CustomAuthenticationProvider authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUseDetailsManager customUserDetailsManager;
	
	
	@PostMapping(value = "/api/auth")
	public ResponseEntity<?> createAuthenticationToker(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		
		final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final CustomUserDetails userDetails =  (CustomUserDetails) customUserDetailsManager.loadUserByUsername(authentication.getName());
		final String token = jwtUtil.generateToken(userDetails);
		userDetails.setPassword(null);
		return ResponseEntity.ok(new CurrentUser(token,userDetails));
	}
	
	@PostMapping(value = "/api/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		String username = jwtUtil.getUsernameFromToken(token);
		final CustomUserDetails userDetails =  (CustomUserDetails) customUserDetailsManager.loadUserByUsername(username);
		
		if (jwtUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken, userDetails));
		}else {
			return ResponseEntity.ok(null);
		}
		
	}
	
}
