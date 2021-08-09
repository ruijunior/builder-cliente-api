package com.builder.rbsj.domain.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, length = 15)
	private String celular;
	
	private LocalDate nascimento;

	@Setter(value = AccessLevel.NONE)
	@Transient
	private int idade;
	
	public boolean isNovo() {
		return getId() == null;
	}
	
	public int getIdade() {
		return Period.between(nascimento, LocalDate.now()).getYears();
	}

}
