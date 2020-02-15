package com.sipag.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.sipag.util.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Estabelecimento extends GenericEntity {
	
	private static final long serialVersionUID = 5029366923372804712L;

	@Size(min = 20, max = 100)
	private String nome;
	
	@Size(min = 14, max = 14)
	private Integer cnpj;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Endereco> enderecos;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Email> email;
	
	public Estabelecimento() {
		this.email = new ArrayList<>();
		this.enderecos = new ArrayList<>();
	}
}
