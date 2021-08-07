package com.builder.rbsj.api.v1.assembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.builder.rbsj.api.v1.controller.ClienteController;
import com.builder.rbsj.api.v1.model.ClienteModel;
import com.builder.rbsj.domain.model.Cliente;

@Component
public class ClienteModelAssembler 
	extends RepresentationModelAssemblerSupport<Cliente, ClienteModel>{
	
	@Autowired
	private ModelMapper modelMapper;

	public ClienteModelAssembler() {
		super(ClienteController.class, ClienteModel.class);
	}

	@Override
	public ClienteModel toModel(Cliente cliente) {
		ClienteModel clienteModel = createModelWithId(cliente.getId(), cliente);
		modelMapper.map(cliente, clienteModel);
		
		clienteModel.add(linkTo(ClienteController.class).withRel("clientes"));
		
		return clienteModel;
	}
}
