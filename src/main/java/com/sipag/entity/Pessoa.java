package com.sipag.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sipag.util.GenericEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pessoa extends GenericEntity{
	
	private static final long serialVersionUID = -939743917275900732L;
	
	private String nome;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true)
	private String cpf;
	private LocalDate dataNascimento;
	private String telefone;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Endereco> endereco;
		
	private ArrayList<Perfil> perfil;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonIgnore
	private boolean isAccountNonExpired;
	
	@JsonIgnore
	private boolean isAccountNonLocked;
	
	@JsonIgnore
	private boolean isCredentialsNonExpired;
	
	private boolean isEnabled;
	
	public Pessoa() {
		super();
		this.perfil = new ArrayList<>();
		this.endereco = new ArrayList<>();
	}
}
