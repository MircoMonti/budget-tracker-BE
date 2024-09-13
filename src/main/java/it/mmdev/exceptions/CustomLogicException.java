package it.mmdev.exceptions;

import it.mmdev.enums.ErrorCode;

public class CustomLogicException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String message;
	

	public CustomLogicException(ErrorCode errorCode) {
		super();
		this.code = errorCode.getCode();
		this.message = errorCode.getDescription();
	}
	
	public CustomLogicException(ErrorCode errorCode, Exception exc) {
		super(exc);
		this.code = errorCode.getCode();
		this.message = errorCode.getDescription();
	}
}
