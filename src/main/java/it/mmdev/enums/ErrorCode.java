package it.mmdev.enums;

public enum ErrorCode {
	
	GENERIC_ERROR("error.generic.unknownError", "Generic server side error"),
	
	
	TOKEN_EXPIRED("error.token.expired", "Token is expired"),
	TOKEN_WRONG_FORMAT("error.token.wrongFormat", "Token has wrong format"),
	
	USER_NOT_FOUND("error.user.notFound", "User not found"),
	USER_NOT_AUTHORIZED("error.user.notAuthorized", "User is not authorized to perform this action"),
	USER_EMAIL_ALREADY_EXISTS("error.user.emailAlreadyExists", "Email is already taken"),
	USERNAME_ALREADY_EXISTS("error.user.usernameAlreadyExists", "Username is already taken"),
	WRONG_PASSWORD("error.user.wrongPassword", "Wrong passoword"),
	PASSWORD_CANNOT_BE_SAME("error.user.samePsw", "New password cannot be like the old one"),
	
	ACCOUNT_NOT_FOUND("error.account.notFound", "Account not foud"),
	
	TRANSACTION_NOT_FOUND("error.transaction.notFound", "Transaction not found")
	
	;
	
	private String code;
	private String description;
	
	
	private ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
	
	public ErrorCode resolve(String code) {
		
		for(ErrorCode error : values()) {
			if (error.code.equals(code)) 
				return error;
		}
		
		return null;
	}

}
