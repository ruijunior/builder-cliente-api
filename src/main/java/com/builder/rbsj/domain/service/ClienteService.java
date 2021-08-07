package com.builder.rbsj.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.builder.rbsj.domain.filter.ClienteFilter;
import com.builder.rbsj.domain.model.Cliente;

public interface ClienteService {

	Page<Cliente> listar(Pageable pageable);
	
	public Page<Cliente> pesquisar(ClienteFilter filtro, Pageable pageable);

	Cliente buscar(Long clienteId);

	Cliente salvar(Cliente cliente);

}
