package com.builder.rbsj.domain.exception;

public class NegocioException extends RuntimeException {

	static final long serialVersionUID = 2090275613419681530L;

	public NegocioException(String msg) {
		super(msg);
	}
	
	public NegocioException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
	
}