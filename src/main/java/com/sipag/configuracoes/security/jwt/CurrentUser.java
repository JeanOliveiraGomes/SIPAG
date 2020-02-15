package com.sipag.configuracoes.security.jwt;

import org.springframework.stereotype.Service;

import com.sipag.configuracoes.security.CustomUserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Service
@AllArgsConstructor
public class CurrentUser {
	private String token;
	private CustomUserDetails userDetails;
	
	public CurrentUser() {
		super();
	}
}
