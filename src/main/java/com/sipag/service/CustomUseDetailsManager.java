package com.sipag.service;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sipag.configuracoes.security.ConverterPessoaToUserDetails;
import com.sipag.entity.Perfil;
import com.sipag.entity.Pessoa;
import com.sipag.enums.PerfilEnum;
import com.sipag.repository.PessoalRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Service
public class CustomUseDetailsManager implements UserDetailsManager {
	
	@Autowired 
	PessoalRepository pessoaRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ConverterPessoaToUserDetails converterPessoaToUserDetails;

	public void salvarPessoa(String email, PerfilEnum perfil) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(email);
		pessoa.setEmail(email);
		pessoa.setAccountNonLocked(true);
		pessoa.setAccountNonExpired(true);
		pessoa.setPassword(passwordEncoder.encode("123"));
		ArrayList<Perfil> roles = new ArrayList<>();
		roles.add(new Perfil(perfil.name()));
		pessoa.setPerfil(roles);
		pessoa.setEnabled(true);
		pessoaRepository.save(pessoa);
	}
	
	public Pessoa findByEmail(String email) {
		if (pessoaRepository.findByEmail(email).isPresent()) {
			return pessoaRepository.findByEmail(email).get();
		}else{
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		pessoaRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario ou senha invalidos"));
		return converterPessoaToUserDetails.pessoaToUseDetails(pessoaRepository.findByEmail(username).get());
	}

	@Override
	public void createUser(UserDetails user) {
		Pessoa pessoa = new Pessoa();
		pessoa.setEmail(user.getUsername());
		pessoa.setPassword(user.getPassword());
		pessoa.setAccountNonExpired(user.isAccountNonExpired());
		pessoa.setAccountNonLocked(user.isAccountNonLocked());
		pessoa.setCredentialsNonExpired(user.isCredentialsNonExpired());
		pessoa.setEnabled(user.isEnabled());
		ArrayList<Perfil> perfil = pessoa.getPerfil();
		perfil.add(new Perfil(PerfilEnum.USUARIO.toString()));
		pessoa.setPerfil(perfil);
		pessoaRepository.save(pessoa);
	}

	@Override
	public void updateUser(UserDetails user) {
		Pessoa pessoa = pessoaRepository.findByEmail(user.getUsername()).orElse(null);
		pessoa.setEmail(user.getUsername());
		pessoa.setPassword(user.getPassword());
		pessoa.setAccountNonExpired(user.isAccountNonExpired());
		pessoa.setAccountNonLocked(user.isAccountNonLocked());
		pessoa.setCredentialsNonExpired(user.isCredentialsNonExpired());
		pessoa.setEnabled(user.isEnabled());
		ArrayList<Perfil> perfil = (ArrayList<Perfil>) user.getAuthorities();
		pessoa.setPerfil(perfil);
		pessoaRepository.save(pessoa);
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public boolean userExists(String username) {
		Pessoa pessoa = pessoaRepository.findByEmail(username).orElse(null);
		return Objects.nonNull(pessoa);
	}
}
