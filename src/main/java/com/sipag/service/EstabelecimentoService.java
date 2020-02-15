package com.sipag.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	
	public EstabelecimentoDTO salvar(EstabelecimentoDTO estabelecimentoDTO) {
		Estabelecimento estabelecimento = DtoUtil.modelMapper.map(estabelecimentoDTO, Estabelecimento.class);
		estabelecimentoRepository.save(estabelecimento);
		return estabelecimentoDTO;
	}
	
	public List<EstabelecimentoDTO> findAll(){
		
		//LOGICA EXISTENTE APENAS PARA COMPRIR OBRIGATORIEDADES DO DESAFIO UTILIZAR STREAM
		List<Estabelecimento> estabelecimentoList = new ArrayList<Estabelecimento>();
		List<EstabelecimentoDTO> estabelecimentoDto = new ArrayList<EstabelecimentoDTO>();
		
		estabelecimentoRepository.findAll().iterator().forEachRemaining(d -> estabelecimentoList.add(d));
		
		estabelecimentoList.stream().forEach(a -> estabelecimentoDto.add(DtoUtil.modelMapper.map(a, EstabelecimentoDTO.class)));
	
		return estabelecimentoDto;
	}
	
	public void deletar(Long id) {
		Estabelecimento estabelecimento = estabelecimentoRepository.findById(id).orElse(null);
	
		if (Objects.nonNull(estabelecimento)) {
			estabelecimentoRepository.delete(estabelecimento);	
		}
	}
	
}
