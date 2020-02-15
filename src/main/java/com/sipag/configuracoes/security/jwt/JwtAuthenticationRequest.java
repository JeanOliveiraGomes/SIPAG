package com.sipag.configuracoes.security.jwt;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JwtAuthenticationRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public JwtAuthenticationRequest(String email, String password) {
		this.setUsername(email);
		this.setPassword(password);
	}
	
}
