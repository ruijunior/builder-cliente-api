package com.builder.rbsj.api.v1.openApi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;

import com.builder.rbsj.api.v1.model.ClienteModel;
import com.builder.rbsj.api.v1.model.input.ClienteInput;
import com.builder.rbsj.domain.filter.ClienteFilter;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ClienteControllerOpenApi {
	
	@ApiOperation("Lista os clientes de forma paginada")
	public PagedModel<ClienteModel> listar(Pageable pageable);
	
	@ApiOperation("Realiza pesquisa de clientes de forma paginada")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Filtra por Nome, E-mail ou numero do Celular",
				name = "campos", paramType = "query", type = "string")
	})
	public PagedModel<ClienteModel> pesquisar(ClienteFilter filtro, Pageable pageable);
	
	@ApiOperation("Busca um cliente por ID")
	public ClienteModel buscar(
			@ApiParam(value = "ID de um cliente", example = "1", required = true)
			@PathVariable Long clienteId);
	
	@ApiOperation("")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente cadastrado"),
	})
	public ClienteModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo cliente", required = true)
			ClienteInput clienteInput) ;
	
	@ApiOperation("Atualiza um cliente por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente atualizado"),
		@ApiResponse(code = 404, message = "Cliente não encontrado")
	})
	public ClienteModel atualizar(
			@ApiParam(value = "ID de um cliente", example = "1", required = true)
			Long clienteId, 
			@ApiParam(name = "corpo", value = "Representação de um cliente com os novos dados", required = true)
			ClienteInput clienteInput);
}
