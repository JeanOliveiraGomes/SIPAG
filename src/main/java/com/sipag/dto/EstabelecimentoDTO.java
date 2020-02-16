package com.sipag.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.sipag.entity.Email;
import com.sipag.entity.Endereco;
import com.sipag.entity.Telefone;
import com.sipag.util.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstabelecimentoDTO extends GenericEntity {
	
	@Size(min = 20, max = 100)
	private String nome;
	
	@Size(min = 14, max = 14)
	private String cnpj;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
	
	private List<Email> email;
	
	private List<Telefone> telefone;
	
	public EstabelecimentoDTO() {
		this.email = new ArrayList<>();
		this.enderecos = new ArrayList<>();
	}
}
