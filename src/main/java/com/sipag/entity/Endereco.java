package com.sipag.entity;

import javax.persistence.Entity;

import com.sipag.enums.EstadosEnum;
import com.sipag.util.GenericEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Endereco extends GenericEntity {

	private static final long serialVersionUID = -8945666229445887747L;
	
	private String cep;
	private String bairro;
	private String complemento;
	private EstadosEnum uf;
	
	
	public Endereco() {
		super();
	}

}
