package com.sipag.configuracoes.security;

import org.springframework.stereotype.Component;

import com.sipag.entity.Pessoa;

@Component
public class ConverterPessoaToUserDetails {

	public CustomUserDetails pessoaToUseDetails(Pessoa pessoa) {
		CustomUserDetails custom = new CustomUserDetails(pessoa.getEmail(), pessoa.getPassword(), pessoa.getPerfil(), pessoa.isAccountNonExpired(),pessoa.isAccountNonLocked(),pessoa.isCredentialsNonExpired(),pessoa.isEnabled()); 
		return custom;
	}
}
