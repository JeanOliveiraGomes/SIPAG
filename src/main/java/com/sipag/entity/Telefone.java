package com.sipag.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sipag.enums.TipoTelefoneEnum;
import com.sipag.util.GenericEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Telefone extends GenericEntity {

	private static final long serialVersionUID = -6234453410434578166L;
	
	@Column(nullable = false)
	private Integer numero;
	@Column(nullable = false)
	private TipoTelefoneEnum tipo;
	
}
