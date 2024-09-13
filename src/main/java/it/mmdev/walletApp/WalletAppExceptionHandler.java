package it.mmdev.walletApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.mmdev.exceptions.CustomLogicException;
import it.mmdev.exceptions.NotAuthorizedException;
import it.mmdev.responses.RestCustomError;
import it.mmdev.utils.ValidationErrorConverter;

public class WalletAppExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(CustomLogicException.class)
	public ResponseEntity<RestCustomError> handleCustomLogicException(CustomLogicException exc) {
		System.out.println("Custom logic error");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ValidationErrorConverter.convertExceptionToRestCustomError(exc));
	}
	
	@ExceptionHandler(NotAuthorizedException.class)
	public ResponseEntity<RestCustomError> handleNotAuthorizedException(NotAuthorizedException exc) {
		System.out.println("User is not authorized");
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ValidationErrorConverter.convertExceptionToRestCustomError(exc));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestCustomError> handleCustomLogicException(Exception exc) {
		System.out.println("Unknown internal server error");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ValidationErrorConverter.convertExceptionToRestCustomError(exc));
	}

}
