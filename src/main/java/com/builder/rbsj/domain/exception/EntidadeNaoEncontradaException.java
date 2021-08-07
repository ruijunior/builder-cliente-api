package com.builder.rbsj.domain.exception;


public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = -5932845077760015427L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}