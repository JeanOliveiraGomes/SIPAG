package com.sipag.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sipag.entity.Pessoa;
import com.sipag.repository.PessoalRepository;


@Service
public class PessoaService {
	
	@Autowired
	PessoalRepository pessoaRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public List<Pessoa> findByNome(String nome){
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "nome");
		return pessoaRepository.findByNomeContainingIgnoreCase(nome, pageable);
	}
	
	public Pessoa save(Pessoa pessoa) {
		
		if (Objects.nonNull(pessoa.getPassword()) && !pessoa.getPassword().equals("")) {			
			pessoa.setPassword(passwordEncoder.encode(pessoa.getPassword()));			
		}else if(Objects.nonNull(pessoa.getId())){
			Pessoa pessoaAtual = pessoaRepository.findById(pessoa.getId()).orElse(null);
			if (Objects.nonNull(pessoaAtual)) {
				pessoa.setPassword(Objects.nonNull(pessoaAtual.getPassword())? pessoaAtual.getPassword() : passwordEncoder.encode("123"));				
			}
		}
		pessoa.setAccountNonExpired(true);
		pessoa.setAccountNonLocked(true);
		pessoa.setEnabled(true);
		pessoa.setCredentialsNonExpired(true);
		return pessoaRepository.save(pessoa);
	}
	
	public Iterable<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}
	
	public void delete(Long id) {
		this.pessoaRepository.deleteById(id);
	}
}
