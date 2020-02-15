package com.sipag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sipag.entity.Pessoa;
import com.sipag.service.CustomUseDetailsManager;
import com.sipag.service.PessoaService;

@RestController
@RequestMapping(AdminPathBase.PESSOA )
@CrossOrigin(origins = "*")
public class PessoaController {

	
	@Autowired
	CustomUseDetailsManager custom;
	
	@Autowired
	PessoaService pessoaService;
	
	@GetMapping("/findByNome")
	@PreAuthorize("hasAnyAuthority('ADMIN','OPERADOR')")
	public List<Pessoa> findByNome(@RequestParam("nome") String nome) {
		return pessoaService.findByNome(nome);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMIN','OPERADOR')")
	public Iterable<Pessoa> findAll() {
		return pessoaService.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public Pessoa salvar(@RequestBody Pessoa pessoa) {
		return pessoaService.save(pessoa);
	}
	
	@GetMapping("/delete")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public void delete(@RequestParam("id") Long id) {
		pessoaService.delete(id);
	}
}
