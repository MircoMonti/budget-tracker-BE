package it.mmdev.utils;

import it.mmdev.enums.ErrorCode;
import it.mmdev.exceptions.NotAuthorizedException;
import it.mmdev.exceptions.TokenExpiredException;
import it.mmdev.responses.RestCustomError;

public class ValidationErrorConverter {
	
	public static RestCustomError convertExceptionToRestCustomError(Exception e) {		
		RestCustomError restError = new RestCustomError(ErrorCode.GENERIC_ERROR.getCode(), e.getMessage());
		return restError;
	}
	
	public static RestCustomError convertExceptionToRestCustomError(TokenExpiredException e) {		
		RestCustomError restError = new RestCustomError(ErrorCode.TOKEN_EXPIRED.getCode(), e.getMessage());
		return restError;
	}
	
	public static RestCustomError convertExceptionToRestCustomError(NotAuthorizedException e) {
		RestCustomError restError = new RestCustomError(ErrorCode.USER_NOT_AUTHORIZED.getCode(), e.getMessage());
		return restError;
	}
	
}
