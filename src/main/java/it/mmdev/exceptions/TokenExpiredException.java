package it.mmdev.exceptions;

import it.mmdev.enums.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends Exception {
	
	private static final long serialVersionUID = -2642422127653228811L;
	
	private String code;
	
	public TokenExpiredException(Exception e) {
		super(e);
		this.code = ErrorCode.TOKEN_EXPIRED.getCode();
	}
	
	public TokenExpiredException() {
		super(ErrorCode.TOKEN_EXPIRED.getDescription());
	}

}
