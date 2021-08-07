package com.builder.rbsj.api.v1.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.builder.rbsj.api.v1.assembler.ClienteInputDisassembler;
import com.builder.rbsj.api.v1.assembler.ClienteModelAssembler;
import com.builder.rbsj.api.v1.model.ClienteModel;
import com.builder.rbsj.api.v1.model.input.ClienteInput;
import com.builder.rbsj.api.v1.openApi.controller.ClienteControllerOpenApi;
import com.builder.rbsj.domain.filter.ClienteFilter;
import com.builder.rbsj.domain.model.Cliente;
import com.builder.rbsj.domain.service.ClienteService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Clientes")
@RestController
@RequestMapping(path = "/v1/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class ClienteController implements ClienteControllerOpenApi {
	
	private ClienteService service;
	private ClienteModelAssembler modelAssembler;
	private ClienteInputDisassembler inputDisassembler;
	private PagedResourcesAssembler<Cliente> pagedResourcesAssembler;
	
		
	@GetMapping
	@Override
	public PagedModel<ClienteModel> listar(Pageable pageable) {
		Page<Cliente> clientes = this.service.listar(pageable);
		PagedModel<ClienteModel> clientesPageModel = pagedResourcesAssembler
				.toModel(clientes, modelAssembler);
		
		return clientesPageModel;
	}
	
	@GetMapping("/pesquisar")
	@Override
	public PagedModel<ClienteModel> pesquisar(ClienteFilter filtro, Pageable pageable) {
		Page<Cliente> clientes = this.service.pesquisar(filtro, pageable);
		PagedModel<ClienteModel> clientesPageModel = pagedResourcesAssembler
				.toModel(clientes, modelAssembler);
		
		return clientesPageModel;
	}
	
	@GetMapping("/{clienteId}")
	@Override
	public ClienteModel buscar(@PathVariable Long clienteId) {
		Cliente cliente = this.service.buscar(clienteId);
		ClienteModel clienteModel = modelAssembler.toModel(cliente);
		return clienteModel;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public ClienteModel adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		log.info("Cadastrando novo cliente {} ", clienteInput.getNome());
		Cliente cliente = inputDisassembler.toDomainObject(clienteInput);
		
		cliente = this.service.salvar(cliente);
		log.info("Cliente {} salvo!", clienteInput.getNome());
		return modelAssembler.toModel(cliente);
	}
	

	@PutMapping("/{clienteId}")
	@Override
	public ClienteModel atualizar(@PathVariable Long clienteId, @Valid @RequestBody ClienteInput clienteInput) {
		log.info("Busca cliente pelo ID {} ", clienteId);
		Cliente clienteAtual = this.service.buscar(clienteId);
		
		log.info("Atribui campos de entrada ao cliente retornado na busca: {}", clienteAtual.getNome());
		inputDisassembler.copyToDomainObject(clienteInput, clienteAtual);
		
		clienteAtual = this.service.salvar(clienteAtual);
		log.info("Cliente {} atualizado!", clienteInput.getNome());
		return modelAssembler.toModel(clienteAtual);
	}
	
}
