package com.builder.rbsj.api.v1.model;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "clientes")
@Getter
@Setter
public class ClienteModel extends RepresentationModel<ClienteModel>{
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Rui Junior")
	private String nome;

	@ApiModelProperty(example = "rui@gmail.com")
	private String email;
	
	@ApiModelProperty(example = "(99)98888-0000")
	private String celular;
	
	@ApiModelProperty(example = "1978-08-10")
	private LocalDate nascimento;
	
	@ApiModelProperty(example = "42")
	private int idade;
}