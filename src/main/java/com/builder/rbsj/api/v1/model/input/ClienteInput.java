package com.builder.rbsj.api.v1.model.input;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
	
	@ApiModelProperty(example = "Jose da Silva", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "jose@email.com", required = true)
	@NotBlank
	@Email
	private String email;
	
	@ApiModelProperty(example = "(99)98888-0000", required = true)
	@NotBlank
	@Size(max = 14, min = 14)
	private String celular;
	
	@Past
	@ApiModelProperty(example = "1978-08-10", required = true)
	private LocalDate nascimento;

}
