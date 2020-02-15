package com.sipag.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sipag.dto.EstabelecimentoDTO;
import com.sipag.service.EstabelecimentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(AdminPathBase.ESTABELECIMENTO )
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstabelecimentoController {
	
	private final EstabelecimentoService estabelecimentoService;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMIN','OPERADOR')")
	public List<EstabelecimentoDTO> findAll() {
		return estabelecimentoService.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADMIN','OPERADOR')")
	public EstabelecimentoDTO salvar(@RequestBody EstabelecimentoDTO estabelecimento) {
		return estabelecimentoService.salvar(estabelecimento);
	}
	
	@GetMapping("/delete")
	@PreAuthorize("hasAnyAuthority('ADMIN','OPERADOR')")
	public void delete(@RequestParam("id") Long id) {
		estabelecimentoService.deletar(id);
	}

}
