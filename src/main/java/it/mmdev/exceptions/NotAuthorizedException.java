package it.mmdev.exceptions;

import it.mmdev.enums.ErrorCode;
import lombok.Getter;

@Getter
public class NotAuthorizedException extends Exception {
	
	private static final long serialVersionUID = 6169876802512629967L;
	
	public NotAuthorizedException(Exception e) {
		super(e);
	}
	
	public NotAuthorizedException() {
		super(ErrorCode.USER_NOT_AUTHORIZED.getDescription());
	}

}
