package com.builder.rbsj.domain.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteFilter {
	
	private String nome;
	private String email;
	private String celular;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate nascimentoInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate nascimentoFim;
}