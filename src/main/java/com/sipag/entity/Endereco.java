package com.sipag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sipag.enums.EstadosEnum;
import com.sipag.util.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Endereco extends GenericEntity {

	private static final long serialVersionUID = -8945666229445887747L;
	
	@Column(nullable = false) 
	private String cep;
	@Column(nullable = false) 
	private String lougradouro;
	
	@Column(nullable = false) 
	private String bairro;

	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private EstadosEnum uf;
	
	private String complemento;
	
	
	public Endereco() {
		super();
	}


	public Endereco(String cep, String lougradouro, String bairro, String cidade, EstadosEnum uf, String complemento) {
		super();
		this.cep = cep;
		this.lougradouro = lougradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.complemento = complemento;
	}
	
	

}
