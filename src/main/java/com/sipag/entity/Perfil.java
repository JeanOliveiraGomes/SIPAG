package com.sipag.entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 6432880507603744085L;
	
	private String perfil;

	@Override
	public String getAuthority() {
		return perfil;
	}
	
	public Perfil () {
		super();
	}
}
