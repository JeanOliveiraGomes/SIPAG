package com.sipag.entity;

import javax.persistence.Entity;

import com.sipag.util.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Email extends GenericEntity {
	private static final long serialVersionUID = -4396533634045275701L;
	
	private String email;
}
