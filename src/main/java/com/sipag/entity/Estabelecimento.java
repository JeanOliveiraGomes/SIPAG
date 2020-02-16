package com.sipag.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Column(nullable = false, length = 14)
	private String cnpj;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(nullable = false)
	private List<Endereco> enderecos;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@Column(nullable = false)
	private List<Email> email;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@Column(nullable = false)
	private List<Telefone> telefone;
	
	
	public Estabelecimento() {
		this.email = new ArrayList<>();
		this.enderecos = new ArrayList<>();
		this.telefone = new ArrayList<Telefone>();
	}
}
