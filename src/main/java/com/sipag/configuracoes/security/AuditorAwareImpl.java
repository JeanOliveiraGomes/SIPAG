package com.sipag.configuracoes.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String>{
	


	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  if (authentication == null || !authentication.isAuthenticated()) {
			   return Optional.of("");
			  }
		return Optional.of(authentication.getName());
	}

}
