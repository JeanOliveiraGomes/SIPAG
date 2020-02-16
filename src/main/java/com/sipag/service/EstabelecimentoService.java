package com.sipag.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sipag.dto.EstabelecimentoDTO;
import com.sipag.entity.Estabelecimento;
import com.sipag.repository.EstabelecimentoRepository;
import com.sipag.util.DtoUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {
	
	private final EstabelecimentoRepository estabelecimentoRepository;
	
	public EstabelecimentoDTO salvar(EstabelecimentoDTO estabelecimentoDTO) throws Exception {
		Estabelecimento estabelecimento = DtoUtil.modelMapper.map(estabelecimentoDTO, Estabelecimento.class);
		if (estabelecimento.getEmail().size() < 1) {
			throw new Exception("Email Obrigatorio");
		}
		if (estabelecimento.getEnderecos().size() < 1) {
			throw new Exception("Endereco Obrigatorio");
		}
		return DtoUtil.modelMapper.map(estabelecimentoRepository.save(estabelecimento), EstabelecimentoDTO.class);
	}
	
	public Iterable<Estabelecimento> findAll(){
		return estabelecimentoRepository.findAll();
	}
	
	public void deletar(Long id) {
		Estabelecimento estabelecimento = estabelecimentoRepository.findById(id).orElse(null);
	
		if (Objects.nonNull(estabelecimento)) {
			estabelecimentoRepository.delete(estabelecimento);	
		}
	}
	
}
