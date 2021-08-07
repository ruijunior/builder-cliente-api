package com.builder.rbsj.domain.exception;

public class ClienteNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = -4708911368650435645L;

	public ClienteNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ClienteNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de cliente com código %d", id));
	}

}
