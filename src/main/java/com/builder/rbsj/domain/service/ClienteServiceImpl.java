package com.builder.rbsj.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.builder.rbsj.domain.exception.ClienteNaoEncontradoException;
import com.builder.rbsj.domain.filter.ClienteFilter;
import com.builder.rbsj.domain.model.Cliente;
import com.builder.rbsj.domain.repository.ClienteRepository;
import com.builder.rbsj.domain.repository.spec.ClienteRepositorySpec;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService{
	
	private ClienteRepository clienteRepository;

	@Override
	public Page<Cliente> listar(Pageable pageable) {
		return this.clienteRepository.findAll(pageable);
	}
	
	@Override
	public Page<Cliente> pesquisar(ClienteFilter filtro, Pageable pageable) {
		return this.clienteRepository.findAll(ClienteRepositorySpec.filtrarPor(filtro), pageable);
	}

	@Override
	public Cliente buscar(Long clienteId) {
		return this.clienteRepository.findById(clienteId)
				.orElseThrow(() -> 
					new ClienteNaoEncontradoException(clienteId));
	}

	@Override
	public Cliente salvar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

}
