package com.sipag.configuracoes.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sipag.service.CustomUseDetailsManager;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	CustomUseDetailsManager customUserDetailManager;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails pessoa = (CustomUserDetails) customUserDetailManager.loadUserByUsername(username);

        if (pessoa == null || !pessoa.getEmail().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!passwordEncoder.matches(password, pessoa.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = pessoa.getPerfil();

        return new UsernamePasswordAuthenticationToken(pessoa, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
